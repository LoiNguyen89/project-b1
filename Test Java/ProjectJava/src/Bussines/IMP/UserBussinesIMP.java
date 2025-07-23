package Bussines.IMP;

import Bussines.UserBussines;
import Dao.IMP.UserDaoIMP;
import Dao.UserDao;
import Entity.User;

import java.util.List;

public class UserBussinesIMP implements UserBussines {

    private final UserDao userDao;

    public UserBussinesIMP() {
        userDao = new UserDaoIMP();
    }

    @Override
    public List<User> getAllStudents() {
        return userDao.getAllStudents();
    }

    @Override
    public User getStudentsById(int id) {
        return userDao.getStudentsById(id);
    }

    @Override
    public boolean addStudent(User user) {
        return userDao.addStudent(user);
    }

    @Override
    public boolean updateStudent(User user) {
        return userDao.updateStudent(user);
    }

    @Override
    public boolean deleteStudent(int id) {
        return userDao.deleteStudent(id);
    }

    @Override
    public List<User> searchStudentByName(String keyword) {
        return userDao.searchStudentByName(keyword);
    }

    @Override
    public List<User> sortStudent(String orderBy, boolean asc) {
        return userDao.sortStudent(orderBy, asc);
    }
}
