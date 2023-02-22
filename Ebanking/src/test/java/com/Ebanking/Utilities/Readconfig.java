package com.Ebanking.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Readconfig {
	
	Properties Pro;
	
	public  Readconfig() 
	{
		File src=new File("./configarations/config.properties");
		try {
			FileInputStream fis=new FileInputStream(src);
			Pro=new Properties();
			Pro.load(fis);
			}
		catch(Exception e){
			System.out.println("Exception is" +e.getMessage());
			
		}
		
	}
	public String getApplicationUrl() {
		String url=Pro.getProperty("baseUrl");
		return url;
	}
	public String getuserName() {
		String userName=Pro.getProperty("userName");
		return userName;
	}
	public String getpassword() {
		String password=Pro.getProperty("password");
		return password;
	}
	public String getchropath() {
		String chropath=Pro.getProperty("chropath");
		return chropath;
	}
	public String getfirefoxpath() {
		String firefoxpath=Pro.getProperty("firefoxpath");
		return firefoxpath;
	}
	public String getIEpath() {
		String iepath=Pro.getProperty("iepath");
		return iepath;
		
	}
	
	
}
