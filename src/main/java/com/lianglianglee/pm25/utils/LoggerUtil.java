
package com.lianglianglee.pm25.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 日志处理.
 *
 * @author liangliang
 */
public class LoggerUtil {

  static {
    try {
      PropertyConfigurator.configure(LoggerUtil.class.getClassLoader()
          .getResourceAsStream("log4j.properties"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static final Logger logger = Logger.getLogger(LoggerUtil.class);

  public LoggerUtil() {
  }

  public static void info(String message, String module) {
    logger.info(" - " + module + " - " + message);
  }

  public static void info(String message) {
    logger.info(" -  - " + message);
  }


  public static void error(String message, String module) {
    logger.error(module + " - " + message);
  }
}
