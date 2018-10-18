package com.lianglianglee.pm25.crawler;

import com.lianglianglee.pm25.database.DataDao;
import com.lianglianglee.pm25.utils.HtmlUtils;
import com.lianglianglee.pm25.utils.LoggerUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClessName CrawlerDevice
 * @Desc 监控点的空气质量
 * @Author liangliang
 * @Date 2018/9/30 15:50
 * @Version 1.0
 */
public class CrawlerDevice extends Thread {
  private String url;


  private static int[] index = {0, 1, 4, 5, 6, 10, 8, 7, 3};


  @Override
  public void run() {
    if (null == this.url) {
      throw new RuntimeException("爬虫线程未初始化，请传递地址");
    }
    LoggerUtil.info("准备爬取城市信息：" + url);
    List<List<String>> data = getData();
    new DataDao().insertDeviceAir(data);
  }

  public CrawlerDevice(String url) {
    super(url);
    if (!url.startsWith("http")) {
      url = "http://pm25.in" + url;
    }

    LoggerUtil.info("初始化城市信息：" + url);
    this.url = url;
  }


  public List<List<String>> getData() {
    LoggerUtil.info("开始爬取城市信息：" + url);
    String html = WebDriverConst.getUrl(this.url);
    LoggerUtil.info("抓取到城市信息：" + url);
    List<List<String>> data;
    Document doc = Jsoup.parse(html);
    String time = doc.getElementsByClass("live_data_time").text();
    String cityname = doc.getElementsByClass("city_name").tagName("h2").text();
    if (CheckData.checkData(time)) {
      Element table = doc.select("table").first();
      data = HtmlUtils.getDeivceData(table, cityname, index);
    } else {
      data = new ArrayList<>();
    }
    LoggerUtil.info("抓取城市" + cityname + "信息数量：" + data.size());
    return data;
  }
}
