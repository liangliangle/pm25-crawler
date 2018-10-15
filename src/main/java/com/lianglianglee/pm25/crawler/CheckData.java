package com.lianglianglee.pm25.crawler;

import com.lianglianglee.pm25.consts.AppConst;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;

/**
 * @ClessName CheckData
 * @Desc TODO
 * @Author liangliang
 * @Date 2018/9/30 14:41
 * @Version 1.0
 */
public class CheckData {

  public static boolean checkData() {
    String date = AppConst.getDate();
    String html = WebDriverConst.getUrl(UrlConst.MAIN_URL);
    Document doc = Jsoup.parse(html);
    String currentString = doc.getElementsByClass("time").text();
    date = "数据更新时间：" + date;
    if (!currentString.equals(date)) {
      String[] strings = currentString.split("数据更新时间：");
      AppConst.setDate(strings[1]);
      return false;
    }
    return true;
  }
}
