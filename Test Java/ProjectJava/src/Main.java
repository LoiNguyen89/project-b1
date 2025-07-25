import Bussines.IMP.UserBussinesIMP;
import Bussines.UserBussines;
import Entity.User;
import Presentation.AdminMenu;
import Presentation.StudentMenu;
import Validate.Validate;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        UserBussines userBussines = new UserBussinesIMP();
        do {
            System.out.println("=== Đăng Nhập Hệ Thống ===");
            System.out.print("Nhập Username: ");
            String username = input.nextLine();
            System.out.print("Nhập Password: ");
            String password = input.nextLine();
            if (!Validate.validateInput(username, password)) {
                System.out.println("Dữ liệu không hợp lệ. Vui lòng thử lại!");
                return;
            }
            User user = userBussines.getUserForLoggin(username, password);
            if (user != null) {
                if (user.getRole().equals("admin")) {
                    System.out.println("Đăng nhập thành công!");
                    new AdminMenu();
                } else  {
                    StudentMenu.user = user;
                    System.out.println("Đăng nhập thành công!");
                    new StudentMenu();
                }
            } else {
                System.out.println("Sai Username hoặc Password!");
            }
        } while (true);


    }
}
