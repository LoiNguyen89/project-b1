package Dao;

import Entity.Course;

import java.util.List;

public interface CourseDao {

    List<Course> getAllCourses();
    Course getCourseById(int id);
    boolean addCourse(Course course);
    boolean updateCourse(Course course);
    boolean deleteCourse(int id);
    List<Course> searchCourseByName(String keyword);
    List<Course> sortCourses(String orderBy, boolean asc);

}
