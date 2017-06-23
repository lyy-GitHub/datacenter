package util;

import java.io.File;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ProperUtil {
private static File file=new File(System.getProperty("user.dir"),"config/properites/config.properties");
public static String getParamVale(String key)
{
	String value=null;
	try {
		
		Configuration config=new PropertiesConfiguration(file);
		value=config.getString(key);
		config.clear();
	
	} catch (ConfigurationException e) {
		e.printStackTrace();
	}
	return value;
}
public static void setParm(String key,Object value)
{
	PropertiesConfiguration config;
	try {
		
		config=new PropertiesConfiguration(file);
		if(config.containsKey(key))
		{
			config.setProperty(key, value);
		}
		else
		{
			config.addProperty(key, value);
		}
		config.save();
		config.clear();
		
	} catch (ConfigurationException e) {
		e.printStackTrace();
	}
	
}
public static void main(String[] args) {
	String username=getParamVale("password");
System.out.println(username);	
	
}
}
