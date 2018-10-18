package com.lianglianglee.pm25.crawler;

import com.lianglianglee.pm25.utils.BrowserPool;
import com.lianglianglee.pm25.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

/**
 * @ClessName WebDriverConst
 * @Desc WebDriver封装
 * @Author liangliang
 * @Date 2018/9/30 14:50
 * @Version 1.0
 */
public class WebDriverConst {


  public static String getUrl(String url) {
    WebDriver wd = getWebDriver();
    wd.get(url);
    String html = wd.getPageSource();
    restore(wd);
    return html;
  }


  private static WebDriver getWebDriver() {
    WebDriver webDriver = new BrowserPool().getWebDriver();
    webDriver.switchTo().defaultContent();
    return webDriver;
  }

  public static void restore(WebDriver webDriver) {
    BrowserPool.restore(webDriver);
  }
}
