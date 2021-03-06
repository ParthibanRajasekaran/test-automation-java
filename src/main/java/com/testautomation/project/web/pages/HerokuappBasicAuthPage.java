package com.testautomation.project.web.pages;

import com.testautomation.project.web.locators.HerukuappBasicAuthPageLocator;

import java.security.GeneralSecurityException;

import static com.testautomation.project.common.ConfigFileReaderUtils.getValueFromEnvironmentFile;
import static com.testautomation.project.common.PropertyFileReaderUtils.getValueFromTestDataFile;
import static com.testautomation.utils.common.Logger.logInfo;
import static com.testautomation.utils.web.SeleniumUtils.assertElementPresent;
import static com.testautomation.utils.web.SeleniumUtils.getElementText;
import static com.testautomation.utils.web.Waiters.waitUntilElementVisible;

public class HerokuappBasicAuthPage extends HerukuappBasicAuthPageLocator {

  public String generateEncodedURL() throws GeneralSecurityException {

    return getValueFromEnvironmentFile("protocol")
        + ":"
        + "//"
        + getValueFromTestDataFile("USERNAME")
        + ":"
        + getValueFromTestDataFile("PASSWORD")
        + "@"
        + getValueFromEnvironmentFile("host_url")
        + "/basic_auth";
  }

  public void verifyIfPageHeaderIsDisplayed() {
    waitUntilElementVisible(PAGE_HEADER);
    logInfo("Page header:" + getElementText(PAGE_HEADER) + " is displayed");
  }

  public void verifyIfSuccessMessageIsDisplayed() {
    assertElementPresent(SUCCESS_MESSAGE);
    logInfo("Message: " + getElementText(SUCCESS_MESSAGE) + " is displayed on the landing page");
  }

  public void verifyIfPageFooterIsPresent() {
    waitUntilElementVisible(PAGE_FOOTER);
    logInfo("Page footer contains the link:" + getElementText(PAGE_FOOTER_LINK));
  }
}
