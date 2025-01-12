package com.dataProvider;

import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataReader
{
   public static String readProperty(String key)
   {
       Properties property=new Properties();
       try {
          property.load(new FileInputStream(new File("./Config.properties")));
       } catch (IOException e)
       {
           Reporter.log("Issue with loading the config File check DataReader Class!!");
       }
       String value = property.getProperty(key);

       return value;
   }




}
