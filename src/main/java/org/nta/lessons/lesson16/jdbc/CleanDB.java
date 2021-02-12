package org.nta.lessons.lesson16.jdbc;

import java.sql.SQLException;
import java.sql.Statement;

public class CleanDB {
  public static void clean() {
    MySQLDB dbWorker = new MySQLDB();
    Statement statement;
    try {
      statement = dbWorker.getConnection().createStatement();
      statement.execute("delete from fibonacci");
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

  }

  public static void main(String[] args) {
    clean();
  }
}

