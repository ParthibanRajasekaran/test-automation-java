package com.testautomation.utils.web;

import com.testautomation.utils.common.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.testng.AllureTestListener;

import static com.testautomation.utils.common.Logger.logError;
import static com.testautomation.utils.common.Logger.logInfo;

public class SeleniumListener extends AllureTestListener {

  @Override
  public void onStart(ITestContext arg0) {
    logInfo("Test suite: " + arg0.getName());
  }

  @Override
  public void onTestStart(ITestResult arg0) {
    logInfo("Starting test: " + arg0.getMethod());
  }

  @Override
  public void onTestSuccess(ITestResult tr) {
    logInfo(tr.getName() + " \n--- SUCCESS ---\n");
  }

  @Step("Step on Failure")
  @Override
  public void onTestFailure(ITestResult tr) {
    logError(tr.getName() + " --- FAILED --- ");

    // saveTextLog(tr.getName());
    logInfo(String.format("%s failed and screenshot taken", tr.getName()));
    Logger.saveScreenshotPNG();

    Throwable ex = tr.getThrowable();
    if (ex != null) {
      String cause = ex.toString();
      Logger.saveTextLog(cause);
      logError(cause + "\n");
    }
  }

  @Override
  public void onTestSkipped(ITestResult tr) {
    logInfo(tr.getName() + " --- SKIPPED ---\n");
    Throwable ex = tr.getThrowable();
    if (ex != null) {
      String cause = ex.toString();
      logError(cause);
    }
  }
}
