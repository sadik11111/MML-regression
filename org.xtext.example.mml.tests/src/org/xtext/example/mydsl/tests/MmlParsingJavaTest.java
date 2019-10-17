package org.xtext.example.mydsl.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.sound.midi.MidiFileFormat;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.documentation.impl.MultiLineFileHeaderProvider;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.xtext.example.mydsl.mml.CSVParsingConfiguration;
import org.xtext.example.mydsl.mml.DataInput;
import org.xtext.example.mydsl.mml.FrameworkLang;
import org.xtext.example.mydsl.mml.MLAlgorithm;
import org.xtext.example.mydsl.mml.MMLModel;
import org.xtext.example.mydsl.mml.RFormula;
import org.xtext.example.mydsl.mml.StratificationMethod;
import org.xtext.example.mydsl.mml.Validation;
import org.xtext.example.mydsl.mml.impl.CrossValidationImpl;
import org.xtext.example.mydsl.mml.impl.DTImpl;
import org.xtext.example.mydsl.mml.impl.GTBImpl;
import org.xtext.example.mydsl.mml.impl.RandomForestImpl;
import org.xtext.example.mydsl.mml.impl.SGDImpl;
import org.xtext.example.mydsl.mml.impl.SVRImpl;
import org.xtext.example.mydsl.mml.impl.TrainingTestImpl;

import com.google.common.io.Files;
import com.google.inject.Inject;

@ExtendWith(InjectionExtension.class)
@InjectWith(MmlInjectorProvider.class)
public class MmlParsingJavaTest {

	@Inject
	ParseHelper<MMLModel> parseHelper;
	
	@Test
	public void loadModel() throws Exception {
		MMLModel result = parseHelper.parse("datainput \"foo.csv\"\n"
				+ "mlframework scikit-learn\n"
				+ "algorithm DT\n"
				+ "TrainingTest { percentageTraining 70 }\n"
				+ "mean_absolute_error\n"
				+ "");
		Assertions.assertNotNull(result);
		EList<Resource.Diagnostic> errors = result.eResource().getErrors();
		Assertions.assertTrue(errors.isEmpty(), "Unexpected errors");			
		Assertions.assertEquals("foo.csv", result.getInput().getFilelocation());			
		
	}		
	
	@Test
	public void compileDataInput() throws Exception {
		MMLModel result = parseHelper.parse("datainput \"foo2.csv\" separator ;\n"
				+ "mlframework scikit-learn\n"
				+ "algorithm DT\n"
				+ "TrainingTest { percentageTraining 70 }\n"
				+ "mean_absolute_error\n"
				+ "");
		DataInput dataInput = result.getInput();
		String fileLocation = dataInput.getFilelocation();
	
		
		String pythonImport = "import pandas as pd\n"; 
		String DEFAULT_COLUMN_SEPARATOR = ","; // by default
		String csv_separator = DEFAULT_COLUMN_SEPARATOR;
		CSVParsingConfiguration parsingInstruction = dataInput.getParsingInstruction();
		if (parsingInstruction != null) {			
			System.err.println("parsing instruction..." + parsingInstruction);
			csv_separator = parsingInstruction.getSep().toString();
		}
		String csvReading = "mml_data = pd.read_csv(" + mkValueInSingleQuote(fileLocation) + ", sep=" + mkValueInSingleQuote(csv_separator) + ")";						
		String pandasCode = pythonImport + csvReading;
		
		pandasCode += "\nprint (mml_data)\n"; 
		
		Files.write(pandasCode.getBytes(), new File("mml.py"));
		// end of Python generation for csv parsing
		
		FrameworkLang framework = result.getAlgorithm().getFramework();
		if(FrameworkLang.SCIKIT.equals(framework)) {
			String importAlgoFrom = "ensemble";
			String algoCall = "";
			MLAlgorithm algo = result.getAlgorithm().getAlgorithm();
			if(algo instanceof DTImpl) {
				importAlgoFrom="tree";
				algoCall="DecisionTreeRegressor()";
			}else if(algo instanceof GTBImpl) {
				algoCall="GradientBoostingRegressor()";
			}else if(algo instanceof RandomForestImpl) {
				algoCall="RandomForestRegressor()";
			}else if(algo instanceof SGDImpl) {
				importAlgoFrom="linear_model";
				algoCall="SGDRegressor()";
			}else if(algo instanceof SVRImpl) {
				importAlgoFrom="svm";
				algoCall="SVR()";
			}else {
				//algo non reconnu
			}
			//... 
			String algoImport = "from sklearn import "+importAlgoFrom;
			String algoRes = "clf = "+importAlgoFrom+"."+algoCall;
			
			RFormula formula = result.getFormula();			
			//formula est optionnel, on l'ignore pour l'instant
			
			Validation validation = result.getValidation();
			StratificationMethod strat = validation.getStratification();
			
			String importStratFrom = "";
			
			if(strat instanceof CrossValidationImpl) {
				
			}else if(strat instanceof TrainingTestImpl) {
				
			}else {
				//stratification non reconnue
			}
			
			String stratImport="from sklearn.model_selection import";
			String stratRes= "";
			
			//...
		}else {
			//not yet supported
		}
		
		/*
		 * Calling generated Python script (basic solution through systems call)
		 * we assume that "python" is in the path
		 */
		Process p = Runtime.getRuntime().exec("python mml.py");
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line; 
		while ((line = in.readLine()) != null) {
			System.out.println(line);
	    }

		
		
	}

	private String mkValueInSingleQuote(String val) {
		return "'" + val + "'";
	}


}
