package com.testautomation.project.api.utils;

import io.restassured.response.Response;

import static com.testautomation.utils.api.RestAssuredUtils.*;
import static com.testautomation.utils.common.Logger.logInfo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class CustomFunctions {

  public static String getOrdersUrl(Response rawRes) {
    return convertRawToJson(rawRes).get("orders_url");
  }

  public static String getCustomerId(Response rawRes) {
    String customerId = convertRawToJson(rawRes).get("customer_url");
    customerId = customerId.replaceAll("\\D+", "");
    logInfo(String.format("Customer created and Customer Id is %s", customerId));
    return customerId;
  }

  public static String getCustomerFirstname(Response rawRes) {
    String customerFirstname = convertRawToJson(rawRes).get("firstname");
    logInfo(String.format("Customer created and Customer first name is %s", customerFirstname));
    return customerFirstname;
  }

  public static String getCustomerLastname(Response rawRes) {
    String customerLastname = convertRawToJson(rawRes).get("lastname");
    logInfo(String.format("Customer created and Customer last name is %s", customerLastname));
    return customerLastname;
  }

  public static void verifyCustomerDetails(Response rawRes) {
    String customerFirstname = getCustomerFirstname(rawRes);
    assertEquals(
        customerFirstname,
        "featureflow",
        String.format("Invalid featureflow firstname", customerFirstname));

    String customerLastname = getCustomerLastname(rawRes);
    assertEquals(
        customerLastname, "2", String.format("Invalid featureflow lastname", customerLastname));
  }

  public static void verifyOrderUrlNotNull(Response rawRes) {
    String orderUrl = getOrdersUrl(rawRes);
    assertNotEquals(orderUrl, "", String.format("Invalid order url", orderUrl));
  }

  public static String[] splitName(String name) {
    return name.split(" ");
  }

  public static void verifyCustomerIdNotNull(Response rawRes) {
    String customerId = getCustomerId(rawRes);
    assertNotEquals(customerId, "", String.format("Invalid featureflow id", customerId));

    logInfo(String.format("Customer created successfully : %s", customerId));
  }

  protected static String getEncodedJpegData() {
    byte[] file = findFile("test_resources/sampleJPEG.jpeg");
    return encodeImageToBase64(file);
  }

  protected static String getEncodedPngData() {
    byte[] file = findFile("test_resources/samplePNG.png");
    return encodeImageToBase64(file);
  }

  protected static String getOversizedEncodedJpegData() {
    byte[] file = findFile("test_resources/oversizedSampleJPEG.jpg");
    return encodeImageToBase64(file);
  }
}
