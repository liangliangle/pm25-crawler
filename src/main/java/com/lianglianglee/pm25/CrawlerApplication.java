package com.lianglianglee.pm25;

import com.lianglianglee.pm25.consts.AppConst;
import com.lianglianglee.pm25.thread.CheckThread;

/**
 * @ClessName CrawlerApplication
 * @Desc TODO
 * @Author liangliang
 * @Date 2018/9/30 13:41
 * @Version 1.0
 */
public class CrawlerApplication {


  public static void main(String args[]) {
    AppConst.build();
    new CheckThread().run();

  }


}
