package Bussines;

import Entity.Course;
import Entity.Enrollment;
import java.util.List;

public interface EnrollmentBussines {
    List<Enrollment> getEnrollmentsByCourse(int courseId);
    boolean approveEnrollment(int id);   // Duyệt
    boolean denyEnrollment(int id);      // Từ chối
    boolean deleteEnrollment(int id);    // Xóa

    boolean registerCourse(int studentId, int courseId);
    List<Course> getRegisteredCourses(int studentId);
    boolean cancelRegistration(int enrollmentId);
    List<Enrollment> getAllEnrollments();
    List<Course> getAllCourses();
}
