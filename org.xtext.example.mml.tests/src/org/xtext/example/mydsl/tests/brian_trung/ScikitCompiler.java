package org.xtext.example.mydsl.tests.brian_trung;

import java.util.ArrayList;
import java.util.List;

import org.xtext.example.mydsl.mml.CSVParsingConfiguration;
import org.xtext.example.mydsl.mml.DataInput;
import org.xtext.example.mydsl.mml.MLAlgorithm;
import org.xtext.example.mydsl.mml.MMLModel;
import org.xtext.example.mydsl.mml.RFormula;
import org.xtext.example.mydsl.mml.StratificationMethod;
import org.xtext.example.mydsl.mml.impl.DTImpl;
import org.xtext.example.mydsl.mml.impl.GTBImpl;
import org.xtext.example.mydsl.mml.impl.RandomForestImpl;
import org.xtext.example.mydsl.mml.impl.SGDImpl;
import org.xtext.example.mydsl.mml.impl.SVRImpl;

public class ScikitCompiler implements Compiler {

	@Override
	public void compile(MMLModel mml) {
		DataInput dataInput = mml.getInput();
		String fileLocation = dataInput.getFilelocation();
		
		String pythonImport = "import pandas as pd\n";
		String DEFAULT_COLUMN_SEPARATOR = ","; // by default
		String csv_separator = DEFAULT_COLUMN_SEPARATOR;
		CSVParsingConfiguration parsingInstruction = dataInput.getParsingInstruction();
		if (parsingInstruction != null) {
			System.err.println("parsing instruction..." + parsingInstruction);
			csv_separator = parsingInstruction.getSep().toString();
		}
		String csvReading = "mml_data = pd.read_csv(" + UtilCompil.mkValueInSingleQuote(fileLocation) + ", sep="
				+ UtilCompil.mkValueInSingleQuote(csv_separator) + ")\n";
		String scikitImports = pythonImport;
		String scikitProgram = csvReading;
	}

	private List<String> compileAlgo(MLAlgorithm algo) {
		List<String> algoRes = new ArrayList<>();
		
		String importAlgoFrom = "ensemble";
		String algoCall = "";
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
		String importAlgo = "from sklearn import " + importAlgoFrom + "\n";
		String callAlgo = "clf = " + importAlgoFrom + "." + algoCall;
		
		algoRes.add(importAlgo);
		algoRes.add(callAlgo);

		return algoRes;
	}

	private String compileStrategy(StratificationMethod strat) {

		return "";
	}

	private String compileFormula(RFormula formula) {

		return "";
	}

}
