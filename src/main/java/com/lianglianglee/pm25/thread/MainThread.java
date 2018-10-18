package com.lianglianglee.pm25.thread;

import com.lianglianglee.pm25.crawler.CrawlerCity;
import com.lianglianglee.pm25.database.DataDao;

import java.util.List;

/**
 * @ClessName MainThread
 * @Author liangliang
 * @Date 2018/9/30 17:09
 * @Version 1.0
 */
public class MainThread {
  public void run() {
    List<List<String>> data = new CrawlerCity().getData();
    new DataDao().insertCityAir(data);

  }
}
