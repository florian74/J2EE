/**
 * TestBaseDBManagerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.testBase.fonctionWebService;

public class TestBaseDBManagerServiceLocator extends org.apache.axis.client.Service implements com.testBase.fonctionWebService.TestBaseDBManagerService {

    public TestBaseDBManagerServiceLocator() {
    }


    public TestBaseDBManagerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TestBaseDBManagerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TestBaseDBManagerPort
    private java.lang.String TestBaseDBManagerPort_address = "http://testeclipse17.appspot.com/TestBaseDBManagerService";

    public java.lang.String getTestBaseDBManagerPortAddress() {
        return TestBaseDBManagerPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TestBaseDBManagerPortWSDDServiceName = "TestBaseDBManagerPort";

    public java.lang.String getTestBaseDBManagerPortWSDDServiceName() {
        return TestBaseDBManagerPortWSDDServiceName;
    }

    public void setTestBaseDBManagerPortWSDDServiceName(java.lang.String name) {
        TestBaseDBManagerPortWSDDServiceName = name;
    }

    public com.testBase.fonctionWebService.TestBaseDBManager getTestBaseDBManagerPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TestBaseDBManagerPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTestBaseDBManagerPort(endpoint);
    }

    public com.testBase.fonctionWebService.TestBaseDBManager getTestBaseDBManagerPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.testBase.fonctionWebService.TestBaseDBManagerPortBindingStub _stub = new com.testBase.fonctionWebService.TestBaseDBManagerPortBindingStub(portAddress, this);
            _stub.setPortName(getTestBaseDBManagerPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTestBaseDBManagerPortEndpointAddress(java.lang.String address) {
        TestBaseDBManagerPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.testBase.fonctionWebService.TestBaseDBManager.class.isAssignableFrom(serviceEndpointInterface)) {
                com.testBase.fonctionWebService.TestBaseDBManagerPortBindingStub _stub = new com.testBase.fonctionWebService.TestBaseDBManagerPortBindingStub(new java.net.URL(TestBaseDBManagerPort_address), this);
                _stub.setPortName(getTestBaseDBManagerPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("TestBaseDBManagerPort".equals(inputPortName)) {
            return getTestBaseDBManagerPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://fonctionWebService.testBase.com/", "TestBaseDBManagerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://fonctionWebService.testBase.com/", "TestBaseDBManagerPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TestBaseDBManagerPort".equals(portName)) {
            setTestBaseDBManagerPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
