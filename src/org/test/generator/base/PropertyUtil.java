package org.test.generator.base;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertyUtil {

  public static void main(String[] args) {
	  PropertyUtil app = new PropertyUtil();
	String fileName="config.properties";
	InputStream input=app.getClass().getClassLoader().getResourceAsStream(fileName);
	app.prop(input);
  }

  
  public static void prop(InputStream input){
	  Properties prop = new Properties();
		try {
			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("database"));
			System.out.println(prop.getProperty("dbuser"));
			System.out.println(prop.getProperty("dbpassword"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

  }
  
  public static void printThemAll(InputStream inputStream,String configFile) {

	Properties prop = new Properties();
	InputStream input = null;

	try {

		String filename = "config.properties";
		//input = getClass().getClassLoader().getResourceAsStream(filename);
		if (input == null) {
			System.out.println("Sorry, unable to find " + filename);
			return;
		}

		prop.load(input);

		Enumeration<?> e = prop.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = prop.getProperty(key);
			System.out.println("Key : " + key + ", Value : " + value);
		}

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

  }

}