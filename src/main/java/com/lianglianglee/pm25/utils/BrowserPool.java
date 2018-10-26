package com.lianglianglee.pm25.utils;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @ClessName BrowserPool
 * @Author liangliang
 * @Date 2018/10/15 20:42
 * @Version 1.0
 */
public class BrowserPool {

  private static Integer maxPoolSize = 10;
  private static Integer totalSize = 0;
  private static final Logger logger = LoggerFactory.getLogger(BrowserPool.class);

  static {
    String size = ConfigurationUtil.getProperty("webdriver.maxsize");
    if (null != size) {
      maxPoolSize = Integer.valueOf(size);
    }
  }

  private static ConcurrentLinkedQueue<WebDriver> webDrivers = new ConcurrentLinkedQueue<>();


  public static WebDriver getWebDriver() {
    logger.info("有线程需要浏览器");
    WebDriver webDriver = null;
    do {
      webDriver = webDrivers.poll();
      try {
        Thread.sleep(5000);
      } catch (Exception e) {
      }
      if (null == webDriver) {
        synchronized (totalSize) {
          if (totalSize < maxPoolSize) {
            totalSize++;
            logger.info("打开新的浏览器了");
            webDriver = BrowserUtils.getInstance();
          }
        }
      }
    } while (null == webDriver);
    webDriver.switchTo().defaultContent();
    logger.info("浏览器要返回了，还有：" + webDrivers.size() + "个浏览器");
    return webDriver;
  }

  public static void restore(WebDriver webDriver) {
    synchronized (webDrivers) {
      webDrivers.add(webDriver);
      logger.info("浏览器要归还了，还有：" + webDrivers.size() + "个浏览器");
    }
  }


  public static void quit(WebDriver webDriver) {
    synchronized (totalSize) {
      webDriver.quit();
      totalSize--;
      logger.info("浏览器注销了，还有：" + webDrivers.size() + "个浏览器");
    }
  }

  public static void clean() {
    WebDriver webDriver = null;
    totalSize = 0;
    do {
      webDriver = webDrivers.poll();
      if (null != webDriver) {
        webDriver.quit();
      }
    } while (webDriver != null);
    try {
      Runtime.getRuntime().exec("taskkill /f /im phantomjs.exe");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
