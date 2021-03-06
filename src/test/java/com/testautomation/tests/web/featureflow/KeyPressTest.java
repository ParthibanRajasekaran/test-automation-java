package com.testautomation.tests.web.featureflow;

import com.testautomation.project.common.SampleDataProvider;
import com.testautomation.project.web.pages.HerokuappKeyPressPage;
import com.testautomation.project.web.pages.HerokuappLandingPage;
import com.testautomation.tests.web.common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static com.testautomation.project.common.ConfigFileReaderUtils.getValueFromEnvironmentFile;
import static com.testautomation.project.common.Groups.INPUT_FIELD;
import static com.testautomation.utils.web.SeleniumUtils.openPage;

@Features("Herokuapp")
@Stories("Herokuapp Key Press")
public class KeyPressTest extends BaseTest {

  private HerokuappLandingPage herokuappLandingPage;
  private HerokuappKeyPressPage herokuappKeyPressPage;

  @BeforeMethod(alwaysRun = true)
  public void setup() {
    herokuappLandingPage = new HerokuappLandingPage();
    herokuappKeyPressPage = new HerokuappKeyPressPage();

    final String url = getValueFromEnvironmentFile("host");
    openPage(url);
    herokuappLandingPage.verifyIfPageHeaderIsDisplayed();
    herokuappLandingPage.navigateToSubPage("Key Presses");
    herokuappKeyPressPage.verifyIfPageHeaderIsDisplayed();
  }

  @Test(
      dataProvider = "testData",
      dataProviderClass = SampleDataProvider.class,
      groups = {INPUT_FIELD})
  public void keyPressTest(String Key, String Result) {
    herokuappKeyPressPage.sendKeyAndGetResult(Key, Result);
  }
}
