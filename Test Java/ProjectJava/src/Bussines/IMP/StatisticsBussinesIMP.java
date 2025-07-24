package Bussines.IMP;

import Bussines.StatisticsBussines;
import Dao.StatisticsDao;
import Dao.IMP.StatisticsDaoIMP;
import Entity.CourseStats;

import java.util.List;

public class StatisticsBussinesIMP implements StatisticsBussines {

    private final StatisticsDao statisticsDao;

    public StatisticsBussinesIMP() {
        this.statisticsDao = new StatisticsDaoIMP();
    }

    @Override
    public int[] getTotalCounts() {
        return statisticsDao.getTotalCounts();
    }

    @Override
    public List<CourseStats> getStudentsPerCourse() {
        return statisticsDao.getStudentsPerCourse();
    }

    @Override
    public List<CourseStats> getTopCourses(int limit) {
        return statisticsDao.getTopCourses(limit);
    }

    @Override
    public List<CourseStats> getCoursesOverThreshold(int minStudents) {
        return statisticsDao.getCoursesOverThreshold(minStudents);
    }
}
