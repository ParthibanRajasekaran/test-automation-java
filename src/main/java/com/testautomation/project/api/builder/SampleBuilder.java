package com.testautomation.project.api.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.testautomation.project.api.model.SampleModel;

import static com.testautomation.utils.api.RestAssuredUtils.serializeToJson;

public class SampleBuilder {
  public static String getJsonPayload(SampleModel sampleModel) throws JsonProcessingException {
    final String jsonPayload = serializeToJson(sampleModel, true);
    return jsonPayload;
  }

  public static SampleModel addCustomer(String firstname, String lastname) {
    SampleModel sampleModel = new SampleModel();
    sampleModel.setFirstname(firstname);
    sampleModel.setLastname(lastname);
    return sampleModel;
  }

  public static SampleModel updateCustomerDetails(String firstname, String lastname) {
    SampleModel sampleModel = new SampleModel();
    sampleModel.setFirstname(firstname);
    sampleModel.setLastname(lastname);
    return sampleModel;
  }
}
