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


  private static String getPhantomjs() {
    String osName = System.getProperty("os.name");
    if (osName.indexOf("Windows") >= 0) {
      return "phantomjs.exe";
    }
    return "phantomjs";
  }

  /**
   * get webDriver.
   */
  public static WebDriver getInstance() {
    //设置必要参数
    DesiredCapabilities dcaps = new DesiredCapabilities();
    //ssl证书支持
    dcaps.setCapability("acceptSslCerts", true);
    //截屏支持
    dcaps.setCapability("takesScreenshot", false);
    //css搜索支持
    dcaps.setCapability("cssSelectorsEnabled", true);
    //js支持
    dcaps.setJavascriptEnabled(true);
    //驱动支持（第二参数表明的是你的phantomjs引擎所在的路径，使用whereis phantomjs可以查看）
    dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, getPhantomjs());
    WebDriver driver = new PhantomJSDriver(dcaps);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    return driver;
  }

}
