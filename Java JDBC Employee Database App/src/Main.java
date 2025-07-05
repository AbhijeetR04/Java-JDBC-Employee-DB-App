import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            Scanner sc = new Scanner(System.in);
            EmployeeManager manager = new EmployeeManager(conn);

            while (true) {
                System.out.println("\n1. Add Employee\n2. View Employees\n3. Update Employee\n4. Delete Employee\n5. Exit");
                System.out.print("Enter Your Choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Name: ");
                        String name = sc.next();
                        System.out.print("Email: ");
                        String email = sc.next();
                        System.out.print("Salary: ");
                        double salary = sc.nextDouble();

                        manager.addEmployee(new Employee(name, email, salary));
                        System.out.println("Employee added.");
                    }
                    case 2 -> {
                        List<Employee> list = manager.getAllEmployees();
                        System.out.println("\nID | Name | Email | Salary");
                        for (Employee e : list) {
                            System.out.printf("%d | %s | %s | %.2f\n",
                                    e.getId(), e.getName(), e.getEmail(), e.getSalary());
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter ID to update: ");
                        int id = sc.nextInt();
                        System.out.print("New Name: ");
                        String name = sc.next();
                        System.out.print("New Email: ");
                        String email = sc.next();
                        System.out.print("New Salary: ");
                        double salary = sc.nextDouble();

                        manager.updateEmployee(new Employee(id, name, email, salary));
                        System.out.println("Employee updated.");
                    }
                    case 4 -> {
                        System.out.print("Enter ID to delete: ");
                        int id = sc.nextInt();
                        manager.deleteEmployee(id);
                        System.out.println("Employee deleted.");
                    }
                    case 5 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
