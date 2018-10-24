package test.liangliangle;

import com.lianglianglee.pm25.consts.AppConst;
import com.lianglianglee.pm25.crawler.CheckData;
import com.lianglianglee.pm25.crawler.CrawlerDevice;
import com.lianglianglee.pm25.crawler.WebDriverConst;
import com.lianglianglee.pm25.database.DataDao;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @ClessName MainTest
 * @Desc TODO
 * @Author liangliang
 * @Date 2018/9/30 14:25
 * @Version 1.0
 */
public class MainTest {

  @Test
  public void getDate() throws IOException {
    //chrome.exe
    Runtime.getRuntime().exec("taskkill /f /im WinRAR.exe");
    Runtime.getRuntime().exec("cmd /c dir  taskkill /f /im chromedriver.exe");
  }

  @Test
  public void current() {
    System.out.println(System.getProperty("os.name"));
  }

  @Test
  public void stringDate() {
    String s = "数据更新时间:1956";
    String[] strings = s.split("数据更新时间:");
    System.out.println(s);


  }

  @Test
  public void getData() {
    AppConst.setDate("2017-08-16 16:00:00");
    List<List<String>> data = new CrawlerDevice("http://pm25.in/chifeng").getData();
    new DataDao().insertDeviceAir(data);
    data.forEach(d -> {
      d.forEach(a -> {
        System.out.print(a + "  ");
      });
      System.out.println();
    });

  }
}
