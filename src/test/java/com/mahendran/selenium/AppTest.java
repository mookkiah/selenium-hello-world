package com.mahendran.selenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class AppTest {
  public  WebDriver driver;

  @Test
  public void testChrome() throws Exception {

    System.out.println("Chrome Test");
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();

    addLoggingPreference(capabilities);
    runTest(capabilities);
  }
  
  @Test
  @EnabledOnOs(OS.MAC)
  public void testSafari() throws Exception {

    System.out.println("Safari Test");
    DesiredCapabilities capabilities = DesiredCapabilities.safari();

    addLoggingPreference(capabilities);
    runTest(capabilities);
  }

  @Test
  @EnabledOnOs(OS.WINDOWS)
  public void testInternetExplorer() throws Exception {

    System.out.println("IE Test");
    DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
    addLoggingPreference(capabilities);
    capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
    capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
    capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, "accept");
    capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
    runTest(capabilities);

  }

  private void runTest(DesiredCapabilities capabilities) throws Exception {
    String useRemoteDriver = System.getProperty("useRemoteDriver");
    if (useRemoteDriver == null) {
      initalizeLocalDriver(capabilities);
    } else {
      URL hub = null;
      String hubUrl = System.getProperty("hubUrl", "http://localhost:4444/wd/hub");

      try {
        hub = new URL(hubUrl);
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
      driver = new RemoteWebDriver(hub, capabilities);
    }

    driver.get("https://google.com");
    
    driver.findElement(By.name("q")).sendKeys("Selenium"+Keys.ENTER);
    
 // get the number of pages
    int size = driver.findElements(By.cssSelector("[valign='top'] > td")).size();
    for(int j = 1 ; j < size ; j++) {
        if (j > 1) {// we don't need to navigate to the first page
            driver.findElement(By.cssSelector("[aria-label='Page " + j + "']")).click(); // navigate to page number j
        }
        String pagesearch = driver.getCurrentUrl();
        List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));
        System.out.println(findElements.size());
        for(int i=0;i<findElements.size();i++){
            findElements= driver.findElements(By.xpath("//*[@id='rso']//h3/a"));                
            findElements.get(i).click(); 
            driver.navigate().to(pagesearch);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            //Scroll vertically downward by 250 pixels
            jse.executeScript("window.scrollBy(0,250)", "");
        }
    }

    try {
      Thread.sleep(2000);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Closing Browser");
    driver.close();
    try {
      driver.quit();
    }catch (NoSuchSessionException e) {
      System.out.println("Quit is not required in this browser");
    }
    
  }

  private void initalizeLocalDriver(DesiredCapabilities capabilities) throws Exception {
    String browser = (String) capabilities.getCapability(CapabilityType.BROWSER_NAME);
    if (browser.equalsIgnoreCase(BrowserType.IE)) {
      driver = new InternetExplorerDriver(capabilities);
    } else if (browser.equalsIgnoreCase(BrowserType.FIREFOX)) {
      driver = new FirefoxDriver(capabilities);
    } else if (browser.equalsIgnoreCase(BrowserType.CHROME)) {
      driver = new ChromeDriver(capabilities);
    } else if (browser.equalsIgnoreCase(BrowserType.SAFARI)) {
      driver = new SafariDriver(capabilities);
    }
    else if (browser.equalsIgnoreCase(BrowserType.EDGE)) {
      driver = new EdgeDriver(capabilities);
    } else {
      // If no browser passed throw exception
      throw new Exception("Browser is not correct");
    }
  }

  private void addLoggingPreference(DesiredCapabilities capabilities) {
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
    logPrefs.enable(LogType.PROFILER, Level.ALL);
    logPrefs.enable(LogType.BROWSER, Level.ALL);
    logPrefs.enable(LogType.CLIENT, Level.ALL);
    logPrefs.enable(LogType.DRIVER, Level.ALL);
    logPrefs.enable(LogType.SERVER, Level.ALL);
    capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
  }
}
