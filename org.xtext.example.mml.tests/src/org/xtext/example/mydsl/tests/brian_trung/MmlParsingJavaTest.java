package org.xtext.example.mydsl.tests.brian_trung;

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
import org.xtext.example.mydsl.mml.ValidationMetric;
import org.xtext.example.mydsl.mml.impl.CrossValidationImpl;
import org.xtext.example.mydsl.mml.impl.DTImpl;
import org.xtext.example.mydsl.mml.impl.GTBImpl;
import org.xtext.example.mydsl.mml.impl.RandomForestImpl;
import org.xtext.example.mydsl.mml.impl.SGDImpl;
import org.xtext.example.mydsl.mml.impl.SVRImpl;
import org.xtext.example.mydsl.mml.impl.TrainingTestImpl;
import org.xtext.example.mydsl.tests.MmlInjectorProvider;

import com.google.common.io.Files;
import com.google.inject.Inject;

@ExtendWith(InjectionExtension.class)
@InjectWith(MmlInjectorProvider.class)
public class MmlParsingJavaTest {

	@Inject
	ParseHelper<MMLModel> parseHelper;

	@Test
	public void loadModel() throws Exception {
		MMLModel result = parseHelper.parse("datainput \"foo.csv\"\n" + "mlframework scikit-learn\n" + "algorithm DT\n"
				+ "TrainingTest { percentageTraining 70 }\n" + "mean_absolute_error\n" + "");
		Assertions.assertNotNull(result);
		EList<Resource.Diagnostic> errors = result.eResource().getErrors();
		Assertions.assertTrue(errors.isEmpty(), "Unexpected errors");
		Assertions.assertEquals("foo.csv", result.getInput().getFilelocation());

	}

	@Test
	public void compileDataInput() throws Exception {
		MMLModel result = parseHelper.parse("datainput \"foo2.csv\" separator ;\n" + "mlframework scikit-learn\n"
				+ "algorithm DT\n" + "TrainingTest { percentageTraining 70 }\n" + "mean_absolute_error\n" + "");
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
		String csvReading = "mml_data = pd.read_csv(" + mkValueInSingleQuote(fileLocation) + ", sep="
				+ mkValueInSingleQuote(csv_separator) + ")";
		String pandasCode = pythonImport + csvReading;

		pandasCode += "\nprint (mml_data)\n";

		Files.write(pandasCode.getBytes(), new File("mml.py"));
		// end of Python generation for csv parsing

		FrameworkLang framework = result.getAlgorithm().getFramework();
		if (FrameworkLang.SCIKIT.equals(framework)) {
			String importAlgoFrom = "ensemble";
			String algoCall = "";
			MLAlgorithm algo = result.getAlgorithm().getAlgorithm();
			if (algo instanceof DTImpl) {
				importAlgoFrom = "tree";
				algoCall = "DecisionTreeRegressor()";
			} else if (algo instanceof GTBImpl) {
				algoCall = "GradientBoostingRegressor()";
			} else if (algo instanceof RandomForestImpl) {
				algoCall = "RandomForestRegressor()";
			} else if (algo instanceof SGDImpl) {
				importAlgoFrom = "linear_model";
				algoCall = "SGDRegressor()";
			} else if (algo instanceof SVRImpl) {
				importAlgoFrom = "svm";
				algoCall = "SVR()";
			} else {
				// algo non reconnu
			}
			// ...
			String algoImport = "from sklearn import " + importAlgoFrom;
			String algoRes = "clf = " + importAlgoFrom + "." + algoCall;

			String split = "";
			RFormula formula = result.getFormula();
			if (formula != null) {
				String colName = formula.getPredictive().getColName();
				if (colName != null) {
					split = "X = mml_data.drop(columns=[\"" + colName + "\"])" + "\n" + "y = mml_data[\"" + colName
							+ "\"]";
				} else {
					int column = formula.getPredictive().getColumn();
					split = "X = mml_data.drop(columns=[" + column + "])" + "\n" + "y = mml_data[" + column + "]";
				}
			} else {
				// par défaut la cible est la dernière colonne
				split = "X = mml_data.drop(columns=[len(mmm_data.columns)-1])" + "\n"
						+ "y = mml_data[len(mmm_data.columns)-1]";
			}

			Validation validation = result.getValidation();
			StratificationMethod strat = validation.getStratification();

			String importStratFrom = "";
			String stratRes = "";

			if (strat instanceof CrossValidationImpl) {
				importStratFrom = "cross_val_score";
				int numRepCross = strat.getNumber();

				stratRes += "cross_val_score(clf, X, Y, C=" + numRepCross + ")";
			} else if (strat instanceof TrainingTestImpl) {
				importStratFrom = "train_test_split";
				int percentage = strat.getNumber();
				stratRes += "test_size = " + (100.0 - percentage) / 100 + "\n";
				stratRes += "X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=test_size)";
			} else {
				// stratification non reconnue
			}

			String stratImport = "from sklearn.model_selection import" + importStratFrom;
			
			String metricRes="";
			EList<ValidationMetric> metricList = validation.getMetric();
			for(ValidationMetric metric : metricList) {
				String metricName = metric.getName();
				if(metricName == "MSE" || metricName == "mean_squared_error") {
					metricRes += "" + "\n";
				}else if(metricName == "MAE" || metricName == "mean_absolute_error") {
					metricRes += "" + "\n";
				}else if(metricName == "MAPE" || metricName == "mean_absolute_percentage_error") {
					metricRes += "" + "\n";
				}else {
					// metric non supportée
				}
			}
			// ...
		} else {
			// not yet supported
		}

		/*
		 * Calling generated Python script (basic solution through systems call) we
		 * assume that "python" is in the path
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
