package Dao;

import Entity.Enrollment;
import java.util.List;

public interface EnrollmentDao {
    List<Enrollment> getEnrollmentsByCourse(int courseId);
    boolean updateEnrollmentStatus(int id, String status);
    boolean deleteEnrollment(int id);
}
