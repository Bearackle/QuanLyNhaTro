/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.DriverManager;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class DataBaseConnection {
    private static Connection connection;
    public static Connection CreateConnection()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl21","C##DOANJAVA","123");
        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public static Connection getConnection(){
        return connection;
    }
}
