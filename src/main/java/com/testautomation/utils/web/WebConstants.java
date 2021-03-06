package com.testautomation.utils.web;

import com.testautomation.utils.common.Constants;

import static com.testautomation.utils.common.Constants.findResource;

/** Provides configuration data such as location of properties files. */
public class WebConstants extends Constants {

  private static final long serialVersionUID = -1510175697881600431L;

  public String getHubConfigPath() {
    return HUB_CONFIG_FILE;
  }

  @Deprecated
  public static final String HUB_CONFIG_FILE = findResource("selenium_config.json").getPath();

  public static final long FLUENT_WAIT_TIMEOUT_SECONDS = 60;

  public static final long FLUENT_WAIT_POLLING_INTERVAL_MILLISECONDS = 5000;
}
