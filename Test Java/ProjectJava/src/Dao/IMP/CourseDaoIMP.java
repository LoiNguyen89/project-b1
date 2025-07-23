package Dao.IMP;

import Dao.CourseDao;
import Entity.Course;
import Ulti.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoIMP implements CourseDao {

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

    @Override
    public Course getCourseById(int id) {
        Course course = null;
        Connection conn = null;
        CallableStatement callst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL get_course_id(?)}");
            callst.setInt(1, id);
            rs = callst.executeQuery();
            if (rs.next()) {
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreateAt(rs.getTimestamp("create_at").toLocalDateTime());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }
        return course;
    }

    @Override
    public boolean addCourse(Course course) {
        if (course.getName() == null || course.getName().trim().isEmpty()) {
            System.err.println("Tên khóa học không được để trống.");
            return false;
        }
        if (course.getDuration() <= 0) {
            System.err.println("Thời gian học phải lớn hơn 0.");
            return false;
        }
        if (course.getInstructor() == null || course.getInstructor().trim().isEmpty()) {
            System.err.println("Giảng viên không được để trống.");
            return false;
        }

        Connection conn = null;
        CallableStatement callst = null;
        boolean isInserted = false;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL add_course(?, ?, ?)}");
            callst.setString(1, course.getName());
            callst.setInt(2, course.getDuration());
            callst.setString(3, course.getInstructor());
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
    public boolean updateCourse(Course course) {
        if (course == null || course.getId() <= 0) {
            System.err.println("Khóa học không hợp lệ để cập nhật.");
            return false;
        }

        Connection conn = null;
        CallableStatement callst = null;
        boolean isUpdated = false;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL update_course(?, ?, ?, ?)}");
            callst.setInt(1, course.getId());
            callst.setString(2, course.getName());
            callst.setInt(3, course.getDuration());
            callst.setString(4, course.getInstructor());
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
    public boolean deleteCourse(int id) {
        if (id <= 0) {
            System.err.println("ID khóa học không hợp lệ.");
            return false;
        }

        Connection conn = null;
        CallableStatement callst = null;
        boolean isDeleted = false;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL delete_course(?)}");
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
    public List<Course> searchCourseByName(String keyword) {
        List<Course> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL search_course_by_name(?)}");
            callst.setString(1, keyword);
            rs = callst.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                Timestamp ts = rs.getTimestamp("create_at");
                course.setCreateAt(ts != null ? ts.toLocalDateTime() : null);
                list.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }

        return list;
    }

    @Override
    public List<Course> sortCourses(String orderBy, boolean asc) {
        List<Course> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL sort_courses(?, ?)}");
            String safe = "id";
            if ("name".equalsIgnoreCase(orderBy)) safe = "name";
            callst.setString(1, safe);
            callst.setInt(2, asc ? 1 : 0);
            rs = callst.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                Timestamp ts = rs.getTimestamp("create_at");
                course.setCreateAt(ts != null ? ts.toLocalDateTime() : null);
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

