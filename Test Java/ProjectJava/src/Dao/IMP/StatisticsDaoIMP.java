package Dao.IMP;

import Dao.StatisticsDao;
import Entity.CourseStats;
import Ulti.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticsDaoIMP implements StatisticsDao {

    @Override
    public int[] getTotalCounts() {
        int[] totals = {0, 0};
        Connection conn = null;
        CallableStatement callst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL stats_total_counts()}");
            rs = callst.executeQuery();
            if (rs.next()) {
                totals[0] = rs.getInt("total_courses");
                totals[1] = rs.getInt("total_students");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }
        return totals;
    }

    @Override
    public List<CourseStats> getStudentsPerCourse() {
        List<CourseStats> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callst = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL stats_students_per_course()}");
            rs = callst.executeQuery();
            while (rs.next()) {
                CourseStats cs = new CourseStats();
                cs.setCourseId(rs.getInt("course_id"));
                cs.setCourseName(rs.getString("course_name"));
                cs.setStudentCount(rs.getInt("student_count"));
                list.add(cs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }
        return list;
    }

    @Override
    public List<CourseStats> getTopCourses(int limit) {
        List<CourseStats> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL stats_top_courses(?)}");
            callst.setInt(1, limit);
            rs = callst.executeQuery();
            while (rs.next()) {
                CourseStats cs = new CourseStats();
                cs.setCourseId(rs.getInt("course_id"));
                cs.setCourseName(rs.getString("course_name"));
                cs.setStudentCount(rs.getInt("student_count"));
                list.add(cs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }
        return list;
    }

    @Override
    public List<CourseStats> getCoursesOverThreshold(int minStudents) {
        List<CourseStats> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callst = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{CALL stats_courses_over_threshold(?)}");
            callst.setInt(1, minStudents);
            rs = callst.executeQuery();
            while (rs.next()) {
                CourseStats cs = new CourseStats();
                cs.setCourseId(rs.getInt("course_id"));
                cs.setCourseName(rs.getString("course_name"));
                cs.setStudentCount(rs.getInt("student_count"));
                list.add(cs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callst);
        }
        return list;
    }
}
