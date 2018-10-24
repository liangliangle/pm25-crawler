package com.lianglianglee.pm25.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @ClessName BrowserUtils
 * @Author liangliang
 * @Date 2018/9/30 13:42
 * @Version 1.0
 */
public class BrowserUtils {
  private static Logger logger = Logger.getLogger(BrowserUtils.class);


  /**
   * 打开谷歌浏览器.
   *
   * @return 返回浏览器
   */
  public static WebDriver getInstance() {
    try {
      String driverDirectory = "chromedriver.exe";
      ChromeDriverService src = new ChromeDriverService.Builder()
              .usingDriverExecutable(new File(driverDirectory)).usingAnyFreePort().build();
      src.start();
      ChromeOptions options = new ChromeOptions();
      ChromeDriver driver = new ChromeDriver(src, options);
      driver.manage().window().maximize();
      driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
      return driver;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

}
