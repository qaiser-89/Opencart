package com.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.dataProvider.DataReader;

public class RetryAnalyzer implements IRetryAnalyzer
{
	
	private int retryCount  = 0;
	private int maxRetryCount = Integer.parseInt(DataReader.readProperty("retryCount"));

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) 
		{
			retryCount++;
			return true;
			
		}

		return false;
	}
	
	

}
