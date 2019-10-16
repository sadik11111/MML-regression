/**
 * generated by Xtext 2.19.0
 */
package org.xtext.example.mydsl.mml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SVR</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.mml.SVR#getC <em>C</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.mml.SVR#getKernel <em>Kernel</em>}</li>
 * </ul>
 *
 * @see org.xtext.example.mydsl.mml.MmlPackage#getSVR()
 * @model
 * @generated
 */
public interface SVR extends MLAlgorithm
{
  /**
   * Returns the value of the '<em><b>C</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>C</em>' attribute.
   * @see #setC(String)
   * @see org.xtext.example.mydsl.mml.MmlPackage#getSVR_C()
   * @model
   * @generated
   */
  String getC();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.mml.SVR#getC <em>C</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>C</em>' attribute.
   * @see #getC()
   * @generated
   */
  void setC(String value);

  /**
   * Returns the value of the '<em><b>Kernel</b></em>' attribute.
   * The literals are from the enumeration {@link org.xtext.example.mydsl.mml.SVMKernel}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Kernel</em>' attribute.
   * @see org.xtext.example.mydsl.mml.SVMKernel
   * @see #setKernel(SVMKernel)
   * @see org.xtext.example.mydsl.mml.MmlPackage#getSVR_Kernel()
   * @model
   * @generated
   */
  SVMKernel getKernel();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.mml.SVR#getKernel <em>Kernel</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kernel</em>' attribute.
   * @see org.xtext.example.mydsl.mml.SVMKernel
   * @see #getKernel()
   * @generated
   */
  void setKernel(SVMKernel value);

} // SVR
