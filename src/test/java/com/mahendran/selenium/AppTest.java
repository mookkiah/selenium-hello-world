package com.mahendran.selenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
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

public class AppTest {
  public static WebDriver driver;

  @Test
  public void testChrome() throws Exception {

    System.out.println("Chrome Test");
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();

    addLoggingPreference(capabilities);
    runTest(capabilities);
  }

  @Test
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
    String app = System.getProperty("app.home");

    if (useRemoteDriver == null) {
      initalizeLocalDriver(capabilities);
    } else {
      URL hub = null;
      String hubUrl = System.getProperty("hubUrl");

      try {
        hub = new URL(hubUrl);
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
      System.out.println("Using remote  $$$$$$$$$$$$$$$$$$$$$");
      driver = new RemoteWebDriver(hub, capabilities);
    }

    driver.get(app);

    try {
      Thread.sleep(2000);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Closing Browser");
    driver.close();
    driver.quit();
  }

  private void initalizeLocalDriver(DesiredCapabilities capabilities) throws Exception {
    String browser = (String) capabilities.getCapability(CapabilityType.BROWSER_NAME);
    if (browser.equalsIgnoreCase(BrowserType.IE)) {
      driver = new InternetExplorerDriver(capabilities);
    } else if (browser.equalsIgnoreCase(BrowserType.FIREFOX)) {
      driver = new FirefoxDriver(capabilities);
    } else if (browser.equalsIgnoreCase(BrowserType.CHROME)) {
      driver = new ChromeDriver(capabilities);
    }
    // Check if parameter passed as 'Edge'
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
