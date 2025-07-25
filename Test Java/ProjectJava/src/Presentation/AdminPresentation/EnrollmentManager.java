package Presentation.AdminPresentation;

import Bussines.EnrollmentBussines;
import Bussines.IMP.EnrollmentBussinesIMP;
import Entity.Course;
import Entity.Enrollment;
import Presentation.AdminMenu;
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
                    new AdminMenu();
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (true);
    }

    public void showEnrollmentsByCourse() {
        showAllCourses();
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

    public void approveEnrollment() {
        showEnrollments();
        System.out.print("Nhập ID sinh vien đăng ký cần duyệt: ");
        String idStr = input.nextLine();
        if (!Validate.validateEnrollmentId(idStr)) return;

        int id = Integer.parseInt(idStr);
        if (enrollmentBussines.approveEnrollment(id)) {
            System.out.println("Duyệt thành công!");
        } else {
            System.out.println("Duyệt thất bại!");
        }
    }

    public void deleteEnrollment() {
        showAllCourses();
        System.out.print("Nhập ID khóa học: ");
        String courseIdStr = input.nextLine();
        if (!Validate.validateCourseId(courseIdStr)) return;
        int courseId = Integer.parseInt(courseIdStr);
        List<Enrollment> enrollments = enrollmentBussines.getEnrollmentsByCourse(courseId);
        if (enrollments.isEmpty()) {
            System.out.println("Không có sinh viên nào đăng ký khóa học này.");
            return;
        }

        System.out.println("\n--- Danh sách sinh viên đăng ký khóa học ---");
        for (Enrollment e : enrollments) {
            System.out.printf("Student ID: %d | Enrollment ID: %d | Tên: %s | Email: %s | Ngày đăng ký: %s | Trạng thái: %s\n",
                    e.getStudentId(), e.getId(), e.getStudentName(), e.getEmail(),
                    e.getRegisteredAt().toLocalDate(), e.getStatus());
        }


        System.out.print("Nhập mã sinh viên cần xóa khỏi khóa học: ");
        String studentIdStr = input.nextLine();
        if (!Validate.validateStudentId(studentIdStr)) return;

        int studentId = Integer.parseInt(studentIdStr);


        Enrollment enrollmentToDelete = null;
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId) {
                enrollmentToDelete = e;
                break;
            }
        }

        if (enrollmentToDelete == null) {
            System.out.println("Không tìm thấy bản ghi đăng ký của sinh viên này trong khóa học.");
            return;
        }

        // Bước 6: Thực hiện xóa theo enrollment ID
        int enrollmentId = enrollmentToDelete.getId();
        if (enrollmentBussines.deleteEnrollment(enrollmentId)) {
            System.out.println("Xóa thành công!");
        } else {
            System.out.println("Xóa thất bại!");
        }
    }


    public void showEnrollments() {
        List<Enrollment> enrollments = enrollmentBussines.getAllEnrollments();
        if (enrollments.isEmpty()) {
            System.out.println("Không có khóa học nào!");
            return;
        }
        System.out.println("\n--- Danh sách khóa học ---");
        for (Enrollment e : enrollments) {
            System.out.println(e);
        }
    }

    public void showAllCourses() {
        List<Course> courses = enrollmentBussines.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("Không có khóa học nào!");
            return;
        }
        System.out.println("\n--- Danh sách khóa học ---");
        for (Course c : courses) {
            System.out.println(c);
        }
    }
}

