package com.mahendran.selenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.Platform;
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

public class SampleTest {

  public WebDriver driver = null;

  @Test
  @DisplayName("testChrome()")
  public void testChrome(TestInfo testInfo) throws Exception {
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    addGeneralCapabilities(testInfo, capabilities);
    addSauceLabsCapabilities(capabilities);
    addLoggingPreference(capabilities);
    runTest(capabilities);
  }

  @Test
  @DisplayName("testSafari()")
  public void testSafari(TestInfo testInfo) throws Exception {
    DesiredCapabilities capabilities = DesiredCapabilities.safari();
    addGeneralCapabilities(testInfo, capabilities);
    addLoggingPreference(capabilities);
    addSauceLabsCapabilities(capabilities);
    runTest(capabilities);
  }

  @Test
  @DisplayName("testFirefox()")
  public void testFirefox(TestInfo testInfo) throws Exception {
    DesiredCapabilities capabilities = DesiredCapabilities.firefox();
    addGeneralCapabilities(testInfo, capabilities);
    addLoggingPreference(capabilities);
    addSauceLabsCapabilities(capabilities);
    runTest(capabilities);
  }

  @Test
  @DisplayName("testEdge()")
  public void testEdge(TestInfo testInfo) throws Exception {
    DesiredCapabilities capabilities = DesiredCapabilities.edge();
    capabilities.setCapability(CapabilityType.PLATFORM, Platform.WIN10);
    addGeneralCapabilities(testInfo, capabilities);
    addLoggingPreference(capabilities);
    addSauceLabsCapabilities(capabilities);
    runTest(capabilities);
  }

  @Test
  @DisplayName("testInternetExplorer()")
  public void testInternetExplorer(TestInfo testInfo) throws Exception {
    DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
    addGeneralCapabilities(testInfo, capabilities);
    addLoggingPreference(capabilities);
    addSauceLabsCapabilities(capabilities);
    capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
    capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
    capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, "accept");
    capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
    runTest(capabilities);

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
    } else if (browser.equalsIgnoreCase(BrowserType.EDGE)) {
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

  // https://wiki.saucelabs.com/display/DOCS/Java+Test+Setup+Example
  private void addSauceLabsCapabilities(DesiredCapabilities capabilities) {
    String sauceUserName = System.getenv("SAUCE_USERNAME");
    String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");

    // set your user name and access key to run tests in Sauce
    capabilities.setCapability("username", sauceUserName);

    // set your sauce labs access key
    capabilities.setCapability("accessKey", sauceAccessKey);
  }

  private void addGeneralCapabilities(TestInfo testInfo, DesiredCapabilities capabilities) {
    // set your test case name so that it shows up in Sauce Labs
    capabilities.setCapability("name", testInfo.getDisplayName());
  }

  // Source: https://dzone.com/articles/selenium-with-java-google-search
  private void runTest(DesiredCapabilities capabilities) throws Exception {

    initializeDriver(capabilities);
    System.out.println("Initialized the Driver");
    System.out.println("Entering google url in the browser");
    driver.get("https://google.com");
    System.out.println("Typing Selenium in search input and hitting Enter");

    driver.findElement(By.name("q")).sendKeys("Selenium" + Keys.ENTER);
    Thread.sleep(2000); // TODO: Added to support firefox. But need to change to wait until page loaded with result.

    // get the number of pages
    int size = driver.findElements(By.cssSelector("tr[valign='top'] > td")).size();
    System.out.println("Number of result page: " + size);
    for (int j = 1; j < size; j++) {
      if (j > 1) {// we don't need to navigate to the first page
        System.out.println("Click page " + j);
        driver.findElement(By.cssSelector("a[aria-label='Page " + j + "']")).click(); // navigate to page number j
        Thread.sleep(2000); 
      }
      String pagesearch = driver.getCurrentUrl();
      List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));
      System.out.println(findElements.size());
      for (int i = 0; i < findElements.size(); i++) {
        findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));
        findElements.get(i).click();
        driver.navigate().to(pagesearch);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        System.out.println("Scrolling down...");
        // Scroll vertically downward by 250 pixels
        jse.executeScript("window.scrollBy(0,250)", "");
        
        Thread.sleep(2000); 
      }
    }

    System.out.println("Closing Browser");
    driver.close();
    try {
      driver.quit();
    } catch (NoSuchSessionException e) {
      System.out.println("Quit is not required in this browser");
    }

  }

  private void initializeDriver(DesiredCapabilities capabilities) throws Exception, MalformedURLException {
    // For SauceLab => -DhubUrl=https://ondemand.saucelabs.com:443/wd/hub
    // For local grid => -DhubUrl=http://localhost:4444/wd/hub
    String hubUrl = System.getProperty("hubUrl");
    if (hubUrl == null) {
      initalizeLocalDriver(capabilities);
    } else {
      driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
    }
  }
}
