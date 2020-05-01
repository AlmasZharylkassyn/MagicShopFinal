package MainBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class Connect {
    private static Connection connect = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static String url = "jdbc:mysql://localhost/guilogin?serverTimezone=Europe/Moscow&useSSL=false";
    //jdbc:mysql://localhost:3306/library?serverTimezone=UTC //8.0.19
    private static String user = "root";
    private static String pass = "";

    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver"); //com.mysql.cj.jdbc.Driver //8.0.19
            connect = DriverManager
                    .getConnection(url,user,pass);
            System.out.println("connection success");

            return connect;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void registerUser() {

    }
}
