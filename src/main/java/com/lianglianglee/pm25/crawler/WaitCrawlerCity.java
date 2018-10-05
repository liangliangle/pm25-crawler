package com.lianglianglee.pm25.crawler;

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

  private static Queue<String> waitUrl = new LinkedList<>();

  public synchronized static void addTask(String url) {
    waitUrl.add(url);
  }

  public synchronized static String getTask() {
    return waitUrl.poll();
  }
}
