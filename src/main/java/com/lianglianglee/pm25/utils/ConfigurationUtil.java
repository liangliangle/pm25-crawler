package com.lianglianglee.pm25.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 运行时项目配置属性.
 *
 * @author shawn_tao
 */
public class ConfigurationUtil {

  public static final String CHROME_PATH = "chrome.path";

  public static final String LOCA_BACKUP_DIR = "local.backup.dir";

  public static final String DOWN_DIR = "local.down.dir";


  public static final String DATASOURCE_URL = "datasource.url";
  public static final String DATASOURCE_USER = "datasource.username";

  public static final String DATASOURCE_PASSWORD = "datasource.password";

  public static final String DATASOURCE_CLASS = "datasource.driver-class-name";


  static Properties prop = new Properties();

  static {
    try {
      String jarWholePath = ConfigurationUtil.class.getProtectionDomain().getCodeSource()
              .getLocation().getFile();
      jarWholePath = java.net.URLDecoder.decode(jarWholePath, "UTF-8");
      System.out.println(jarWholePath);
      prop.load(new FileInputStream(jarWholePath + "/../config.properties"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 返回配置项.
   */
  public static String getProperty(String key) {
    return prop.getProperty(key);
  }

}
