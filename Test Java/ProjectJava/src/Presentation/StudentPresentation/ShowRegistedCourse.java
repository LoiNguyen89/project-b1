package Presentation.StudentPresentation;

import Bussines.EnrollmentBussines;
import Bussines.IMP.EnrollmentBussinesIMP;
import Entity.Course;
import Presentation.StudentMenu;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ShowRegistedCourse {
    private final EnrollmentBussines enrollmentBussines = new EnrollmentBussinesIMP();
    private final Scanner input = new Scanner(System.in);

    public ShowRegistedCourse() {
        show();
    }

    private void show() {
        List<Course> courses = enrollmentBussines.getRegisteredCourses(StudentMenu.user.getId());

        if (courses.isEmpty()) {
            System.out.println("Bạn chưa đăng ký khóa học nào.");
            return;
        }

        System.out.println("1. Sắp xếp theo tên (A-Z)");
        System.out.println("2. Sắp xếp theo tên (Z-A)");
        System.out.println("3. Sắp xếp theo ngày đăng ký (mới nhất)");
        System.out.println("4. Sắp xếp theo ngày đăng ký (cũ nhất)");
        System.out.print("Chọn: ");
        int choice = Integer.parseInt(input.nextLine());

        switch (choice) {
            case 1 -> courses.sort(Comparator.comparing(Course::getName));
            case 2 -> courses.sort(Comparator.comparing(Course::getName).reversed());
            case 3 -> courses.sort(Comparator.comparing(Course::getCreateAt).reversed());
            case 4 -> courses.sort(Comparator.comparing(Course::getCreateAt));
        }

        for (Course course : courses) {
            System.out.println(course);
        }
    }
}
