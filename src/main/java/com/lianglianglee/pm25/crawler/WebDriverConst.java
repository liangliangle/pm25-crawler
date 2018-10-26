package com.lianglianglee.pm25.crawler;

import com.lianglianglee.pm25.utils.BrowserPool;
import com.lianglianglee.pm25.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

import static com.lianglianglee.pm25.crawler.UrlConst.MAIN_URL;

/**
 * @ClessName WebDriverConst
 * @Desc WebDriver封装
 * @Author liangliang
 * @Date 2018/9/30 14:50
 * @Version 1.0
 */
public class WebDriverConst {


  public static String getUrl(String url, boolean disposable) {
    WebDriver wd = getWebDriver();
    wd.get(url);
    String html = wd.getPageSource();
    restore(wd, disposable);
    return html;

  }


  private static WebDriver getWebDriver() {
    WebDriver webDriver = new BrowserPool().getWebDriver();
    webDriver.switchTo().defaultContent();
    return webDriver;
  }

  public static void restore(WebDriver webDriver, boolean disposable) {
    if (disposable) {
      BrowserPool.quit(webDriver);
    } else {
      BrowserPool.restore(webDriver);
    }
  }
}
