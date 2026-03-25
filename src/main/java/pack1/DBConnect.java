package pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import static pack1.DBInfo.*;

public class DBConnect {
    private DBConnect() {}

    public static Connection getcon() {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(DBurl, DBUname, DBUpwd);
        } catch (Exception e) {
            e.printStackTrace(); // Log the SQL exception
        }
        return con;
    }

    public static void testConnection() {
        try (Connection con = getcon()) {
            if (con != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Failed to establish a connection.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}