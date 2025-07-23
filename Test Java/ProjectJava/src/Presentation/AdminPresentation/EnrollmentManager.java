package Presentation.AdminPresentation;

import Bussines.EnrollmentBussines;
import Bussines.IMP.EnrollmentBussinesIMP;
import Entity.Enrollment;
import Validate.Validate;
import java.util.List;
import java.util.Scanner;

public class EnrollmentManager {
    private final Scanner input = new Scanner(System.in);
    private final EnrollmentBussines enrollmentBussines;

    public EnrollmentManager() {
        enrollmentBussines = new EnrollmentBussinesIMP();

        do {
            System.out.println("\n===== QUẢN LÝ ĐĂNG KÝ KHÓA HỌC =====");
            System.out.println("1. Hiển thị danh sách sinh viên theo khóa học");
            System.out.println("2. Duyệt sinh viên đăng ký");
            System.out.println("3. Xóa sinh viên khỏi khóa học");
            System.out.println("4. Thoát");
            System.out.print("Chọn: ");

            int choice;
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1 -> showEnrollmentsByCourse();
                case 2 -> approveEnrollment();
                case 3 -> deleteEnrollment();
                case 4 -> {
                    System.out.println("Thoát menu quản lý đăng ký.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (true);
    }

    private void showEnrollmentsByCourse() {
        System.out.print("Nhập ID khóa học: ");
        String courseIdStr = input.nextLine();
        if (!Validate.validateCourseId(courseIdStr)) return;

        int courseId = Integer.parseInt(courseIdStr);
        List<Enrollment> enrollments = enrollmentBussines.getEnrollmentsByCourse(courseId);

        if (enrollments.isEmpty()) {
            System.out.println("Không có sinh viên đăng ký khóa học này.");
        } else {
            System.out.println("\n--- Danh sách sinh viên đăng ký ---");
            enrollments.forEach(System.out::println);
        }
    }

    private void approveEnrollment() {
        System.out.print("Nhập ID đăng ký cần duyệt: ");
        String idStr = input.nextLine();
        if (!Validate.validateEnrollmentId(idStr)) return;

        int id = Integer.parseInt(idStr);
        if (enrollmentBussines.approveEnrollment(id)) {
            System.out.println("Duyệt thành công!");
        } else {
            System.out.println("Duyệt thất bại!");
        }
    }

    private void deleteEnrollment() {
        System.out.print("Nhập ID đăng ký cần xóa: ");
        String idStr = input.nextLine();
        if (!Validate.validateEnrollmentId(idStr)) return;

        int id = Integer.parseInt(idStr);
        if (enrollmentBussines.deleteEnrollment(id)) {
            System.out.println("Xóa thành công!");
        } else {
            System.out.println("Xóa thất bại!");
        }
    }
}
