package com.lianglianglee.pm25.thread;


import com.lianglianglee.pm25.crawler.CrawlerDevice;
import com.lianglianglee.pm25.crawler.WaitCrawlerCity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClessName ThreadPool
 * @Author liangliang
 * @Date 2018/9/30 15:59
 * @Version 1.0
 */
public class ThreadPool {

  private Logger logger=LoggerFactory.getLogger(ThreadPool.class);

  public void run() {
    String url = null;
    do {
      try {
        url = WaitCrawlerCity.getTask();
        if (url != null) {
          logger.info("爬虫任务分发：" + url);
          new CrawlerDevice(url).start();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    } while (url != null);
  }
}
