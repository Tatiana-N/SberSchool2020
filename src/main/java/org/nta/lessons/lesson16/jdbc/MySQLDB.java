package org.nta.lessons.lesson16.jdbc;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDB {
    private final String URL = "jdbc:mysql://localhost:3306/fibonaccidb?autoReconnect=true&useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "Root1";
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public MySQLDB() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); //подключение к БД
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
            if (connection.isClosed()) {
                System.out.println("Соединение с БД разорвано");
            }
        } catch (SQLException troubles) {
            System.out.println("Не удалось соединиться с БД");
            troubles.printStackTrace();
        }
    }
}
