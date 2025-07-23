import Validate.Validate;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("=== Đăng Nhập Hệ Thống ===");
            System.out.print("Nhập Username: ");
            String username = input.nextLine();
            System.out.print("Nhập Password: ");
            String password = input.nextLine();
            if (!Validate.validateInput(username, password)) {
                System.out.println("Dữ liệu không hợp lệ. Vui lòng thử lại!");
                return;
            }

            if (Validate.checkUserFromDB(username, password)) {
                System.out.println("Đăng nhập thành công!");
                break;

            } else {
                System.out.println("Sai Username hoặc Password!");
            }
        } while (true);
    }
}
