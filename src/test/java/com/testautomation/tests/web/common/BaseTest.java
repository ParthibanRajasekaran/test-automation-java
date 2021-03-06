package com.testautomation.tests.web.common;

import com.testautomation.utils.web.SeleniumListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import static com.testautomation.utils.common.Logger.logInfo;
import static com.testautomation.utils.web.WebDriverHolder.setDriver;
import static com.testautomation.utils.web.WebDriverHolder.tearDownBrowser;

@Listeners(SeleniumListener.class)
public class BaseTest {

  @Parameters("browserName")
  @BeforeMethod(alwaysRun = true)
  protected static void setUpBrowser(@Optional("chrome") String browserName, ITestContext result)
      throws UnknownHostException {
    logInfo(String.format("Tests running on host: %s", InetAddress.getLocalHost().getHostName()));
    setDriver(browserName, result.getName());
  }

  @AfterMethod(alwaysRun = true)
  protected void tearDown(ITestResult testResult) {
    tearDownBrowser();
  }
}
