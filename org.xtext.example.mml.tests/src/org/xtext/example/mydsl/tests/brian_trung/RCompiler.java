package org.xtext.example.mydsl.tests.brian_trung;

import org.xtext.example.mydsl.mml.DataInput;
import org.xtext.example.mydsl.mml.MLAlgorithm;
import org.xtext.example.mydsl.mml.MMLModel;
import org.xtext.example.mydsl.mml.impl.DTImpl;
import org.xtext.example.mydsl.mml.impl.GTBImpl;
import org.xtext.example.mydsl.mml.impl.RandomForestImpl;
import org.xtext.example.mydsl.mml.impl.SGDImpl;
import org.xtext.example.mydsl.mml.impl.SVRImpl;

public class RCompiler implements Compiler {

	@Override
	public void compile(MMLModel model) {
		DataInput dataInput = model.getInput();
		String fileLocation = dataInput.getFilelocation();
		String readCSV = "MyData <- read.csv(file=" + fileLocation + ", header=TRUE, sep=\",\")";
		
		String algoCall = "";
		MLAlgorithm algo = model.getAlgorithm().getAlgorithm();
		String algoPackage = "";
	
		if (algo instanceof DTImpl) {
			algoPackage = "install.packages(\"rpart\")\n" + "\n" + "library(rpart)";
			algoCall = "fit <- rpart(method=\"anova\", MyData )";
		} else if (algo instanceof GTBImpl) {
			algoCall = "GradientBoostingRegressor()";
		} else if (algo instanceof RandomForestImpl) {
			algoCall = "RandomForestRegressor()";
		} else if (algo instanceof SGDImpl) {
			algoCall = "SGDRegressor()";
		} else if (algo instanceof SVRImpl) {
			algoCall = "SVR()";
		} else {
			// algo non reconnu
		}
		// ...
		//scikitImports+= "from sklearn import " + importAlgoFrom+"\n";
		String algoRes = algoPackage + algoCall;
		
	}
	
}
