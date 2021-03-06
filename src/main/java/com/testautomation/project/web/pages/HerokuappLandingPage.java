package com.testautomation.project.web.pages;

import com.testautomation.project.web.locators.HerukuappLandingPageLocator;

import static com.testautomation.utils.common.Logger.logInfo;
import static com.testautomation.utils.web.SeleniumUtils.*;
import static com.testautomation.utils.web.Waiters.waitUntilElementVisible;

public class HerokuappLandingPage extends HerukuappLandingPageLocator {

  public void navigateToSubPage(String subPageName) {
    final String subPageLink = "//li/a[contains(text(),'" + subPageName + "')]";
    assertElementPresent(subPageLink);
    clickElement(subPageLink);
  }

  public void verifyIfPageHeaderIsDisplayed() {
    waitUntilElementVisible(PAGE_HEADER);
    logInfo("Page header:" + getElementText(PAGE_HEADER) + " is displayed");
    waitUntilElementVisible(PAGE_SUB_HEADER);
    logInfo("Sub header:" + getElementText(PAGE_SUB_HEADER) + " is displayed");
  }
}
