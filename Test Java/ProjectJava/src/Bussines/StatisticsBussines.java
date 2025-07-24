package Bussines;

import Entity.CourseStats;
import java.util.List;

public interface StatisticsBussines {
    int[] getTotalCounts();
    List<CourseStats> getStudentsPerCourse();
    List<CourseStats> getTopCourses(int limit);
    List<CourseStats> getCoursesOverThreshold(int minStudents);
}
