package Presentation.AdminPresentation;

import Bussines.CourseBussines;
import Bussines.IMP.CourseBussinesIMP;
import Entity.Course;
import Validate.Validate;

import java.util.List;
import java.util.Scanner;

public class CourseManager {
    Scanner input = new Scanner(System.in);
    private final CourseBussines courseBussines;

    public CourseManager() {
        courseBussines = new CourseBussinesIMP();

        do {
            System.out.println("\n===== QUẢN LÝ KHÓA HỌC =====");
            System.out.println("1. Hiển thị danh sách khóa học");
            System.out.println("2. Thêm mới khóa học");
            System.out.println("3. Chỉnh sửa khóa học");
            System.out.println("4. Xóa khóa học");
            System.out.println("5. Tìm kiếm khóa học theo tên");
            System.out.println("6. Sắp xếp khóa học");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                case 1:
                    showAllCourses();
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
                    editCourse();
                    break;
                case 4:
                    deleteCourse();
                    break;
                case 5:
                    searchCourse();
                    break;
                case 6:
                    sortCourses();
                    break;
                case 0:
                    System.out.println("Thoát menu quản lý.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    break;
            }

        } while (true);
    }

    public void showAllCourses() {
        List<Course> courses = courseBussines.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("Không có khóa học nào!");
            return;
        }
        System.out.println("\n--- Danh sách khóa học ---");
        for (Course c : courses) {
            System.out.println(c);
        }
    }

    public void addCourse() {
        System.out.println("\n--- Thêm khóa học ---");
        System.out.print("Nhập tên khóa học: ");
        String name = input.nextLine();
        System.out.print("Nhập thời lượng (giờ): ");
        String durationStr = input.nextLine();
        System.out.print("Nhập giảng viên: ");
        String instructor = input.nextLine();

        if (!Validate.validateCourseInput(name, durationStr, instructor)) {
            System.out.println("Dữ liệu không hợp lệ!");
            return;
        }

        int duration = Integer.parseInt(durationStr);
        Course course = new Course(name, duration, instructor);
        if (courseBussines.addCourse(course)) {
            System.out.println("Thêm khóa học thành công!");
        } else {
            System.out.println("Thêm khóa học thất bại!");
        }
    }

    public void editCourse() {
        System.out.println("\n--- Chỉnh sửa khóa học ---");
        System.out.print("Nhập ID khóa học cần sửa: ");
        int id = Integer.parseInt(input.nextLine());
        Course course = courseBussines.getCourseById(id);
        if (course == null) {
            System.out.println("Không tìm thấy khóa học!");
            return;
        }
        System.out.println("Khóa học hiện tại: " + course);

        System.out.println("1. Sửa tên");
        System.out.println("2. Sửa thời lượng");
        System.out.println("3. Sửa giảng viên");
        System.out.print("Chọn thuộc tính cần sửa: ");
        int opt = Integer.parseInt(input.nextLine());

        switch (opt) {
            case 1 -> {
                System.out.print("Nhập tên mới: ");
                String name = input.nextLine();
                if (Validate.validateName(name)) {
                    course.setName(name);
                } else {
                    System.out.println("Tên không hợp lệ!");
                    return;
                }
            }
            case 2 -> {
                System.out.print("Nhập thời lượng mới: ");
                String durationStr = input.nextLine();
                if (Validate.validateDuration(durationStr)) {
                    course.setDuration(Integer.parseInt(durationStr));
                } else {
                    System.out.println("Thời lượng không hợp lệ!");
                    return;
                }
            }
            case 3 -> {
                System.out.print("Nhập giảng viên mới: ");
                String instructor = input.nextLine();
                if (Validate.validateInstructor(instructor)) {
                    course.setInstructor(instructor);
                } else {
                    System.out.println("Tên giảng viên không hợp lệ!");
                    return;
                }
            }
            default -> System.out.println("Lựa chọn không hợp lệ!");
        }

        if (courseBussines.updateCourse(course)) {
            System.out.println("Cập nhật thành công!");
        } else {
            System.out.println("Cập nhật thất bại!");
        }
    }

    public void deleteCourse() {
        System.out.println("\n--- Xóa khóa học ---");
        System.out.print("Nhập ID khóa học cần xóa: ");
        int id = Integer.parseInt(input.nextLine());
        Course course = courseBussines.getCourseById(id);
        if (course == null) {
            System.out.println("Không tìm thấy khóa học!");
            return;
        }
        System.out.print("Bạn có chắc chắn muốn xóa (y/n)? ");
        String confirm = input.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            if (courseBussines.deleteCourse(id)) {
                System.out.println("Xóa thành công!");
            } else {
                System.out.println("Xóa thất bại!");
            }
        } else {
            System.out.println("Hủy xóa.");
        }
    }

    public void searchCourse() {
        System.out.println("\n--- Tìm kiếm khóa học ---");
        System.out.print("Nhập tên khóa học: ");
        String keyword = input.nextLine();
        List<Course> courses = courseBussines.searchCourseByName(keyword);
        if (courses.isEmpty()) {
            System.out.println("Không tìm thấy khóa học!");
        } else {
            courses.forEach(System.out::println);
        }
    }

    public void sortCourses() {
        System.out.println("\n--- Sắp xếp khóa học ---");
        System.out.print("Sắp xếp theo (id/name): ");
        String orderBy = input.nextLine();
        System.out.print("Tăng dần? (y/n): ");
        boolean asc = input.nextLine().equalsIgnoreCase("y");
        List<Course> courses = courseBussines.sortCourses(orderBy, asc);
        courses.forEach(System.out::println);
    }

}


