package Presentation.StudentPresentation;

import Bussines.EnrollmentBussines;
import Bussines.IMP.EnrollmentBussinesIMP;
import Entity.Course;
import Presentation.StudentMenu;

import java.util.Scanner;

public class RegisterCourse {
    private final EnrollmentBussines enrollmentBussines = new EnrollmentBussinesIMP();
    private final Scanner input = new Scanner(System.in);

    public RegisterCourse() {
        register();
    }

    private void register() {
        System.out.print("Nhập ID khóa học muốn đăng ký: ");
        int courseId;
        try {
            courseId = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID không hợp lệ.");
            return;
        }

        boolean result = enrollmentBussines.registerCourse(StudentMenu.user.getId(), courseId);
        if (result) {
            System.out.println("Đăng ký thành công. Đang chờ xác nhận.");
        } else {
            System.out.println("Đăng ký thất bại. Có thể bạn đã đăng ký hoặc khóa học không tồn tại.");
        }
    }
}
