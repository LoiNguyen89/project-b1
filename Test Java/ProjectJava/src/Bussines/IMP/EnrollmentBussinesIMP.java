package Bussines.IMP;

import Bussines.EnrollmentBussines;
import Dao.EnrollmentDao;
import Dao.IMP.EnrollmentDaoIMP;
import Entity.Enrollment;
import java.util.List;

public class EnrollmentBussinesIMP implements EnrollmentBussines {
    private final EnrollmentDao enrollmentDao;

    public EnrollmentBussinesIMP() {
        this.enrollmentDao = new EnrollmentDaoIMP();
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse(int courseId) {
        return enrollmentDao.getEnrollmentsByCourse(courseId);
    }

    @Override
    public boolean approveEnrollment(int id) {
        return enrollmentDao.updateEnrollmentStatus(id, "CONFIRMED");
    }

    @Override
    public boolean denyEnrollment(int id) {
        return enrollmentDao.updateEnrollmentStatus(id, "DENIED");
    }

    @Override
    public boolean deleteEnrollment(int id) {
        return enrollmentDao.deleteEnrollment(id);
    }
}
