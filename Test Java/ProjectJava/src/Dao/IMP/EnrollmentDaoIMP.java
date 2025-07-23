package Dao.IMP;

import Dao.EnrollmentDao;
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
                Timestamp ts = rs.getTimestamp("registered_at");
                if (ts != null) e.setRegisteredAt(ts.toLocalDateTime());
                e.setStatus(rs.getString("status"));
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
}



