package Presentation.StudentPresentation;

import Dao.UserDao;
import Dao.IMP.UserDaoIMP;

import Presentation.StudentMenu;
import Validate.Validate;

import java.util.Scanner;

public class UpdatePassword {

    private final UserDao userDao = new UserDaoIMP();

    public void updatePassword() {
        Scanner input = new Scanner(System.in);
        String password;

        while (true) {
            System.out.print("Nhập mật khẩu mới: ");
            password = input.nextLine();

            if (!Validate.validatePassword(password)) {
                continue;
            }

            boolean success = userDao.updatePasswordInDB(StudentMenu.user.getId(), password);

            if (success) {
                StudentMenu.user.setPassword(password);
                System.out.println("Cập nhật mật khẩu thành công!");
            } else {
                System.out.println("Cập nhật mật khẩu thất bại!");
            }
            break;
        }
    }
}
