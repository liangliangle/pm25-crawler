package com.lianglianglee.pm25.crawler;

import com.lianglianglee.pm25.database.DataDao;
import com.lianglianglee.pm25.utils.HtmlUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * @ClessName CrawlerDevice
 * @Desc 监控点的空气质量
 * @Author liangliang
 * @Date 2018/9/30 15:50
 * @Version 1.0
 */
public class CrawlerDevice implements Runnable {
  private String url = null;

  private static int[] index = {0, 1, 4, 5, 6, 10, 8, 7, 3};

  @Override
  public void run() {
    if (null == this.url) {
      throw new RuntimeException("爬虫线程未初始化，请使用execute传递地址");
    }
    List<List<String>> data = getData();
    new DataDao().insertDeviceAir(data);
  }

  public CrawlerDevice(String url) {
    if (!url.startsWith("http")) {
      url = "http://pm25.in" + url;
    }
    this.url = url;
  }


  public List<List<String>> getData() {
    String html = WebDriverConst.getUrl(this.url);
    Document doc = Jsoup.parse(html);
    Element table = doc.select("table").first();
    String cityname = doc.getElementsByClass("city_name").tagName("h2").text();
    return HtmlUtils.getDeivceData(table, cityname, index);
  }
}
