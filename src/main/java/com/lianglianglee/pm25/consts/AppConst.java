package com.lianglianglee.pm25.consts;

import com.lianglianglee.pm25.database.DataDao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClessName AppConst
 * @Desc TODO
 * @Author liangliang
 * @Date 2018/9/30 13:55
 * @Version 1.0
 */
public class AppConst {

  private static String date;


  public synchronized static String getDate() {
    if (null == date) {
      date = new DataDao().getNewDate();
    }
    return date;
  }

  public static void setDate(String newdate) {
    date = newdate;
  }


  public static void build() {
    String date = new DataDao().getNewDate();
    setDate(date);
  }
}
