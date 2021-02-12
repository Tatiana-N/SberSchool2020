package org.nta.lessons.lesson16.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoadFromDB {
 private static List<Integer> list = new ArrayList<>();

  public static List<Integer> load() {
    MySQLDB dbWorker = new MySQLDB();
    Statement statement;
    String query = "select * from fibonacci";
    try {
      statement = dbWorker.getConnection().createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      while (resultSet.next()) {
        list.add(resultSet.getInt("value"));
      }
    } catch (SQLException troubles) {
      System.out.println("Не удалось загрузить класс драйвер");
      troubles.printStackTrace();
    }
return list;
  }
}
