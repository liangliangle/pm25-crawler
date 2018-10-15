package com.lianglianglee.pm25.utils;

import org.openqa.selenium.WebDriver;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClessName BrowserPool
 * @Author liangliang
 * @Date 2018/10/15 20:42
 * @Version 1.0
 */
public class BrowserPool {

  private static int maxPoolSize = 3;
  private static int totalSize = 0;


  private static Queue<WebDriver> webDrivers = new LinkedList<>();


  public synchronized static WebDriver getWebDriver() {
    WebDriver webDriver = null;
    do {
      webDriver = webDrivers.poll();
      if (null == webDriver) {
        if (totalSize < maxPoolSize) {
          webDriver = BrowserUtils.getInstanceByChrome();
          totalSize++;
        }
      }
    } while (null == webDriver);
    return webDriver;
  }

  public synchronized static void restore(WebDriver webDriver) {
    webDrivers.add(webDriver);
  }


}
