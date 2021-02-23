/*
* Copyright 2017 The Red Flag Group (http://www.redflaggroup.com)
*
* You may not use this file except in compliance with the terms of your service agreement with The Red Flag Group
*
*/

package com.psa.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

public class ReadXmlData {
    public String value;
    private ResourceBundle _prop = ResourceBundle.getBundle("dev");

    public String data(String node) {
        try {
        	// File testXMLDataFile = new File("/usr/automation/testdata.xml");
            // String mode = _prop.getString("mode");
            //
        	// if(mode.equals("standalone")){
        	// 	testXMLDataFile = new File("testdata.xml");
        	// }
        	// else if(testXMLDataFile == null || !testXMLDataFile.exists()){
            File	testXMLDataFile = new File("src/test/resources/testdata.xml");
            // }
            FileInputStream fileInput = new FileInputStream(testXMLDataFile);
            Properties properties = new Properties();
            properties.loadFromXML(fileInput);
            fileInput.close();
			Enumeration enuKeys = properties.keys();
            while (enuKeys.hasMoreElements()) {
                if (((String) enuKeys.nextElement()).contains(node)) {
                    value = properties.getProperty(node);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}