package Dao;

import Entity.CourseStats;

import java.util.List;

public interface StatisticsDao {
    int[] getTotalCounts(); // [0] courses, [1] students


    List<CourseStats> getStudentsPerCourse();


    List<CourseStats> getTopCourses(int limit);


    List<CourseStats> getCoursesOverThreshold(int minStudents);
}
