package Presentation.StudentPresentation;

import Bussines.IMP.CourseBussinesIMP;
import Entity.Course;
import Presentation.AdminPresentation.CourseManager;

import java.util.List;
import java.util.Scanner;


public class ShowCourse {

    private static final CourseBussinesIMP cb = new CourseBussinesIMP();
    private static boolean exit = false;



    public static void showAllCourses() {
        List<Course> courses = cb.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("Không có khóa học nào!");
            return;
        }
        System.out.println("\n--- Danh sách khóa học ---");
        for (Course c : courses) {
            System.out.println(c);
        }
    }

    public static void search(String keyword) {
        cb.searchCoursesByName(keyword);

    }

    public static void showCourseMenu() {
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("\n===== MENU KHOÁ HỌC =====");
            System.out.println("1. Hiển thị danh sách khoá học");
            System.out.println("2. Tìm kiếm khoá học theo tên");
            System.out.println("3. Thoát");
            System.out.print("Chọn chức năng: ");

            int opt;
            try {
                opt = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            switch (opt) {
                case 1:
                    showAllCourses();

                    break;
                case 2:
                    System.out.print("Nhập từ khoá cần tìm: ");
                    String keyword = input.nextLine();
                    search(keyword);
                    List<Course> courses = cb.searchCourseByName(keyword);
                    if (courses.isEmpty()) {
                        System.out.println("Không tìm thấy khóa học!");
                    } else {
                        courses.forEach(System.out::println);
                    }

                    break;
                case 3:
                    System.out.println("Tạm biệt!");
                    exit = true;
                    break;
                default:
                    System.err.println("Xin mời chọn từ 1 -> 3");
            }
        } while (!exit);
    }

}




