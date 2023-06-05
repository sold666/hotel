package com.sold.hotel.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    public static void connection() {
        try {
            Utils.logger = Logger.getLogger(DBConnection.class);
            String dbURL = "jdbc:jtds:sqlserver://LAPTOP-3A07KN4S/Hotel;instance=MSSQLSERVER;encrypt=true;trustServerCertificate=true";
            connection = DriverManager.getConnection(dbURL);
            Utils.logger.log(Level.INFO, "Database connect");
        } catch (SQLException exception) {
            Utils.logger.log(Level.FATAL, exception.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
