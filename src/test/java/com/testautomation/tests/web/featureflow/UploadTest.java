package com.testautomation.tests.web.featureflow;

import com.testautomation.project.web.pages.HerokuappLandingPage;
import com.testautomation.project.web.pages.HerokuappUploadPage;
import com.testautomation.tests.web.common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.testautomation.project.common.ConfigFileReaderUtils.getValueFromEnvironmentFile;
import static com.testautomation.utils.web.SeleniumUtils.openPage;

@Features("Herokuapp")
@Stories("Herokuapp upload")
public class UploadTest extends BaseTest {

  private HerokuappLandingPage herokuappLandingPage;
  private HerokuappUploadPage herokuappUploadPage;

  @BeforeMethod(alwaysRun = true)
  public void setup() {
    herokuappLandingPage = new HerokuappLandingPage();
    herokuappUploadPage = new HerokuappUploadPage();

    openPage(getValueFromEnvironmentFile("host"));
    herokuappLandingPage.verifyIfPageHeaderIsDisplayed();
    herokuappLandingPage.navigateToSubPage("File Upload");
    herokuappUploadPage.verifyIfPageHeaderIsDisplayed();
  }

  @Test
  @Description("This is a sample Test 4")
  @Severity(SeverityLevel.BLOCKER)
  public void uploadTest() {
    herokuappUploadPage.uploadImageToField("//input[@id='file-upload']", "sampleJPEG.jpeg");
    herokuappUploadPage.clickUploadButton();
  }
}
