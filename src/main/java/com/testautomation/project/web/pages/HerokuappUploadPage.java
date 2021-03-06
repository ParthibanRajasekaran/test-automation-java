package com.testautomation.project.web.pages;

import com.testautomation.project.web.locators.HerukuappUploadPageLocator;
import com.testautomation.utils.web.SeleniumUtils;

import static com.testautomation.utils.common.Logger.logInfo;
import static com.testautomation.utils.web.SeleniumUtils.clickElement;
import static com.testautomation.utils.web.SeleniumUtils.getElementText;
import static com.testautomation.utils.web.Waiters.waitUntilElementVisible;

public class HerokuappUploadPage extends HerukuappUploadPageLocator {

  public void verifyIfPageHeaderIsDisplayed() {
    waitUntilElementVisible(PAGE_HEADER);
    logInfo("Page header:" + getElementText(PAGE_HEADER) + " is displayed");
  }

  public void uploadImageToField(String locator, String fileName) {
    SeleniumUtils.uploadImage(locator, fileName);
  }

  public void clickUploadButton() {
    clickElement(UPLOAD_BUTTON);
  }

  public String getUploadedFileName() {
    return getElementText(UPLOADED_FILE_NAME);
  }
}
