package com.lianglianglee.pm25.crawler;

import com.lianglianglee.pm25.consts.AppConst;
import com.lianglianglee.pm25.utils.LoggerUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @ClessName CheckData
 * @Author liangliang
 * @Date 2018/9/30 14:41
 * @Version 1.0
 */
public class CheckData {

  private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static boolean isNewDate() {
    String time1 = "";
    String time2 = "";
    String html = WebDriverConst.getUrl(UrlConst.RANK_URL, true);
    Document doc = Jsoup.parse(html);
    String currentString = doc.getElementsByClass("time").text();
    LoggerUtil.info("页面" + currentString);
    if (!isCurrentDate(currentString)) {
      String[] strings = currentString.split("数据更新时间：");
      if (strings.length == 2) {
        time1 = strings[1];
      }
    }
    List<String> strings = doc.getElementsByTag("tspan").eachText();
    for (String s : strings) {
      if (s.indexOf("发布") > 0) {
        LoggerUtil.info("页面" + s);
        String date = AppConst.getDate();
        date = date + " 发布";
        if (!s.equals(date)) {
          String[] times = s.split(" 发");
          if (times.length == 2) {
            time2 = times[0];
          }
        }
      }
    }
    return isNewTime(time1, time2+":00");
  }


  public static boolean isCurrentDate(String currentString) {
    String date = AppConst.getDate();
    date = "数据更新时间：" + date;
    if (currentString.equals(date)) {
      return true;
    }
    return false;
  }

  private static boolean isNewTime(String time1, String time2) {
    try {
      Date date1 = formatter.parse(time1);
      Date date2 = formatter.parse(time2);
      String time;
      if (date1.getTime() > date2.getTime()) {
        time = formatter.format(date1);

      } else {
        time = formatter.format(date2);
      }
      if (!AppConst.getDate().equals(time)) {
        AppConst.setDate(time);
        return true;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return false;
  }
}
