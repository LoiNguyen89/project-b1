package Validate;

import Ulti.ConnecttonDB;     // dùng class của bạn
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Validate {

    public static boolean validateInput(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Username không được để trống!");
            return false;
        }
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password không được để trống!");
            return false;
        }
        if (password.length() < 4) {
            System.out.println("Password phải có ít nhất 4 ký tự!");
            return false;
        }
        return true;
    }


    public static boolean checkUserFromDB(String username, String password) {
        String callSP = "{CALL validate_user(?, ?, ?)}";
        try (Connection conn = ConnecttonDB.openConnection()) {
            if (conn == null) {
                System.out.println("Không kết nối được DB.");
                return false;
            }
            try (CallableStatement stmt = conn.prepareCall(callSP)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.registerOutParameter(3, java.sql.Types.INTEGER);
                stmt.execute();
                int result = stmt.getInt(3);
                return result == 1;
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi gọi validate_user: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }



    public static boolean validateCourseInput(String name, String durationStr, String instructor) {
        return validateName(name) && validateDuration(durationStr) && validateInstructor(instructor);
    }

    public static boolean validateName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean validateDuration(String durationStr) {
        try {
            int d = Integer.parseInt(durationStr);
            return d > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validateInstructor(String instructor) {
        return instructor != null && !instructor.trim().isEmpty();
    }
}




