package com.devcolibri.database;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

public class Main {


    private static final String PASSWORD = "root";
    private static final java.lang.String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";

    private static final String INSERT_NEW = "INSERT INTO dish VALUES(?,?,?,?,?,?,?)";
    private static final String GET_ALL = "SELECT * from dish";
    private static final String DELETE = "DELETE FROM dish WHERE id=?";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL,USERNAME, PASSWORD);

            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1,2);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String desc = resultSet.getString("description");
                float rating = resultSet.getFloat("rating");
                boolean published = resultSet.getBoolean("published");
                Date date = resultSet.getDate("created");
                byte[] icon = resultSet.getBytes("icon");

                System.out.println(id + title + desc + rating + published+ date+ icon);
            }

//            preparedStatement = connection.prepareStatement(INSERT_NEW);
//            preparedStatement.setInt(1,2);
//            preparedStatement.setString(2,"Inserted title");
//            preparedStatement.setString(3,"Inseted description");
//            preparedStatement.setFloat(4,9.2f);
//            preparedStatement.setBoolean(5, true);
//            preparedStatement.setDate(6, new Date(Calendar.getInstance().getTimeInMillis()));
//            preparedStatement.setBlob(7,new FileInputStream("sample.png"));
//            preparedStatement.execute();



        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

        }
    }
}
