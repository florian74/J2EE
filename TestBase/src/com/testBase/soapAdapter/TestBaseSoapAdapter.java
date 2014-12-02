package com.testBase.soapAdapter;

import java.util.ArrayList;
import java.util.List;

import com.testBase.fonctionWebService.TestBaseDBManager;
import com.testBase.fonctionWebService.jaxws.*;

public class TestBaseSoapAdapter {

	TestBaseDBManager tbdbm = new TestBaseDBManager();
	public AfficherBaseResponse adapterAfficherBase( AfficherBase request)
	{
		
		
		
		AfficherBaseResponse resp = new AfficherBaseResponse();
		//resp.setReturn(tbdbm.afficherBase());
		List<String> str = new ArrayList<String>();
		str.add("foo");
		resp.setReturn("foo");
		return resp;
		
	}
	
	public AdditionnerResponse adapterAdditionner( Additionner request)
	{
		
		int i = request.getArg0();
		int j = request.getArg1();
		
		AdditionnerResponse resp = new AdditionnerResponse();
		//resp.setReturn(tbdbm.afficherBase());
		

		resp.setReturn(tbdbm.additionner(i, j));
		return resp;
		
	}
	
	
}
