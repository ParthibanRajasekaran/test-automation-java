package com.testautomation.project.web.pages;

import com.testautomation.project.web.locators.HerukuappKeyPressPageLocator;

import static com.testautomation.utils.common.Logger.logInfo;
import static com.testautomation.utils.web.SeleniumUtils.getElementText;
import static com.testautomation.utils.web.SeleniumUtils.tryFindElement;
import static com.testautomation.utils.web.Waiters.waitUntilElementVisible;
import static org.testng.Assert.assertTrue;

public class HerokuappKeyPressPage extends HerukuappKeyPressPageLocator {

  public void verifyIfPageHeaderIsDisplayed() {
    waitUntilElementVisible(PAGE_HEADER);
    logInfo("Page header:" + getElementText(PAGE_HEADER) + " is displayed");
  }

  public String getResult() {
    return getElementText(RESULT);
  }

  public void sendKeyAndGetResult(String Key, String result) {
    tryFindElement(INPUT_FIELD).sendKeys(Key);
    logInfo(String.format("Result is %s", getResult()));
    assertTrue(getResult().contains(result));
  }
}
