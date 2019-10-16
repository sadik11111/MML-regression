/**
 * generated by Xtext 2.19.0
 */
package org.xtext.example.mydsl.mml.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.xtext.example.mydsl.mml.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.xtext.example.mydsl.mml.MmlPackage
 * @generated
 */
public class MmlAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static MmlPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MmlAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = MmlPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MmlSwitch<Adapter> modelSwitch =
    new MmlSwitch<Adapter>()
    {
      @Override
      public Adapter caseMMLModel(MMLModel object)
      {
        return createMMLModelAdapter();
      }
      @Override
      public Adapter caseDataInput(DataInput object)
      {
        return createDataInputAdapter();
      }
      @Override
      public Adapter caseCSVParsingConfiguration(CSVParsingConfiguration object)
      {
        return createCSVParsingConfigurationAdapter();
      }
      @Override
      public Adapter caseMLChoiceAlgorithm(MLChoiceAlgorithm object)
      {
        return createMLChoiceAlgorithmAdapter();
      }
      @Override
      public Adapter caseMLAlgorithm(MLAlgorithm object)
      {
        return createMLAlgorithmAdapter();
      }
      @Override
      public Adapter caseSVR(SVR object)
      {
        return createSVRAdapter();
      }
      @Override
      public Adapter caseDT(DT object)
      {
        return createDTAdapter();
      }
      @Override
      public Adapter caseRandomForest(RandomForest object)
      {
        return createRandomForestAdapter();
      }
      @Override
      public Adapter caseSGD(SGD object)
      {
        return createSGDAdapter();
      }
      @Override
      public Adapter caseGTB(GTB object)
      {
        return createGTBAdapter();
      }
      @Override
      public Adapter caseRFormula(RFormula object)
      {
        return createRFormulaAdapter();
      }
      @Override
      public Adapter caseXFormula(XFormula object)
      {
        return createXFormulaAdapter();
      }
      @Override
      public Adapter caseAllVariables(AllVariables object)
      {
        return createAllVariablesAdapter();
      }
      @Override
      public Adapter casePredictorVariables(PredictorVariables object)
      {
        return createPredictorVariablesAdapter();
      }
      @Override
      public Adapter caseFormulaItem(FormulaItem object)
      {
        return createFormulaItemAdapter();
      }
      @Override
      public Adapter caseValidation(Validation object)
      {
        return createValidationAdapter();
      }
      @Override
      public Adapter caseStratificationMethod(StratificationMethod object)
      {
        return createStratificationMethodAdapter();
      }
      @Override
      public Adapter caseCrossValidation(CrossValidation object)
      {
        return createCrossValidationAdapter();
      }
      @Override
      public Adapter caseTrainingTest(TrainingTest object)
      {
        return createTrainingTestAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.MMLModel <em>MML Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.MMLModel
   * @generated
   */
  public Adapter createMMLModelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.DataInput <em>Data Input</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.DataInput
   * @generated
   */
  public Adapter createDataInputAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.CSVParsingConfiguration <em>CSV Parsing Configuration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.CSVParsingConfiguration
   * @generated
   */
  public Adapter createCSVParsingConfigurationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.MLChoiceAlgorithm <em>ML Choice Algorithm</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.MLChoiceAlgorithm
   * @generated
   */
  public Adapter createMLChoiceAlgorithmAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.MLAlgorithm <em>ML Algorithm</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.MLAlgorithm
   * @generated
   */
  public Adapter createMLAlgorithmAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.SVR <em>SVR</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.SVR
   * @generated
   */
  public Adapter createSVRAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.DT <em>DT</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.DT
   * @generated
   */
  public Adapter createDTAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.RandomForest <em>Random Forest</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.RandomForest
   * @generated
   */
  public Adapter createRandomForestAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.SGD <em>SGD</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.SGD
   * @generated
   */
  public Adapter createSGDAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.GTB <em>GTB</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.GTB
   * @generated
   */
  public Adapter createGTBAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.RFormula <em>RFormula</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.RFormula
   * @generated
   */
  public Adapter createRFormulaAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.XFormula <em>XFormula</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.XFormula
   * @generated
   */
  public Adapter createXFormulaAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.AllVariables <em>All Variables</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.AllVariables
   * @generated
   */
  public Adapter createAllVariablesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.PredictorVariables <em>Predictor Variables</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.PredictorVariables
   * @generated
   */
  public Adapter createPredictorVariablesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.FormulaItem <em>Formula Item</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.FormulaItem
   * @generated
   */
  public Adapter createFormulaItemAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.Validation <em>Validation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.Validation
   * @generated
   */
  public Adapter createValidationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.StratificationMethod <em>Stratification Method</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.StratificationMethod
   * @generated
   */
  public Adapter createStratificationMethodAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.CrossValidation <em>Cross Validation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.CrossValidation
   * @generated
   */
  public Adapter createCrossValidationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.xtext.example.mydsl.mml.TrainingTest <em>Training Test</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.xtext.example.mydsl.mml.TrainingTest
   * @generated
   */
  public Adapter createTrainingTestAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //MmlAdapterFactory
