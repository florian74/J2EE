package com.testBase.soapAdapter;

import com.testBase.fonctionWebService.TestBaseDBManager;
import com.testBase.fonctionWebService.jaxws.*;

public class TestBaseSoapAdapter {

	TestBaseDBManager tbdbm = new TestBaseDBManager();
	public AfficherBaseResponse adapterAfficherBase( AfficherBase request)
	{
		
		
		tbdbm.afficherBase();
		AfficherBaseResponse resp = new AfficherBaseResponse();
		
		
		
		return resp;
		
	}
	
	
}
