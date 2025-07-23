package Validate;

import Ulti.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Validate {

//       LOGIN VALIDATION

    public static boolean validateInput(String username, String password) {
        if (!validateUsername(username)) return false;
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
        try (Connection conn = ConnectionDB.openConnection()) {
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

//    COURSE VALIDATE

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

//USER VALIDATE

    private static final Pattern USERNAME_PATTERN =
            Pattern.compile("^[A-Za-z0-9_.]{4,30}$");

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[0-9]{8,20}$");

    public static boolean validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Username không được để trống!");
            return false;
        }
        if (!USERNAME_PATTERN.matcher(username.trim()).matches()) {
            System.out.println("Username không hợp lệ! (4-30 ký tự: chữ, số, '_', '.')");
            return false;
        }
        return true;
    }

    public static boolean validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email không được để trống!");
            return false;
        }
        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) {
            System.out.println("Email không hợp lệ!");
            return false;
        }
        return true;
    }

    public static boolean validatePhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            System.out.println("Số điện thoại không được để trống!");
            return false;
        }
        if (!PHONE_PATTERN.matcher(phone.trim()).matches()) {
            System.out.println("Số điện thoại không hợp lệ!");
            return false;
        }
        return true;
    }

    public static boolean validateUserInput(String username, String name, String email, String phone) {
        boolean ok = true;
        if (!validateUsername(username)) ok = false;
        if (!validateName(name)) {
            System.out.println("Tên học viên không được để trống!");
            ok = false;
        }
        if (!validateEmail(email)) ok = false;
        if (!validatePhone(phone)) ok = false;
        return ok;
    }

//ENROLLMENT VALIDATE

    public static boolean validateCourseId(String courseIdStr) {
        try {
            int courseId = Integer.parseInt(courseIdStr);
            if (courseId <= 0) {
                System.out.println("Course ID phải > 0.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Course ID không hợp lệ!");
            return false;
        }
    }

    public static boolean validateEnrollmentId(String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            if (id <= 0) {
                System.out.println("Enrollment ID phải > 0.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Enrollment ID không hợp lệ!");
            return false;
        }
    }

    // Kiểm tra trạng thái (WAITING, DENIED, CANCEL, CONFIRMED)
    public static boolean validateEnrollmentStatus(String status) {
        if (status == null) return false;
        String s = status.toUpperCase().trim();
        if (!s.equals("WAITING") && !s.equals("DENIED")
                && !s.equals("CANCEL") && !s.equals("CONFIRMED")) {
            System.out.println("Trạng thái không hợp lệ! (WAITING, DENIED, CANCEL, CONFIRMED)");
            return false;
        }
        return true;
    }
}
