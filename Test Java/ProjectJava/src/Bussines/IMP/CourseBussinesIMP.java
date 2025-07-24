package Bussines.IMP;

import Bussines.CourseBussines;
import Dao.CourseDao;
import Dao.IMP.CourseDaoIMP;
import Entity.Course;

import java.util.List;

public class CourseBussinesIMP implements CourseBussines {
    private final CourseDao courseDao;
    public CourseBussinesIMP() {
        courseDao = new CourseDaoIMP();
    }

    @Override
    public List<Course> getAllCourses() {
        return  courseDao.getAllCourses();
    }

    @Override
    public Course getCourseById(int id) {
        return courseDao.getCourseById(id);
    }

    @Override
    public boolean addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    @Override
    public boolean updateCourse(Course course) {
        return courseDao.updateCourse(course);
    }

    @Override
    public boolean deleteCourse(int id) {
        return courseDao.deleteCourse(id);
    }

    @Override
    public List<Course> searchCourseByName(String keyword) {
        return courseDao.searchCourseByName(keyword);
    }

    @Override
    public List<Course> sortCourses(String orderBy, boolean asc) {
        return courseDao.sortCourses(orderBy, asc);
    }

    @Override
    public List<Course> searchCoursesByName(String keyword) {
        return courseDao.searchCoursesByName(keyword);
    }
}
