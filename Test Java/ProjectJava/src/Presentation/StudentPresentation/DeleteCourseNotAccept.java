package Presentation.StudentPresentation;

import Bussines.EnrollmentBussines;
import Bussines.IMP.EnrollmentBussinesIMP;

import java.util.Scanner;

public class DeleteCourseNotAccept {
    private final EnrollmentBussines enrollmentBussines = new EnrollmentBussinesIMP();
    private final Scanner input = new Scanner(System.in);

    public void deleteCourseNotAccept() {
        System.out.print("Nhập ID đơn đăng ký (enrollment_id) muốn hủy: ");
        int enrollmentId;
        try {
            enrollmentId = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID không hợp lệ.");
            return;
        }

        boolean result = enrollmentBussines.cancelRegistration(enrollmentId);
        if (result) {
            System.out.println("Hủy đăng ký thành công.");
        } else {
            System.out.println("Hủy thất bại. Có thể trạng thái đã được xác nhận hoặc ID không tồn tại.");
        }
    }

}
