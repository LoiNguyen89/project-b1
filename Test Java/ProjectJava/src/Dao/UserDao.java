package Dao;

import Entity.Course;
import Entity.User;

import java.util.List;

public interface UserDao {


    List<User> getAllStudents();

    User getStudentsById(int id);

    boolean addStudent(User user);

    boolean updateStudent(User user);

    boolean deleteStudent(int id);

    List<User> searchStudentByName(String keyword);

    List<User> sortStudent(String orderBy, boolean asc);

    User getUserForLoggin(String username, String password);

    boolean updatePasswordInDB(int userId, String newPassword);
}
