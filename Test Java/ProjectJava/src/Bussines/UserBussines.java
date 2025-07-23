package Bussines;

import Entity.User;

import java.util.List;

public interface UserBussines {

    List<User> getAllStudents();

    User getStudentsById(int id);

    boolean addStudent(User user);

    boolean updateStudent(User user);

    boolean deleteStudent(int id);

    List<User> searchStudentByName(String keyword);

    List<User> sortStudent(String orderBy, boolean asc);
}
