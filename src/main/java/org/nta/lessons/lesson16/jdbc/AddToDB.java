package org.nta.lessons.lesson16.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AddToDB {
  private static final String LAST_INT = "SELECT * FROM fibonacci";
  private static final String ADD_VALUE = "INSERT INTO fibonacci values (?,?)";

  public static boolean saveValues(List<Integer> list) {
    MySQLDB dbWorker = new MySQLDB();
    Statement statement;
    try {
      statement = dbWorker.getConnection().createStatement();
      int anInt = 0;
      try {
      ResultSet resultSet = statement.executeQuery(LAST_INT);
      while (resultSet.next())
      {
        anInt = resultSet.getInt("int");
       anInt++;
      }}
      catch (SQLException e){
        System.out.println("бд пустая");
      }
      for (int i = anInt; i <list.size() ; i++) {
        PreparedStatement preparedStatement = dbWorker.getConnection().prepareStatement(ADD_VALUE);
        preparedStatement.setInt(1,i);
        preparedStatement.setInt(2, list.get(i));
        preparedStatement.execute();
      }
    } catch (SQLException troubles) {
      System.out.println("Не удалось загрузить класс драйвер");
      troubles.printStackTrace();
      return false;
    }
return true;
  }
}
