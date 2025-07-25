package Dao.IMP;

import Dao.UserDao;
import Entity.User;
import Ulti.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoIMP implements UserDao {

    @Override
    public List<User> getAllStudents() {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL get_all_student()}");
            rs = callst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setName(rs.getString("name"));
                user.setDob(rs.getDate("dob"));
                user.setEmail(rs.getString("email"));
                user.setSex(rs.getBoolean("sex"));
                user.setPhone(rs.getString("phone"));
                user.setCreateAt(rs.getTimestamp("create_at").toLocalDateTime());
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }
        return list;
    }

    @Override
    public User getStudentsById(int id) {
        User user = null;
        Connection conn = null;
        CallableStatement callst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL get_student_by_id(?)}");
            callst.setInt(1, id);
            rs = callst.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setName(rs.getString("name"));
                user.setDob(rs.getDate("dob"));
                user.setEmail(rs.getString("email"));
                user.setSex(rs.getBoolean("sex"));
                user.setPhone(rs.getString("phone"));
                user.setCreateAt(rs.getTimestamp("create_at").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }
        return user;
    }

    @Override
    public boolean addStudent(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            System.err.println("Tên đăng nhập (username) không được để trống.");
            return false;
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            System.err.println("Tên học viên không được để trống.");
            return false;
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            System.err.println("Email không được để trống.");
            return false;
        }

        Connection conn = null;
        CallableStatement callst = null;
        boolean isInserted = false;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL add_student(?, ?, ?, ?, ?, ?)}");
            callst.setString(1, user.getUsername());
            callst.setString(2, user.getName());
            if (user.getDob() != null) {
                callst.setDate(3, new java.sql.Date(user.getDob().getTime()));
            } else {
                callst.setNull(3, Types.DATE);
            }
            callst.setString(4, user.getEmail());
            callst.setBoolean(5, user.isSex());
            callst.setString(6, user.getPhone());
            int affectedRows = callst.executeUpdate();
            isInserted = affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }

        return isInserted;
    }


    @Override
    public boolean updateStudent(User user) {

        Connection conn = null;
        CallableStatement callst = null;
        boolean isUpdated = false;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL update_student_by_id(?, ?, ?, ?, ?, ?, ?, ?)}");
            callst.setInt(1, user.getId());
            callst.setString(2, user.getUsername());
            callst.setString(3, user.getPassword());
            callst.setString(4, user.getName());
            if (user.getDob() != null) {
                callst.setDate(5, new java.sql.Date(user.getDob().getTime()));
            } else {
                callst.setNull(5, Types.DATE);
            }
            callst.setString(6, user.getEmail());
            callst.setBoolean(7, user.isSex());
            callst.setString(8, user.getPhone());

            int affectedRows = callst.executeUpdate();
            isUpdated = affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }

        return isUpdated;
    }

    @Override
    public boolean deleteStudent(int id) {
        if (id <= 0) {
            System.err.println("ID học viên không hợp lệ.");
            return false;
        }

        Connection conn = null;
        CallableStatement callst = null;
        boolean isDeleted = false;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL delete_student(?)}");
            callst.setInt(1, id);
            int affectedRows = callst.executeUpdate();
            isDeleted = affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }

        return isDeleted;
    }

    @Override
    public List<User> searchStudentByName(String keyword) {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL search_students(?)}");
            callst.setString(1, keyword);
            rs = callst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setName(rs.getString("name"));
                user.setDob(rs.getDate("dob"));
                user.setEmail(rs.getString("email"));
                user.setSex(rs.getBoolean("sex"));
                user.setPhone(rs.getString("phone"));
                user.setCreateAt(rs.getTimestamp("create_at").toLocalDateTime());
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }

        return list;
    }

    @Override
    public List<User> sortStudent(String orderBy, boolean asc) {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL sort_student(?, ?)}");


            String safe = "id";
            if ("name".equalsIgnoreCase(orderBy)) safe = "name";
            callst.setString(1, safe);
            callst.setInt(2, asc ? 1 : 0);

            rs = callst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setName(rs.getString("name"));
                user.setDob(rs.getDate("dob"));
                user.setEmail(rs.getString("email"));
                user.setSex(rs.getBoolean("sex"));
                user.setPhone(rs.getString("phone"));
                user.setCreateAt(rs.getTimestamp("create_at").toLocalDateTime());
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }

        return list;
    }


    @Override
    public User getUserForLoggin(String username, String password) {
        User user = null;
        String callSP = "{CALL validate_user(?, ?)}";
        try (Connection conn = ConnectionDB.openConnection()) {
            if (conn == null) {
                System.out.println("Không kết nối được DB.");
                return null;
            }

            try (CallableStatement stmt = conn.prepareCall(callSP)) {
                stmt.setString(1, username);
                stmt.setString(2, password);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        user = new User();
                        user.setId(rs.getInt("id"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setRole(rs.getString("role"));

                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi gọi validate_user: " + e.getMessage());
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean updatePasswordInDB(int userId, String newPassword) {
        Connection conn = null;
        CallableStatement callst = null;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL update_student_password(?, ?)}");
            callst.setInt(1, userId);
            callst.setString(2, newPassword);
            callst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật mật khẩu: " + e.getMessage());
            return false;
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }
    }





}

