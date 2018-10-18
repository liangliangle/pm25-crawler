package com.lianglianglee.pm25.thread;


import com.lianglianglee.pm25.crawler.CrawlerDevice;
import com.lianglianglee.pm25.crawler.WaitCrawlerCity;
import com.lianglianglee.pm25.utils.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClessName ThreadPool
 * @Author liangliang
 * @Date 2018/9/30 15:59
 * @Version 1.0
 */
public class ThreadPool {

  public void run() {
    String url = null;
    List<CrawlerDevice> list = new ArrayList<>();
    do {
      try {
        url = WaitCrawlerCity.getTask();
        if (url != null) {
          LoggerUtil.info("爬虫任务分发：" + url);
          new CrawlerDevice(url).start();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    } while (url != null);
  }
}
