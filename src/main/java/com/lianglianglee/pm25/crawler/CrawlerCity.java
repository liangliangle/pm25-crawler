package com.lianglianglee.pm25.crawler;

import com.lianglianglee.pm25.utils.HtmlUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

/**
 * @ClessName CrawlerCity
 * @Desc 排行榜
 * @Author liangliang
 * @Date 2018/9/30 14:58
 * @Version 1.0
 */
public class CrawlerCity {

  private static final int[] index = {1, 2, 5, 6, 7, 11, 9, 8, 4};

  public List<List<String>> getData() {
    String html = WebDriverConst.getUrl(UrlConst.MAIN_URL, true);
    Document doc = Jsoup.parse(html);
    Element table = doc.select("table").first();
    return HtmlUtils.getCityTable(table, 1, index);
  }


}
