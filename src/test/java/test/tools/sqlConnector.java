package test.tools;

import com.mysql.jdbc.Driver.*;
import test.config.ConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class sqlConnector {



    static ConfigReader reader = new ConfigReader();
    protected static String jdbcUrl = reader.getProperty("URLDB");
    static String username = reader.getProperty("loginDB");
    static String password = reader.getProperty("passDB");


    public static void connectDb(){

    // JDBC URL, username, and password of MySQL server
    //String jdbcUrl = "jdbc:mysql://localhost:52000/api_app";
    //String username = "sergey";
    //String password = "sergey";

    // Establish a connection
    try{
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        System.out.println("Connected to the database!");

        // Perform database operations here

        // Close the connection when done
        connection.close();
    }
    catch (SQLException e) {
        e.printStackTrace();
    }
}
    public static void selectShares(){
//        String jdbcUrl = "jdbc:mysql://localhost:52000/api_app";
//        String username = "sergey";
//        String password = "sergey";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Connected to the database!");

            // Create a SQL statement
            Statement statement = connection.createStatement();

            // Define the SQL query
            String sqlQuery = "SELECT * FROM shares";

            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("shareTicker");
                String sharedCompany = resultSet.getString("shareCompany");

                // Process the retrieved data
                System.out.println("ID: " + id + ", Ticker: " + name + ", Company: " + sharedCompany);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

