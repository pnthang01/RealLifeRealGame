package com.rlrg.utillities.service;


public class ReadWebServiceObserver implements ReadWebServiceListener{
	
	public ReadWebServiceObserver(BaseWebServiceReader<?> source){
		source.addReadListener(this);
	}

	public void readWebService(ReadWebServiceEvent event) {
		//TODO: here is where you add code to process those json.
	}
}

