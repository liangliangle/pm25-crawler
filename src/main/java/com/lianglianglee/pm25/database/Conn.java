package com.lianglianglee.pm25.database;

import com.lianglianglee.pm25.utils.ConfigurationUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @ClessName Conn
 * @Desc 数据库连接
 * @Author liangliang
 * @Date 2018/9/30 14:11
 * @Version 1.0
 */
public class Conn {

  public static Connection getConn() {
    Connection conn = null;

    try {
      System.out.println("获取数据库连接");
      Class.forName(ConfigurationUtil.getProperty(ConfigurationUtil.DATASOURCE_CLASS));
      // String url = "jdbc:postgresql://172.27.244.94:5432/postgres";
      String url = ConfigurationUtil.getProperty(ConfigurationUtil.DATASOURCE_URL);

      String user = ConfigurationUtil.getProperty(ConfigurationUtil.DATASOURCE_USER);
      String password = ConfigurationUtil.getProperty(ConfigurationUtil.DATASOURCE_PASSWORD);
      try {
        conn = DriverManager.getConnection(url, user, password);
        if (conn.getAutoCommit()) {
          conn.setAutoCommit(false);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return conn;
  }

  public static void close(Connection connection) {

    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * 提交事务
   *
   * @param cnn
   */
  public static void commitTransaction(Connection cnn) {
    if (cnn != null) {
      try {
        if (!cnn.getAutoCommit()) {
          cnn.commit();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 回滚事务
   *
   * @param cnn
   */
  public static void rollBackTransaction(Connection cnn) {
    if (cnn != null) {
      try {
        if (!cnn.getAutoCommit()) {
          cnn.rollback();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

}
