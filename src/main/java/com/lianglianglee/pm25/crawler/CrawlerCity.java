package com.lianglianglee.pm25.crawler;

import com.lianglianglee.pm25.utils.HtmlUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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

  private  static Logger logger= LoggerFactory.getLogger(CrawlerCity.class);
  public List<List<String>> getData() {
    logger.info("开始爬取排行榜");
    List<List<String>> data = new ArrayList<>();
    try {
      String html = WebDriverConst.getUrl(UrlConst.RANK_URL, false);
      Document doc = Jsoup.parse(html);
      Element table = doc.select("table").first();
      data = HtmlUtils.getCityTable(table, 1, index);
      logger.info("抓取到数据行数：" + data.size());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return data;
  }


}
