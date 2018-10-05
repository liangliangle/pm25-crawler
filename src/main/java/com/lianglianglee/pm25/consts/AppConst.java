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


  private static Map<String, String> cityMap = new ConcurrentHashMap<>();


  private static Map<String, String> stationMap = new ConcurrentHashMap<>();


  public synchronized static String getDate() {
    if (null == date) {
      date = new DataDao().getNewDate();
    }
    return date;
  }

  public static void setDate(String newdate) {
    date = newdate;
  }

  public synchronized static void checkCity(String city) {
    String oldCity = cityMap.get(city);
    if (null == oldCity) {
      cityMap.put(city, city);
      new DataDao().insertCity(city);
    }

  }

  public synchronized static void checkDevice(String station) {
    String oldStation = stationMap.get(station);
    if (null == oldStation) {
      cityMap.put(station, station);
      new DataDao().insertDevice(station);
    }

  }


  public static void build() {
    List<String> citys = new DataDao().getAllCity();
    citys.forEach(city -> {
      cityMap.put(city, city);
    });
    List<String> stations = new DataDao().getDevice();
    stations.forEach(station -> {
              stationMap.put(station, station);
            }
    );
    String date = new DataDao().getNewDate();
    setDate(date);
  }
}
