package com.testautomation.project.web.pages;

import com.testautomation.project.web.locators.HerokuappCheckboxPageLocator;

import static com.testautomation.utils.common.Logger.logInfo;
import static com.testautomation.utils.web.SeleniumUtils.*;
import static com.testautomation.utils.web.Waiters.waitUntilElementVisible;

public class HerokuappCheckboxPage extends HerokuappCheckboxPageLocator {

  public void verifyIfPageHeaderIsDisplayed() {
    waitUntilElementVisible(PAGE_HEADER);
    logInfo("Page header:" + getElementText(PAGE_HEADER) + " is displayed");
  }

  public void verifyCheckboxLandingPage() {
    verifyIfPageHeaderIsDisplayed();
    waitUntilElementVisible(CHECKBOX_1);
    assertElementPresent(CHECKBOX_2);
    logInfo("Checkboxes displayed as expected");
  }

  public void checkOnCheckBox1() {
    if (!tryFindElement(CHECKBOX_1).isSelected()) {
      clickElement(CHECKBOX_1);
    }
    logInfo("Checkbox 1 is checked on");
  }

  public void checkOnCheckBox2() {
    if (!tryFindElement(CHECKBOX_2).isSelected()) {
      clickElement(CHECKBOX_2);
    }
    logInfo("Checkbox 2 is checked on");
  }

  public void unCheckCheckbox1() {
    if (tryFindElement(CHECKBOX_1).isSelected()) {
      clickElement(CHECKBOX_1);
    }
    logInfo("Checkbox 1 is unchecked");
  }

  public void unCheckCheckbox2() {
    if (tryFindElement(CHECKBOX_2).isSelected()) {
      clickElement(CHECKBOX_2);
    }
    logInfo("Checkbox 2 is unchecked");
  }
}
