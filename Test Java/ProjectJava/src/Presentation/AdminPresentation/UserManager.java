package Presentation.AdminPresentation;

import Bussines.UserBussines;
import Bussines.IMP.UserBussinesIMP;
import Entity.User;
import Presentation.AdminMenu;
import Validate.Validate;

import java.util.List;
import java.util.Scanner;

public class UserManager {
    Scanner input = new Scanner(System.in);
    private final UserBussines userBussines;

    public UserManager() {
        userBussines = new UserBussinesIMP();

        boolean running = true;
        do {
            System.out.println("\n===== QUẢN LÝ HỌC VIÊN =====");
            System.out.println("1. Hiển thị danh sách học viên");
            System.out.println("2. Thêm mới học viên");
            System.out.println("3. Chỉnh sửa học viên");
            System.out.println("4. Xóa học viên");
            System.out.println("5. Tìm kiếm học viên");
            System.out.println("6. Sắp xếp học viên");
            System.out.println("7. Thoát");
            System.out.print("Chọn: ");

            int choice;
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
                continue;
            }

            switch (choice) {
                case 1 -> showAllStudents();
                case 2 -> addStudent();
                case 3 -> editStudent();
                case 4 -> deleteStudent();
                case 5 -> searchStudent();
                case 6 -> sortStudents();
                case 7 -> {
                    System.out.println("Thoát menu quản lý học viên.");
                    running = false;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (running);
    }


    public void showAllStudents() {
        List<User> users = userBussines.getAllStudents();
        if (users.isEmpty()) {
            System.out.println("Không có học viên nào!");
            return;
        }
        System.out.println("\n--- Danh sách học viên ---");
        users.forEach(System.out::println);
    }


    public void addStudent() {
        System.out.println("\n--- Thêm học viên ---");
        System.out.print("Nhập username: ");
        String username = input.nextLine();
        System.out.print("Nhập tên: ");
        String name = input.nextLine();
        System.out.print("Nhập email: ");
        String email = input.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phone = input.nextLine();
        System.out.print("Nhập giới tính (1=Nam, 0=Nữ): ");
        String sexStr = input.nextLine();
        boolean sex = "1".equals(sexStr);

        if (!Validate.validateUserInput(username, name, email, phone)) {
            System.out.println("Dữ liệu không hợp lệ!");
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setSex(sex);

        if (userBussines.addStudent(user)) {
            System.out.println("Thêm học viên thành công (mật khẩu mặc định: 123456)!");
        } else {
            System.out.println("Thêm học viên thất bại!");
        }
    }


    public void editStudent() {
        System.out.println("\n--- Chỉnh sửa học viên ---");
        System.out.print("Nhập ID học viên cần sửa: ");
        int id;
        try {
            id = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID không hợp lệ!");
            return;
        }

        User user = userBussines.getStudentsById(id);
        if (user == null) {
            System.out.println("Không tìm thấy học viên!");
            return;
        }
        System.out.println("Thông tin hiện tại: " + user);

        System.out.println("1. Sửa username");
        System.out.println("2. Sửa tên");
        System.out.println("3. Sửa email");
        System.out.println("4. Sửa số điện thoại");
        System.out.println("5. Sửa giới tính");
        System.out.println("6. Sửa mật khẩu");
        System.out.println("7. Sửa toàn bộ (username, tên, email, phone, giới tính, mật khẩu)");
        System.out.println("8. Thoat");

        System.out.print("Chọn thuộc tính cần sửa: ");

        int opt;
        try {
            opt = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Lựa chọn không hợp lệ!");
            return;
        }

        switch (opt) {
            case 1:
                System.out.print("Nhập username mới: ");
                String username = input.nextLine();
                if (Validate.validateUsername(username)) {
                    user.setUsername(username);
                } else {
                    return;
                }
                break;

            case 2:
                System.out.print("Nhập tên mới: ");
                String name = input.nextLine();
                if (Validate.validateName(name)) {
                    user.setName(name);
                } else {
                    System.out.println("Tên không hợp lệ!");
                    return;
                }
                break;

            case 3:
                System.out.print("Nhập email mới: ");
                String email = input.nextLine();
                if (Validate.validateEmail(email)) {
                    user.setEmail(email);
                } else {
                    return;
                }
                break;

            case 4:
                System.out.print("Nhập số điện thoại mới: ");
                String phone = input.nextLine();
                if (Validate.validatePhone(phone)) {
                    user.setPhone(phone);
                } else {
                    return;
                }
                break;

            case 5:
                System.out.print("Nhập giới tính mới (1=Nam, 0=Nữ): ");
                user.setSex("1".equals(input.nextLine()));
                break;

            case 6:
                System.out.print("Nhập mật khẩu mới: ");
                String password = input.nextLine();
                if (password == null || password.isEmpty()) {
                    System.out.println("Mật khẩu không được trống!");
                    return;
                }
                user.setPassword(password);
                break;

            case 7:
                System.out.print("Nhập username mới: ");
                username = input.nextLine();
                if (!Validate.validateUsername(username)) return;
                user.setUsername(username);

                System.out.print("Nhập tên mới: ");
                name = input.nextLine();
                if (!Validate.validateName(name)) return;
                user.setName(name);

                System.out.print("Nhập email mới: ");
                email = input.nextLine();
                if (!Validate.validateEmail(email)) return;
                user.setEmail(email);

                System.out.print("Nhập số điện thoại mới: ");
                phone = input.nextLine();
                if (!Validate.validatePhone(phone)) return;
                user.setPhone(phone);

                System.out.print("Nhập giới tính mới (1=Nam, 0=Nữ): ");
                user.setSex("1".equals(input.nextLine()));

                System.out.print("Nhập mật khẩu mới: ");
                password = input.nextLine();
                if (password == null || password.isEmpty()) {
                    System.out.println("Mật khẩu không được trống!");
                    return;
                }
                user.setPassword(password);
                break;
            case 8:
                System.out.println("Thoat menu User");
                new AdminMenu();
                break;

            default:
                System.out.println("Lựa chọn không hợp lệ!");
                return;
        }

        if (userBussines.updateStudent(user)) {
            System.out.println("Cập nhật thành công!");
        } else {
            System.out.println("Cập nhật thất bại!");
        }
    }


    public void deleteStudent() {
        System.out.println("\n--- Xóa học viên ---");
        System.out.print("Nhập ID học viên cần xóa: ");
        int id;
        try {
            id = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID không hợp lệ!");
            return;
        }

        User user = userBussines.getStudentsById(id);
        if (user == null) {
            System.out.println("Không tìm thấy học viên!");
            return;
        }
        System.out.print("Bạn có chắc chắn muốn xóa (y/n)? ");
        String confirm = input.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            if (userBussines.deleteStudent(id)) {
                System.out.println("Xóa thành công!");
            } else {
                System.out.println("Xóa thất bại!");
            }
        } else {
            System.out.println("Hủy xóa.");
        }
    }

    public void searchStudent() {
        System.out.println("\n--- Tìm kiếm học viên ---");
        System.out.print("Nhập từ khóa: ");
        String keyword = input.nextLine();
        List<User> users = userBussines.searchStudentByName(keyword);
        if (users.isEmpty()) {
            System.out.println("Không tìm thấy học viên!");
        } else {
            users.forEach(System.out::println);
        }
    }


    public void sortStudents() {
        System.out.println("\n--- Sắp xếp học viên ---");
        System.out.println("1. Sắp xếp theo ID");
        System.out.println("2. Sắp xếp theo Tên");
        System.out.print("Chọn kiểu sắp xếp (1-2): ");
        int sortChoice;

        try {
            sortChoice = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Lựa chọn không hợp lệ!");
            return;
        }

        String orderBy;
        boolean asc;

        if (sortChoice == 1) {
            orderBy = "id";
            System.out.println("1. Tăng dần theo ID");
            System.out.println("2. Giảm dần theo ID");
            System.out.print("Chọn (1-2): ");
            asc = "1".equals(input.nextLine());
        } else if (sortChoice == 2) {
            orderBy = "name";
            System.out.println("1. Tăng dần theo Name");
            System.out.println("2. Giảm dần theo Name");
            System.out.print("Chọn (1-2): ");
            asc = "1".equals(input.nextLine());
        } else {
            System.out.println("Lựa chọn không hợp lệ!");
            return;
        }

        List<User> users = userBussines.sortStudent(orderBy, asc);
        if (users.isEmpty()) {
            System.out.println("Không có học viên nào để sắp xếp.");
        } else {
            System.out.println("\n--- Danh sách học viên sau khi sắp xếp ---");
            users.forEach(System.out::println);
        }
    }
}

