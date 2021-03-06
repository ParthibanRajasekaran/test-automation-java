package com.testautomation.project.common;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.testautomation.utils.common.Logger.logInfo;

public class ConfigFileReaderUtils {

  @Step("Get value from Environment config file")
  public static String getValueFromEnvironmentFile(String propName) {

    String environment = System.getProperty("envConfig");
    if (environment == null || environment.equals("")) environment = "default";

    logInfo("Tests are run on " + environment + "config");
    String filePath = Constants.getConfigPath() + "/environment_config.json";
    File configFile = null;
    try {
      configFile = new File(URLDecoder.decode(filePath, String.valueOf(StandardCharsets.UTF_8)));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    JsonObject jsonObject = null;
    try {
      assert configFile != null;
      jsonObject = (JsonObject) JsonParser.parseReader(new FileReader(configFile));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    final String value =
        Objects.requireNonNull(jsonObject).getAsJsonObject(environment).get(propName).getAsString();

    logInfo(String.format("%s is read from environment file", value));

    return value;
  }
}
