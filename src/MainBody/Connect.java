package MainBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class Connect {
    private static Connection connect = null;
    private static String url = "jdbc:mysql://localhost/guilogin?serverTimezone=Europe/Moscow&useSSL=false";
    /** Для версии коннектора 8.0.00 и выше
     *  jdbc:mysql://localhost:3306/library?serverTimezone=UTC
     */
    private static String user = "root";
    private static String pass = "";

    public static Connection ConnectDb(){
        try {
            /** Для версии коннектора 8.0.00 и выше
             *  com.mysql.cj.jdbc.Driver
             */
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(url,user,pass);
            //System.out.println("connection success");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
