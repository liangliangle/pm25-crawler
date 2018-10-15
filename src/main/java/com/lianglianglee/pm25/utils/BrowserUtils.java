package com.lianglianglee.pm25.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.io.IOException;

/**
 * @ClessName BrowserUtils
 * @Desc TODO
 * @Author liangliang
 * @Date 2018/9/30 13:42
 * @Version 1.0
 */
public class BrowserUtils {
  private static Logger logger = Logger.getLogger(BrowserUtils.class);


  /**
   * get webDriver.
   */
  private static WebDriver getInstanceByFirefox() {
    // firefox 配置文件路径
    String sprofilePath = "C:\\Users\\" + System.getProperty("user.name")
            + "\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\"
            + ConfigurationUtil.getProperty("firefox.profile.url");
    FirefoxProfile firefoxProfile = new FirefoxProfile(new File(sprofilePath));
    firefoxProfile.setAcceptUntrustedCertificates(true);
    //browser.download.folderList 设置Firefox的默认 下载 文件夹。0是桌面；1是“我的下载”；2是自定义
    firefoxProfile.setPreference("browser.download.folderList", 1);
    //url打开的页面，不新建窗口
    //firefoxProfile.setPreference("browser.link.open_newwindow", 3);
    firefoxProfile.setPreference("browser.link.open_newwindow.restriction", 1);
    firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
            "application/octet-stream, application/vnd.ms-excel, text/csv, application/zip,"
                    + " application/msexcel, "
                    + "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    FirefoxOptions options = new FirefoxOptions();
    options.setProfile(firefoxProfile);
    // Firefox配置文件，如果不设置这个也可能会被认为是爬虫
    FirefoxDriver driver = new FirefoxDriver(options);
    return driver;
  }

  /**
   * 打开谷歌浏览器.
   *
   * @return 返回浏览器
   * @throws IOException 异常
   */
  public static WebDriver getInstanceByChrome() {
    try {
      String driverDirectory = ConfigurationUtil.getProperty(ConfigurationUtil.CHROME_PATH) == null
              ? "./chromedriver.exe" : ConfigurationUtil.getProperty(ConfigurationUtil.CHROME_PATH);
      logger.info("driverDirectory = [" + driverDirectory + "]");
      ChromeDriverService src = new ChromeDriverService.Builder()
              .usingDriverExecutable(new File(driverDirectory)).usingAnyFreePort().build();
      src.start();
      ChromeOptions options = new ChromeOptions();
      options.addArguments("disable-infobars");
      ChromeDriver driver = new ChromeDriver(src, options);
      driver.manage().window().maximize();
      return driver;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

  }


}
