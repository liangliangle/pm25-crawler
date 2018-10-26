package com.lianglianglee.pm25.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClessName WaitCrawlerCity
 * @Desc 待爬取任务
 * @Author liangliang
 * @Date 2018/9/30 16:09
 * @Version 1.0
 */
public class WaitCrawlerCity {

  private  static Logger logger= LoggerFactory.getLogger(WaitCrawlerCity.class);
  private static Queue<String> waitUrl = new LinkedList<>();

  public synchronized static void addTask(String url) {
    logger.info("添加任务，剩余任务数：" + waitUrl.size());
    waitUrl.add(url);
  }

  public synchronized static String getTask() {
    logger.info("获取任务，剩余任务数：" + waitUrl.size());
    return waitUrl.poll();
  }
}
