package Dao;

import Entity.Course;
import Entity.Enrollment;
import java.util.List;

public interface EnrollmentDao {
    List<Enrollment> getEnrollmentsByCourse(int courseId);
    boolean updateEnrollmentStatus(int id, String status);
    boolean deleteEnrollment(int id);


    boolean registerCourse(int studentId, int courseId);
    List<Course> getRegisteredCourses(int studentId);
    boolean cancelRegistration(int enrollmentId);
    List<Enrollment> getAllEnrollments();
    List<Course> getAllCourses();
}
