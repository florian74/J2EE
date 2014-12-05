package com.testBase.fonctionWebService;

public class TestBaseDBManagerProxy implements com.testBase.fonctionWebService.TestBaseDBManager {
  private String _endpoint = null;
  private com.testBase.fonctionWebService.TestBaseDBManager testBaseDBManager = null;
  
  public TestBaseDBManagerProxy() {
    _initTestBaseDBManagerProxy();
  }
  
  public TestBaseDBManagerProxy(String endpoint) {
    _endpoint = endpoint;
    _initTestBaseDBManagerProxy();
  }
  
  private void _initTestBaseDBManagerProxy() {
    try {
      testBaseDBManager = (new com.testBase.fonctionWebService.TestBaseDBManagerServiceLocator()).getTestBaseDBManagerPort();
      if (testBaseDBManager != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)testBaseDBManager)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)testBaseDBManager)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (testBaseDBManager != null)
      ((javax.xml.rpc.Stub)testBaseDBManager)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.testBase.fonctionWebService.TestBaseDBManager getTestBaseDBManager() {
    if (testBaseDBManager == null)
      _initTestBaseDBManagerProxy();
    return testBaseDBManager;
  }
  
  public void createStubAuteur() throws java.rmi.RemoteException{
    if (testBaseDBManager == null)
      _initTestBaseDBManagerProxy();
    testBaseDBManager.createStubAuteur();
  }
  
  public java.lang.String modifierTout(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException{
    if (testBaseDBManager == null)
      _initTestBaseDBManagerProxy();
    return testBaseDBManager.modifierTout(arg0, arg1, arg2, arg3);
  }
  
  public java.lang.String[] afficher(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (testBaseDBManager == null)
      _initTestBaseDBManagerProxy();
    return testBaseDBManager.afficher(arg0, arg1);
  }
  
  public int additionner(int arg0, int arg1) throws java.rmi.RemoteException{
    if (testBaseDBManager == null)
      _initTestBaseDBManagerProxy();
    return testBaseDBManager.additionner(arg0, arg1);
  }
  
  public java.lang.String supprimerTout(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException{
    if (testBaseDBManager == null)
      _initTestBaseDBManagerProxy();
    return testBaseDBManager.supprimerTout(arg0, arg1, arg2);
  }
  
  public java.lang.String createAuteur(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException{
    if (testBaseDBManager == null)
      _initTestBaseDBManagerProxy();
    return testBaseDBManager.createAuteur(arg0, arg1, arg2, arg3);
  }
  
  public java.lang.String createLivre(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException{
    if (testBaseDBManager == null)
      _initTestBaseDBManagerProxy();
    return testBaseDBManager.createLivre(arg0, arg1, arg2, arg3);
  }
  
  
}