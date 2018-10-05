package com.lianglianglee.pm25.thread;

import com.lianglianglee.pm25.crawler.CheckData;
import com.lianglianglee.pm25.crawler.ThreadPool;

/**
 * @ClessName CheckThread
 * @Desc TODO
 * @Author liangliang
 * @Date 2018/9/30 17:04
 * @Version 1.0
 */
public class CheckThread extends Thread {
  @Override
  public void run() {
    while (true) {
      try {
        if (!CheckData.checkData()) {
          new MainThread().run();
          new ThreadPool().run();
        }
        Thread.sleep(6000000);
      } catch (Exception e) {
        e.printStackTrace();
      }
      //new CrawlerCity().getData();
    }

  }


}
