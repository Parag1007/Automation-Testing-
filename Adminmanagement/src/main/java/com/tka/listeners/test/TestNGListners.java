package com.tka.listeners.test;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListners implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test started: \n" + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test passed: " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test failed: " + result.getName());
	}
	
	 @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        System.out.println("Test failed but within success percentage: " + result.getName());
	    }

}
