package com.testautomation.project.api.payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.testautomation.project.api.builder.SampleBuilder;
import com.testautomation.project.api.model.SampleModel;

import static com.testautomation.project.api.builder.SampleBuilder.addCustomer;
import static com.testautomation.project.api.builder.SampleBuilder.updateCustomerDetails;

public class SamplePayload {

  public static String buildCustomerCreationPayload(String firstname, String lastname)
      throws JsonProcessingException {
    SampleModel sampleModel = addCustomer(firstname, lastname);
    return SampleBuilder.getJsonPayload(sampleModel);
  }

  public static String buildUpdateCustomerDetailsPayload(String firstname, String lastname)
      throws JsonProcessingException {
    SampleModel sampleModel = updateCustomerDetails(firstname, lastname);
    return SampleBuilder.getJsonPayload(sampleModel);
  }
}
