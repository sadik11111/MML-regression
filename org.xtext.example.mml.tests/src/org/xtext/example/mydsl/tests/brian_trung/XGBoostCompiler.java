package org.xtext.example.mydsl.tests.brian_trung;

import org.xtext.example.mydsl.mml.DataInput;
import org.xtext.example.mydsl.mml.MLAlgorithm;
import org.xtext.example.mydsl.mml.MMLModel;
import org.xtext.example.mydsl.mml.impl.DTImpl;
import org.xtext.example.mydsl.mml.impl.GTBImpl;
import org.xtext.example.mydsl.mml.impl.RandomForestImpl;
import org.xtext.example.mydsl.mml.impl.SGDImpl;
import org.xtext.example.mydsl.mml.impl.SVRImpl;

public class XGBoostCompiler implements Compiler {

	@Override
	public void compile(MMLModel model) {
		DataInput dataInput = model.getInput();
		String fileLocation = dataInput.getFilelocation();
		MLAlgorithm algo = model.getAlgorithm().getAlgorithm();
		
		String importXGBoost = "import xgboost as xgb";
		String importPanda = "import pandas as pd";
		// Using XGBoost with scikit-learn
		String importSklearn = "import sklearn";
		String readCSV = "train_df = pd.read_csv(" + fileLocation + ", header = 0)";
		String importAlgoFrom = "";
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
		String algoRes = "clf = " + importAlgoFrom + "." + algoCall;
	}

}
