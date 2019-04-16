package com.mahendran.selenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;  
 public class AppTest  
 {  
  public static WebDriver driver;  

  @Test
  public  void test()
  {  
   
   System.out.println("starting selenium web driver");  
   DesiredCapabilities capabilities = DesiredCapabilities.chrome();
   LoggingPreferences logPrefs = new LoggingPreferences();
   logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
   logPrefs.enable(LogType.PROFILER, Level.ALL);
   logPrefs.enable(LogType.BROWSER, Level.ALL);
   logPrefs.enable(LogType.CLIENT, Level.ALL);
   logPrefs.enable(LogType.DRIVER, Level.ALL);
   logPrefs.enable(LogType.SERVER, Level.ALL);
   capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
   String useRemoteDriver = System.getProperty("useRemoteDriver");

   URL hub = null;
   String hubUrl = System.getProperty("hubUrl");
   String app = System.getProperty("app.home");
   try {
     hub = new URL(hubUrl);
   } catch (MalformedURLException e) {
     e.printStackTrace();
   }
   if (useRemoteDriver == null) {
     System.out.println("Using local chrome $$$$$$$$$$$$$$$$$$$$$");
     driver = new ChromeDriver(capabilities);
   } else {
     
     System.out.println("Using remote  $$$$$$$$$$$$$$$$$$$$$");
     driver = new RemoteWebDriver(hub, capabilities);
   }
   
   driver.get(app);
   
   try{  
    Thread.sleep(2000);  
   }catch(Exception e){
     e.printStackTrace();
   }  
     System.out.println("Closing Browser");
     driver.close();  
     driver.quit();  
  }  
 }
