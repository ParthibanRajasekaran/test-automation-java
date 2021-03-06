package com.testautomation.tests.web.featureflow;

import com.testautomation.project.web.pages.HerokuappBasicAuthPage;
import com.testautomation.tests.web.common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.security.GeneralSecurityException;

import static com.testautomation.project.common.Groups.AUTH;
import static com.testautomation.utils.web.SeleniumUtils.openPage;

@Features("Herokuapp")
@Stories("Herokuapp Basic Authorization")
public class BasicAuthTest extends BaseTest {

  private HerokuappBasicAuthPage herokuappBasicAuthPage;

  private String url;

  @BeforeMethod(alwaysRun = true)
  public void setup() throws GeneralSecurityException {
    herokuappBasicAuthPage = new HerokuappBasicAuthPage();

    url = herokuappBasicAuthPage.generateEncodedURL();
  }

  @Test(groups = {AUTH})
  public void navigateToEncodedURL() {
    openPage(url);
    herokuappBasicAuthPage.verifyIfPageHeaderIsDisplayed();
    herokuappBasicAuthPage.verifyIfSuccessMessageIsDisplayed();
    herokuappBasicAuthPage.verifyIfPageFooterIsPresent();
  }
}
