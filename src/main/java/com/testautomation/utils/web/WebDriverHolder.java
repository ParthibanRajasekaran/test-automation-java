package com.testautomation.utils.web;

import com.testautomation.utils.common.Logger;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static com.testautomation.utils.common.Logger.logError;
import static com.testautomation.utils.web.BrowserFactory.getBrowser;

public class WebDriverHolder {

  private WebDriverHolder() {}

  private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

  public static void setDriver(String browserName, String testName) {
    try {
      driver.set(getBrowser(browserName, testName));
      Logger.logInfo(String.format("Starting browser : %s", browserName));
    } catch (MalformedURLException e) {
      logError("Unable to open: " + browserName + " because of " + e);
    }
    getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
  }

  public static WebDriver getDriver() {
    return driver.get();
  }

  public static void tearDownBrowser() {
    Logger.logInfo("Closing Browser");
    if (getDriver() != null) {
      getDriver().quit();
      driver.remove();
    }
  }
}
