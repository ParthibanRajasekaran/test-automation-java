package com.testautomation.project.api.request;

import com.testautomation.project.api.resource.SampleResource;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;

import static com.testautomation.project.api.payload.SamplePayload.buildCustomerCreationPayload;
import static com.testautomation.project.api.payload.SamplePayload.buildUpdateCustomerDetailsPayload;
import static com.testautomation.project.common.ConfigFileReaderUtils.getValueFromEnvironmentFile;
import static com.testautomation.utils.common.Logger.*;
import static io.restassured.RestAssured.given;

public class SampleRequests {

  @Step("Create Customer using POST request")
  public static Response createCustomer(String firstname, String lastname) throws IOException {
    final String host = getValueFromEnvironmentFile("host_api");

    final String resourcePath = SampleResource.customerResource;

    final String requestBody = buildCustomerCreationPayload(firstname, lastname);

    logInfo("Base URI : " + host);
    logInfo("Path : " + resourcePath);
    logRequest(requestBody);

    Response rawRes =
        given()
            .baseUri(host)
            .basePath(resourcePath)
            .body(requestBody)
            .contentType(ContentType.JSON)
            .and()
            .when()
            .post()
            .then()
            .log()
            .body()
            .contentType(ContentType.JSON)
            .and()
            .extract()
            .response();

    logResponse(rawRes);
    return rawRes;
  }

  @Step("Get Customer details using GET request")
  public static Response getCustomerDetails(String customerId) {

    final String host = getValueFromEnvironmentFile("host_api");
    final String resourcePath = SampleResource.customerResource + customerId;

    logInfo("Base URI : " + host);
    logInfo("Path : " + resourcePath);

    Response rawRes =
        given()
            .baseUri(host)
            .basePath(resourcePath)
            .contentType(ContentType.JSON)
            .and()
            .when()
            .get()
            .then()
            .contentType(ContentType.JSON)
            .and()
            .extract()
            .response();

    logResponse(rawRes);
    return rawRes;
  }

  @Step("Update Customer details using PUT request")
  public static Response updateCustomerDetails(
      String customerId, String newFirstName, String newLastName) throws IOException {

    final String host = getValueFromEnvironmentFile("host_api");
    final String resourcePath = SampleResource.customerResource + customerId;

    String requestBody = buildUpdateCustomerDetailsPayload(newFirstName, newLastName);

    logInfo("Base URI : " + host);
    logInfo("Path : " + resourcePath);
    logRequest(requestBody);

    Response rawRes =
        given()
            .baseUri(host)
            .basePath(resourcePath)
            .body(requestBody)
            .contentType(ContentType.JSON)
            .and()
            .when()
            .put()
            .then()
            .contentType(ContentType.JSON)
            .and()
            .extract()
            .response();

    logResponse(rawRes);
    return rawRes;
  }

  @Step("Delete Customer details using DELETE request")
  public static Response deleteCustomerDetails(String customerId) {

    final String host = getValueFromEnvironmentFile("host_api");
    final String resourcePath = SampleResource.customerResource + customerId;

    logInfo("Base URI : " + host);
    logInfo("Path : " + resourcePath);

    Response rawRes =
        given()
            .baseUri(host)
            .basePath(resourcePath)
            .contentType(ContentType.JSON)
            .and()
            .when()
            .delete()
            .then()
            .extract()
            .response();

    logResponse(rawRes);
    return rawRes;
  }
}
