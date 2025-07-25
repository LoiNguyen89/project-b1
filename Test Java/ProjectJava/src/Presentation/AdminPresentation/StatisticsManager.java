package Presentation.AdminPresentation;

import Bussines.StatisticsBussines;
import Bussines.IMP.StatisticsBussinesIMP;
import Entity.CourseStats;
import Presentation.AdminMenu;
import Validate.Validate;

import java.util.List;
import java.util.Scanner;

public class StatisticsManager {
    private final Scanner input = new Scanner(System.in);
    private final StatisticsBussines statisticsBussines;

    public StatisticsManager() {
        statisticsBussines = new StatisticsBussinesIMP();
        menu();
    }

    private void menu() {
        int choice;
        do {
            System.out.println("\n===== THỐNG KÊ =====");
            System.out.println("1. Tổng số khóa học & tổng số học viên");
            System.out.println("2. Tổng số học viên theo từng khóa học");
            System.out.println("3. Top 5 khóa học đông học viên nhất");
            System.out.println("4. Liệt kê các khóa học có trên 10 học viên");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");

            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1 -> showTotals();
                case 2 -> showStudentsPerCourse();
                case 3 -> showTopCourses();
                case 4 -> showCoursesOver10();
                case 5 -> {
                    System.out.println("Thoát thống kê.");
                    new AdminMenu();
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (true);
    }

    private void showTotals() {
        int[] totals = statisticsBussines.getTotalCounts();
        System.out.println("\n--- Tổng quan hệ thống ---");
        System.out.println("Tổng số khóa học : " + totals[0]);
        System.out.println("Tổng số học viên : " + totals[1]);
    }

    private void showStudentsPerCourse() {
        List<CourseStats> list = statisticsBussines.getStudentsPerCourse();
        if (list.isEmpty()) {
            System.out.println("Không có dữ liệu.");
            return;
        }
        System.out.println("\n--- Số học viên theo từng khóa ---");
        printStatsTable(list);
    }

    private void showTopCourses() {
        int limit = 5;
        System.out.print("Nhập giới hạn Top (bỏ trống dùng 5): ");
        String inp = input.nextLine();
        if (!inp.trim().isEmpty()) {
            if (!Validate.validatePositiveInt(inp, "Giới hạn Top")) return;
            limit = Integer.parseInt(inp.trim());
        }
        List<CourseStats> list = statisticsBussines.getTopCourses(limit);
        if (list.isEmpty()) {
            System.out.println("Không có dữ liệu.");
            return;
        }
        System.out.println("\n--- Top " + limit + " khóa học đông học viên ---");
        printStatsTable(list);
    }

    private void showCoursesOver10() {
        int threshold = 10;
        List<CourseStats> list = statisticsBussines.getCoursesOverThreshold(threshold);
        if (list.isEmpty()) {
            System.out.println("Không có khóa học nào > " + threshold + " học viên.");
            return;
        }
        System.out.println("\n--- Khóa học có trên " + threshold + " học viên ---");
        printStatsTable(list);
    }

    private void printStatsTable(List<CourseStats> list) {
        System.out.printf("%-8s %-30s %-15s%n", "ID", "Tên khóa học", "Số học viên");
        for (CourseStats cs : list) {
            System.out.printf("%-8d %-30s %-15d%n",
                    cs.getCourseId(),
                    cs.getCourseName(),
                    cs.getStudentCount());
        }
    }
}
