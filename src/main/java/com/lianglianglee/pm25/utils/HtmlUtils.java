package com.lianglianglee.pm25.utils;

import com.lianglianglee.pm25.consts.AppConst;
import com.lianglianglee.pm25.crawler.WaitCrawlerCity;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class HtmlUtils {


  /**
   * 获取表元组
   *
   * @param table
   * @return
   */
  public static List<List<String>> getDeivceData(Element table, String cityname, int[] index) {
    List<List<String>> result = new ArrayList<>();
    for (Element etr : table.select("tr")) {
      List<String> list = new ArrayList<>();
      List<Element> data = etr.select("td");
      if (data.size() < 1) {
        continue;
      }
      for (int i : index) {
        Element element = data.get(i);
        String temp = element.text();
        if ("_".equals(temp)) {
          temp = null;
        }
        list.add(temp);
        if (i == 0) {
          list.add(cityname);
          list.add(AppConst.getDate());
        }
      }
      result.add(list);
    }
    return result;
  }

  /**
   * 获取表元组	 * @param table	 * @return
   */
  public static List<List<String>> getCityTable(Element table, int index, int[] order) {
    List<List<String>> result = new ArrayList<>();
    for (Element etr : table.select("tr")) {
      List<String> list = new ArrayList<>();
      List<Element> data = etr.getElementsByTag("td");
      if (data.size() < 1) {
        continue;
      }
      for (int i : order) {
        Element element = data.get(i);
        String temp = element.text();

        if ("_".equals(temp)) {
          temp = null;
        }
        list.add(temp);
        if (i == index) {
          Element link = element.select("a").first();
          String relHref = link.attr("href");
          WaitCrawlerCity.addTask(relHref);
          list.add(AppConst.getDate());
        }
      }
      result.add(list);
    }
    return result;
  }

}
