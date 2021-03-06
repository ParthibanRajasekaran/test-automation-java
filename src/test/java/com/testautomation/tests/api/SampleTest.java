package com.testautomation.tests.api;

import com.testautomation.project.api.request.SampleRequests;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.IOException;

import static com.testautomation.project.api.utils.CustomFunctions.*;
import static com.testautomation.utils.api.RestAssuredUtils.verifyStatusCode;
import static com.testautomation.utils.common.Logger.logInfo;
import static org.testng.Assert.assertEquals;

@Features("Shop")
@Stories("Customer flow")
public class SampleTest {

  @Test
  @Description("Verify Customer creation")
  public void testCustomerCreation() throws IOException {
    Response rawRes = SampleRequests.createCustomer("customerFirstName", "customerLastName");
    verifyStatusCode(rawRes, 201);
    verifyCustomerIdNotNull(rawRes);
    assertEquals(getCustomerFirstname(rawRes), "customerFirstName", "Invalid First name");
    assertEquals(getCustomerLastname(rawRes), "customerLastName", "Invalid Last name");
  }

  @Test
  @Description("Verify Customer creation and retrieve created Customer details")
  public void testCustomerDetailsRetrieval() throws IOException {
    Response rawRes = SampleRequests.createCustomer("customerFirstName", "customerLastName");
    verifyStatusCode(rawRes, 201);
    String customerId = getCustomerId(rawRes);
    verifyCustomerIdNotNull(rawRes);

    rawRes = SampleRequests.getCustomerDetails(customerId);
    verifyStatusCode(rawRes, 200);

    logInfo("Customer details are retrieved successfully");
  }

  @Test
  @Description("Verify Customer creation and update Customer details")
  public void testUpdateCustomerDetails() throws IOException {
    Response rawRes = SampleRequests.createCustomer("customerFirstName", "customerLastName");
    verifyStatusCode(rawRes, 201);
    String customerId = getCustomerId(rawRes);
    verifyCustomerIdNotNull(rawRes);

    rawRes = SampleRequests.getCustomerDetails(customerId);
    verifyStatusCode(rawRes, 200);

    rawRes =
        SampleRequests.updateCustomerDetails(
            customerId, "updatedCustomerFirstName", "updatedCustomerLastName");
    verifyStatusCode(rawRes, 200);

    rawRes = SampleRequests.getCustomerDetails(customerId);
    verifyStatusCode(rawRes, 200);
    assertEquals(getCustomerFirstname(rawRes), "updatedCustomerFirstName", "Invalid First name");
    assertEquals(getCustomerLastname(rawRes), "updatedCustomerLastName", "Invalid Last name");

    logInfo("Customer details are retrieved successfully");
  }

  @Test
  @Description("Verify Customer deletion")
  public void testCustomerDeletion() throws IOException {
    Response rawRes = SampleRequests.createCustomer("customerFirstName", "customerLastName");
    verifyStatusCode(rawRes, 201);
    String customerId = getCustomerId(rawRes);
    verifyCustomerIdNotNull(rawRes);

    rawRes = SampleRequests.deleteCustomerDetails(customerId);
    verifyStatusCode(rawRes, 200);

    rawRes = SampleRequests.getCustomerDetails(customerId);
    verifyStatusCode(rawRes, 404);

    logInfo("Customer details deleted successfully");
  }
}
