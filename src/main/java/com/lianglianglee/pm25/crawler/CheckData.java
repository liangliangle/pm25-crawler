package com.lianglianglee.pm25.crawler;

import com.lianglianglee.pm25.consts.AppConst;
import com.lianglianglee.pm25.utils.LoggerUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @ClessName CheckData
 * @Desc TODO
 * @Author liangliang
 * @Date 2018/9/30 14:41
 * @Version 1.0
 */
public class CheckData {

  public static boolean isNewDate() {
    String html = WebDriverConst.getUrl(UrlConst.RANK_URL, true);
    Document doc = Jsoup.parse(html);
    String currentString = doc.getElementsByClass("time").text();
    LoggerUtil.info("页面" + currentString);
    if (!isCurrentDate(currentString)) {
      String[] strings = currentString.split("数据更新时间：");
      if (strings.length == 2) {
        AppConst.setDate(strings[1]);
        return true;
      }
    }
    return false;
  }


  public static boolean isCurrentDate(String currentString) {
    String date = AppConst.getDate();
    date = "数据更新时间：" + date;
    if (currentString.equals(date)) {
      return true;
    }
    return false;
  }
}
