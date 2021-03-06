package com.testautomation.utils.common;

import lombok.SneakyThrows;

import javax.naming.ConfigurationException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Constants implements Serializable {

  public Constants() {
    super();
  }

  public static final int MAX_RETRY_COUNT = 1;

  private static final long serialVersionUID = -7763032112653322722L;

  public static final String CONFIG_FILE_DIRECTORY;

  public static final String DATA_FILE_DIRECTORY;

  @SneakyThrows
  public static final URL findResource(final String name) {
    if ("".equals(name)) {
      throw new ConfigurationException("Must provide a valid folder name");
    }
    final URL url = Constants.class.getClassLoader().getResource(name);
    if (null == url) {
      throw new ConfigurationException(name + " could not be found");
    }
    return url;
  }

  static {
    CONFIG_FILE_DIRECTORY = findResource("config").getPath();
    DATA_FILE_DIRECTORY = findResource("data").getPath();
  }

  /**
   * The configured path to configuration files.
   *
   * @return The location of the config folder.
   */
  @SneakyThrows
  public static String getConfigPath() {
    try {
      return URLDecoder.decode(CONFIG_FILE_DIRECTORY, String.valueOf(StandardCharsets.UTF_8));
    } catch (final UnsupportedEncodingException e) {
      throw new ConfigurationException("Invalid configuration, encoding not valid");
    }
  }

  /**
   * The configured path to data files.
   *
   * @return The location of the data folder.
   */
  @SneakyThrows
  public static String getDataPath() {
    try {
      return URLDecoder.decode(DATA_FILE_DIRECTORY, String.valueOf(StandardCharsets.UTF_8));
    } catch (final UnsupportedEncodingException e) {
      throw new ConfigurationException("Invalid configuration, encoding not valid");
    }
  }
}
