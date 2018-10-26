package com.lianglianglee.pm25.thread;

import com.lianglianglee.pm25.crawler.CheckData;
import com.lianglianglee.pm25.crawler.CrawlerCity;
import com.lianglianglee.pm25.database.DataDao;
import com.lianglianglee.pm25.utils.BrowserPool;

import java.util.List;

/**
 * @ClessName MainThread
 * @Author liangliang
 * @Date 2018/9/30 17:09
 * @Version 1.0
 */
public class MainThread {
  public void run() {
    while (true) {
      try {
        if (CheckData.isNewDate()) {
          BrowserPool.clean();
          List<List<String>> data = new CrawlerCity().getData();
          new DataDao().insertCityAir(data);
          new ThreadPool().run();
        }
        Thread.sleep(60000);
      } catch (RuntimeException e) {
        e.printStackTrace();
      } catch
      (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
