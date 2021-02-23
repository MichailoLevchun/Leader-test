/*
* Copyright 2017 The Red Flag Group (http://www.redflaggroup.com)
*
* You may not use this file except in compliance with the terms of your service agreement with The Red Flag Group
*
*/

package com.psa.library;

import java.util.HashMap;
import java.util.Map;

import com.psa.pages.ObjectReference;


public class Results extends ObjectReference {

	private static Map<Integer, String> failureMessage = new HashMap<Integer, String>();
	public static String passedMessage;
	
	static String getDescription() {
		return new TestInitReference().getTestDescription();
	}
	
	public static void setFailureMsg(String value) {
		failureMessage.put((int) (Thread.currentThread().getId()), value);
    }
	
	public static String getFailureMsg() {
        return failureMessage.get((int) (Thread.currentThread().getId()));
    }
	
    public void pass(String value) {
    	String msg = "[PASSED] "+ value;
        Log.info(msg);
        passedMessage = value;
    }
    
    public void pass() {
    	pass(getDescription()); 
    }

    public void fail(String value) {
    	String msg = "[FAILED] " + value;
        Log.error(msg);
        setFailureMsg(msg);
    }
    
    public void fail(){
    	fail(getDescription()); 
    }
    
    public void skip(String value) {
    	String msg = "[SKIPPED] "+ value;
        Log.warn(msg);
    }
    
    public void skip() {
   		skip(getDescription());
    }
    
    public void info(String input) {	
        Log.info(input);
    }

}
