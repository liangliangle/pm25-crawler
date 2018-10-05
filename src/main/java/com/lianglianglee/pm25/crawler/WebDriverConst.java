package com.lianglianglee.pm25.crawler;

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


  public static String getUrl(String url, boolean refush) {
    WebDriver wd = getWebDriver();
    String currentUrl = wd.getCurrentUrl();
    if (url.equals(currentUrl) && !refush) {
      wd.switchTo().defaultContent();
    } else {
      wd.get(url);
    }
    return wd.getPageSource();
  }


  public static WebElement findElement(By by) {
    WebDriver wd = getWebDriver();
    wd.switchTo().defaultContent();
    return wd.findElement(by);
  }

  public static List<WebElement> findElements(By by) {
    WebDriver wd = getWebDriver();
    wd.switchTo().defaultContent();
    return wd.findElements(by);
  }

  /**
   * public static void getUrl(String url, boolean newTable) {
   * WebDriver wd = getWebDriver();
   * wd.switchTo().defaultContent();
   * Actions actionOpenLinkInNewTab = new Actions(wd);
   * actionOpenLinkInNewTab.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).perform();
   * String cuurenWindowHandler = wd.getWindowHandle();
   * Set<String> windowHandlers = wd.getWindowHandles();
   * for (String windowHandler : windowHandlers) {
   * if (!windowHandler.equals(cuurenWindowHandler)) {
   * wd.switchTo().window(windowHandler);
   * }
   * }
   * }
   **/

  public static void closeCurrentTable() {
    WebDriver wd = getWebDriver();
    wd.switchTo().defaultContent();
    String cuurenWindowHandler = wd.getWindowHandle();
    Set<String> windowHandlers = wd.getWindowHandles();
    for (String windowHandler : windowHandlers) {
      if (!windowHandler.equals(cuurenWindowHandler)) {
        wd.close();
        wd.switchTo().window(windowHandler);
      }
    }
  }


  private static WebDriver getWebDriver() {
    WebDriver webDriver = BrowserUtils.getWebDriver();
    webDriver.switchTo().defaultContent();
    return webDriver;
  }

  public static void quit() {
    BrowserUtils.quitCurrent();
  }
}
