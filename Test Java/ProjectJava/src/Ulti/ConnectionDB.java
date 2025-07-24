package Ulti;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test_java";
    private static final String USER = "root";
    private static final String PASS = "Loikimbai123";

    public static Connection openConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
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
