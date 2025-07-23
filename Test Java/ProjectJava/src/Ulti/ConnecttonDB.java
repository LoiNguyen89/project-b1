package Ulti;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnecttonDB {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test_java";
    private static final String USER = "root";
    private static final String PASS = "Loikimbai123";

    public static Connection openConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/liblary", "root", "Loikimbai123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void closeConnection(Connection conn, CallableStatement callst) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (callst != null) {
                try {
                    callst.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }



}
