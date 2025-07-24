package Presentation;

import Entity.User;
import Presentation.StudentPresentation.*;

import java.util.Scanner;

public class StudentMenu {
    public static User user = new User();
    Scanner input = new Scanner(System.in);

    public StudentMenu() {
        showStudentMenu();
    }

    public void showStudentMenu() {
        int choice;
        boolean exit = false;
        do {


            System.out.println("\n===== MENU QUẢN LÝ STUDENT =====");
            System.out.println("1. Xem danh sách khóa học");
            System.out.println("2. Đăng ký khóa học ");
            System.out.println("3. Xem khóa học đã đăng kí");
            System.out.println("4. Hủy đăng ký");
            System.out.println("5. Cập nhật mật khẩu ");
            System.out.println("6. Thoat ");
            System.out.print("Chọn: ");

            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    ShowCourse.showCourseMenu();// ok
                    break;
                case 2:
                    new RegisterCourse();
                    break;
                case 3:
                    new ShowRegistedCourse();
                    break;
                case 4:
                    new DeleteCourseNotAccept().deleteCourseNotAccept();
                    break;
                case 5:
                    new UpdatePassword().updatePassword();// ok
                    break;
                case 6:
                    System.out.println("Tam biet !!!");
                    exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (!exit);
    }


}