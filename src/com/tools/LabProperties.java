package com.tools;

import java.io.IOException;
import java.util.Properties;
 
public class LabProperties {
	static String propertiesFileName="/com/config.properties";
	static Properties p=null;
	static{
		p = new Properties();
		try {
			p.load(LabProperties.class.getClassLoader().getResourceAsStream(propertiesFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
	public static String getValue(String key){
		String value=p.getProperty(key);
		return value;	
	}
}
