package com.lianglianglee.pm25.crawler;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClessName ThreadPool
 * @Desc TODO
 * @Author liangliang
 * @Date 2018/9/30 15:59
 * @Version 1.0
 */
public class ThreadPool extends Thread {


  private static ExecutorService pool = Executors.newFixedThreadPool(5);

  @Override
  public void run() {
    String url = null;
    do {
      try {
        Thread.sleep(5000);
        url = WaitCrawlerCity.getTask();
        if (url != null) {
          CrawlerDevice crawlerDevice = new CrawlerDevice(url);
          pool.execute(crawlerDevice);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    } while (url != null);
  }
}
