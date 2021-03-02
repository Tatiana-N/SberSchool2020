package org.nta.lessons.lesson16.jdbc;

import java.sql.Connection;


public class DBconnecting implements DBconnect {
  private Connection connection;

  public DBconnecting(Connection connection) {
    this.connection = connection;
  }

  @Override
  public Connection getConnection() {
    return connection;
  }
}
