package Dao.IMP;

import Dao.EnrollmentDao;
import Entity.Course;
import Entity.Enrollment;
import Ulti.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDaoIMP implements EnrollmentDao {

    @Override
    public List<Enrollment> getEnrollmentsByCourse(int courseId) {
        List<Enrollment> enrollments = new ArrayList<>();
        Connection conn = null;
        CallableStatement callst = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL get_enrollments_by_course(?)}");
            callst.setInt(1, courseId);
            rs = callst.executeQuery();

            while (rs.next()) {
                Enrollment e = new Enrollment();
                e.setId(rs.getInt("id"));
                e.setStudentId(rs.getInt("student_id"));
                e.setCourseId(rs.getInt("course_id"));
                e.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime());
                e.setStatus(rs.getString("status"));
                e.setStudentName(rs.getString("student_name"));
                e.setEmail(rs.getString("email"));


                enrollments.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }
        return enrollments;
    }


    @Override
    public boolean updateEnrollmentStatus(int id, String status) {
        Connection conn = null;
        CallableStatement callst = null;
        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL update_enrollment_status(?, ?)}");
            callst.setInt(1, id);
            callst.setString(2, status);
            return callst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }
        return false;
    }

    @Override
    public boolean deleteEnrollment(int id) {
        Connection conn = null;
        CallableStatement callst = null;
        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL delete_enrollment(?)}");
            callst.setInt(1, id);
            return callst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }
        return false;
    }


    @Override
    public boolean registerCourse(int studentId, int courseId) {
        Connection conn = null;
        CallableStatement callst = null;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL register_course(?, ?)}");
            callst.setInt(1, studentId);
            callst.setInt(2, courseId);

            int result = callst.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi khi đăng ký khóa học: " + e.getMessage());
            return false;

        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }
    }

    @Override
    public List<Course> getRegisteredCourses(int studentId) {
        List<Course> courseList = new ArrayList<>();
        Connection conn = null;
        CallableStatement callst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL get_registered_courses(?)}");
            callst.setInt(1, studentId);

            rs = callst.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreateAt(rs.getTimestamp("create_at").toLocalDateTime());

                courseList.add(course);
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách khóa học đã đăng ký: " + e.getMessage());

        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }

        return courseList;
    }

    @Override
    public boolean cancelRegistration(int enrollmentId) {
        Connection conn = null;
        CallableStatement callst = null;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL cancel_registration(?)}");
            callst.setInt(1, enrollmentId);

            int result = callst.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi khi hủy đăng ký khóa học: " + e.getMessage());
            return false;

        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callst = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL get_all_enrollment()}");
            rs = callst.executeQuery();
            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setId(rs.getInt("id"));
                enrollment.setStudentId(rs.getInt("student_id"));
                enrollment.setCourseId(rs.getInt("course_id"));
                enrollment.setStatus(rs.getString("status"));
                enrollment.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime());
                enrollment.setStudentName(rs.getString("name")); // ✅ sửa đúng
                enrollment.setEmail(rs.getString("email"));       // ✅ nếu có trường email
                list.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }
        return list;
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL get_all_courses()}");
            rs = callst.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreateAt(rs.getTimestamp("create_at").toLocalDateTime());


                list.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }
        return list;
    }
}







