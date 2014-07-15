package com.reminder.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class Configuration {

    private static final Logger LOGGER= LoggerFactory.getLogger(Configuration.class);

    private Properties properties = new Properties();

    private static Configuration configuration=new Configuration();

    private Configuration() {
        try {
            properties.load(this.getClass().getResourceAsStream("/reminder.properties"));
        } catch ( Exception e ) {
            LOGGER.error("Unable to load configuration",e);
        }
    }

    public static final Configuration getInstance () {
        return configuration;
    }

    public  String getString (String propertyName) {
        return properties.getProperty(propertyName);
    }

    public  int getInt (String propertyName) {
        String config= properties.getProperty(propertyName);
        return config==null?0:Integer.valueOf(config);
    }

    public boolean getBoolean(String propertyName){
        String config= properties.getProperty(propertyName);
        if(config==null){
            return false;
        }
        if(config.toLowerCase().equals("true")){
            return true;
        }else{
            return false;
        }

    }
    public static void main(String[] args) {
		System.out.println(Configuration.getInstance().getString("request_url"));
	}
}
