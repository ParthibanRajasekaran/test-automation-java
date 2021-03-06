package com.testautomation.utils.web;

import com.testautomation.utils.common.Logger;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.openqa.selenium.By.xpath;

@Slf4j
public class Waiters {

  static Wait<WebDriver> getFluentWait() {
    return getFluentWait(WebConstants.FLUENT_WAIT_TIMEOUT_SECONDS);
  }

  private static Wait<WebDriver> getFluentWait(long timeoutSeconds) {
    return new FluentWait<>(WebDriverHolder.getDriver())
        .withTimeout(Duration.ofSeconds(timeoutSeconds))
        .pollingEvery(Duration.ofMillis(WebConstants.FLUENT_WAIT_POLLING_INTERVAL_MILLISECONDS))
        .ignoring(NoSuchElementException.class);
  }

  /**
   * Wait until the element is displayed - waits on a polling basis until timeout.
   *
   * @param locator a valid xpath locator
   */
  @Step
  public static void waitUntilElementVisible(final String locator) {
    log.trace("Waiting for {} to be visible", locator);
    getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(xpath(locator)));
  }

  /**
   * Wait until the element is not displayed anymore - waits on a polling basis until timeout.
   *
   * @param locator a valid xpath locator
   */
  @Step
  public static void waitUntilElementInvisible(final String locator) {
    log.trace("Waiting for {} to be invisible", locator);
    getFluentWait().until(ExpectedConditions.invisibilityOfElementLocated(xpath(locator)));
  }

  /**
   * Wait until the element containing the text is displayed - waits on a polling basis until
   * timeout.
   *
   * @param locator a valid xpath locator
   * @param text Not null text we expect to be present on the webelement
   */
  @Step
  public static void waitUntilTextWillBePresent(final String locator, final String text) {
    log.trace("Waiting for text \"{}\" to be present", locator);
    getFluentWait().until(ExpectedConditions.textToBePresentInElementLocated(xpath(locator), text));
  }

  /**
   * Wait until the element containing the text is not displayed anymore - waits on a polling basis
   * until timeout.
   *
   * @param locator a valid xpath locator
   * @param text Some not null text which we expect not to be visible on the webelement
   */
  @Step
  public static void waitUntilTextWillBeInvisible(final String locator, final String text) {
    log.trace("Waiting for text \"{}\" to be invisible", locator);
    getFluentWait().until(ExpectedConditions.invisibilityOfElementWithText(xpath(locator), text));
  }

  /**
   * Wait until the element is clickable - waits on a polling basis until timeout.
   *
   * @param locator a valid xpath locator
   */
  @Step
  static void waitUntilElementClickable(final String locator) {
    log.trace("Waiting for element \"{}\" to be clickable", locator);
    getFluentWait().until(ExpectedConditions.elementToBeClickable(xpath(locator)));
  }

  /**
   * Wait until the element containing the value is displayed - waits on a polling basis until
   * timeout.
   *
   * @param locator a valid xpath locator
   * @param text Text for which we will search
   */
  @Step
  static void waitUntilValueWillBePresentInElement(final String locator, final String text) {
    log.trace("Waiting for text \"{}\" to be present in {}", text, locator);
    getFluentWait().until(ExpectedConditions.textToBePresentInElementValue(xpath(locator), text));
  }

  /**
   * Wait until the particular element is reloaded
   *
   * @param locator a valid xpath locator
   * @param timeout timeout limit in seconds
   */
  public static void waitUntilElementGoesStale(String locator, int timeout) {
    final WebElement webElement = SeleniumUtils.tryFindElement(locator);
    WebDriverWait wait = new WebDriverWait(WebDriverHolder.getDriver(), timeout);
    try {
      wait.pollingEvery(Duration.ofSeconds(5)).until(ExpectedConditions.stalenessOf(webElement));
      Logger.logInfo(String.format("Webelement %s reloaded successfully", locator));
    } catch (TimeoutException e) {
      Logger.logError(String.format("Page is not refreshed in %d seconds", timeout));
    }
  }

  /**
   * Wait until the element is displayed with awaitility with polling every 5 seconds
   *
   * @param locator A valid xpath locator
   * @param timeout timeout limit in seconds
   */
  public static void awaitUntilElementIsDisplayed(String locator, int timeout) {
    await(String.format("Wait for element: %s to be displayed", locator))
        .atMost(timeout, TimeUnit.SECONDS)
        .pollInterval(Duration.ofSeconds(5))
        .ignoreExceptions()
        .until(SeleniumUtils.tryFindElement(locator)::isDisplayed);
  }

  /**
   * Wait until the element is enabled with awaitility with polling every 5 seconds
   *
   * @param locator A valid xpath locator
   * @param timeout timeout limit in seconds
   */
  public static void awaitUntilElementIsEnabled(String locator, int timeout) {
    await(String.format("Wait for element: %s to be enabled", locator))
        .atMost(timeout, TimeUnit.SECONDS)
        .pollInterval(Duration.ofSeconds(5))
        .ignoreExceptions()
        .until(SeleniumUtils.tryFindElement(locator)::isEnabled);
  }

  /**
   * Wait until the element is selected with awaitility with polling every 5 seconds
   *
   * @param locator A valid xpath locator
   * @param timeout timeout limit in seconds
   */
  public static void awaitUntilElementIsSelected(String locator, int timeout) {
    await(String.format("Wait for element: %s to be selected", locator))
        .atMost(timeout, TimeUnit.SECONDS)
        .pollInterval(Duration.ofSeconds(5))
        .ignoreExceptions()
        .until(SeleniumUtils.tryFindElement(locator)::isSelected);
  }

  /**
   * To wait until the specified status code is returned with a polling interval of 2 seconds
   *
   * @param rawRes Any instance of {@code Response} which will be converted to json.
   * @param statusCode of the response
   * @param timeout to throw the exception
   */
  public static void waitUntilStatusCodeIsReceived(Response rawRes, int statusCode, int timeout) {
    await()
        .atMost(timeout, TimeUnit.SECONDS)
        .with()
        .pollInterval(2, TimeUnit.SECONDS)
        .until(() -> rawRes.statusCode() == statusCode);
  }
}
