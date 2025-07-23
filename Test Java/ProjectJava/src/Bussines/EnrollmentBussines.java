package Bussines;

import Entity.Enrollment;
import java.util.List;

public interface EnrollmentBussines {
    List<Enrollment> getEnrollmentsByCourse(int courseId);
    boolean approveEnrollment(int id);   // Duyệt
    boolean denyEnrollment(int id);      // Từ chối
    boolean deleteEnrollment(int id);    // Xóa
}
