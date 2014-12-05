/**
 * TestBaseDBManager.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.testBase.fonctionWebService;

public interface TestBaseDBManager extends java.rmi.Remote {
    public void createStubAuteur() throws java.rmi.RemoteException;
    public java.lang.String modifierTout(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException;
    public java.lang.String[] afficher(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public int additionner(int arg0, int arg1) throws java.rmi.RemoteException;
    public java.lang.String supprimerTout(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException;
    public java.lang.String createAuteur(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException;
    public java.lang.String createLivre(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.rmi.RemoteException;
}
