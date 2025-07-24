package Presentation;

import Presentation.AdminPresentation.CourseManager;
import Presentation.AdminPresentation.EnrollmentManager;
import Presentation.AdminPresentation.StatisticsManager;
import Presentation.AdminPresentation.UserManager;

import java.util.Scanner;

public class AdminMenu {
    Scanner input = new Scanner(System.in);
    public AdminMenu() {
        showMenu();
    }

    public void showMenu() {
        boolean exit = false;
        int choice;
        do {
            System.out.println("\n===== MENU QUẢN LÝ ADMIN =====");
            System.out.println("1. Quản lý khóa học");
            System.out.println("2. Quản lý học viên");
            System.out.println("3. Quản lý đăng ký khóa học");
            System.out.println("4. Thống kê");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");

            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1 -> new CourseManager();
                case 2 -> new UserManager();
                case 3 -> new EnrollmentManager();
                case 4 -> new StatisticsManager();
                case 5 -> {
                    System.out.println("Thoát menu Admin.");
                    exit = true;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (!exit);
    }
}
