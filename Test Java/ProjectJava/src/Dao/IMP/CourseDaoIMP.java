package Dao.IMP;

import Dao.CourseDao;
import Entity.Course;
import Ulti.ConnecttonDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoIMP implements CourseDao {

    @Override
    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        String sql = "{CALL get_all_courses()}";
        try (Connection conn = ConnecttonDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                list.add(mapCourse(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Course getCourseById(int id) {
        String sql = "{CALL get_course_by_id(?)}";
        try (Connection conn = ConnecttonDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return mapCourse(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addCourse(Course course) {
        String sql = "{CALL add_course(?, ?, ?)}";
        try (Connection conn = ConnecttonDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, course.getName());
            cs.setInt(2, course.getDuration());
            cs.setString(3, course.getInstructor());
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCourse(Course course) {
        String sql = "{CALL update_course(?, ?, ?, ?)}";
        try (Connection conn = ConnecttonDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, course.getId());
            cs.setString(2, course.getName());
            cs.setInt(3, course.getDuration());
            cs.setString(4, course.getInstructor());
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCourse(int id) {
        String sql = "{CALL delete_course(?)}";
        try (Connection conn = ConnecttonDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Course> searchCourseByName(String keyword) {
        List<Course> list = new ArrayList<>();
        String sql = "{CALL search_course_by_name(?)}";
        try (Connection conn = ConnecttonDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setString(1, keyword);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    list.add(mapCourse(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Course> sortCourses(String orderBy, boolean asc) {
        List<Course> list = new ArrayList<>();
        String sql = "{CALL sort_courses(?, ?)}";
        try (Connection conn = ConnecttonDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            // Chỉ cho phép name hoặc id
            String safe = "id";
            if ("name".equalsIgnoreCase(orderBy)) safe = "name";

            cs.setString(1, safe);
            cs.setInt(2, asc ? 1 : 0);

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    list.add(mapCourse(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Course mapCourse(ResultSet rs) throws SQLException {
        Course c = new Course();
        c.setId(rs.getInt("id"));
        c.setName(rs.getString("name"));
        c.setDuration(rs.getInt("duration"));
        c.setInstructor(rs.getString("instructor"));
        Timestamp ts = rs.getTimestamp("create_at");
        c.setCreateAt(ts != null ? ts.toLocalDateTime() : null);
        return c;
    }
}
