package com.lianglianglee.pm25.database;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClessName DataDao
 * @Desc 数据读写
 * @Author liangliang
 * @Date 2018/9/30 14:18
 * @Version 1.0
 */
public class DataDao {
  private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  /**
   * @Desc 读取数据库最新日期
   * @Author liangliang
   * @Date 2018/9/30 17:18
   * @Version 1.0
   */
  public String getNewDate() {
    Connection con = Conn.getConn();
    Timestamp timestamp = null;
    try {
      Statement stmt = con.createStatement();
      ResultSet set = stmt.executeQuery("select max(times) from cityair");
      while (set.next()) {
        timestamp = set.getTimestamp(1);
      }
      if (null != timestamp) {
        return format.format(timestamp);
      }
      return "";
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Conn.close(con);
    }
    return "";
  }

  /**
   * @Desc 写入城市汇总值
   * @Author liangliang
   * @Date 2018/9/30 17:19
   * @Version 1.0
   */
  public int insertCityAir(List<List<String>> datas) {
    Connection con = Conn.getConn();
    int count = 0;
    try {
      for (List<String> data : datas) {

        String sql = "insert into cityair(cityname,times,aqi,pm25,pm10,co,so2,no2,o3,prima)values" +
                "(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        for (int i = 0; i < data.size(); i++) {
          ps.setString(i + 1, data.get(i));
        }
        int index = ps.executeUpdate();
        count += index;
      }
      Conn.commitTransaction(con);
    } catch (SQLException e) {
      e.printStackTrace();
      Conn.rollBackTransaction(con);
      throw new RuntimeException("写入失败");
    } finally {
      Conn.close(con);
    }
    return count;

  }

  /**
   * @Desc 写入监测点的值
   * @Author liangliang
   * @Date 2018/9/30 17:18
   * @Version 1.0
   */
  public int insertDeviceAir(List<List<String>> datas) {
    Connection con = Conn.getConn();
    int count = 0;
    try {
      for (List<String> data : datas) {

        String sql = "insert into deviceair(stationname,cityname,times,aqi,pm25,pm10,co,so2,o3,no2,prkey)values" +
                "(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        for (int i = 0; i < data.size(); i++) {
          ps.setString(i + 1, data.get(i));
        }
        int index = ps.executeUpdate();
        count += index;
      }
      Conn.commitTransaction(con);
    } catch (SQLException e) {
      e.printStackTrace();
      Conn.rollBackTransaction(con);
      throw new RuntimeException("写入失败");
    } finally {
      Conn.close(con);
    }
    return count;
  }

}
