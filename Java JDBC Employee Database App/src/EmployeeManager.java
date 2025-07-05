import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {
    private Connection conn;

    public EmployeeManager(Connection conn) {
        this.conn = conn;
    }

    public void addEmployee(Employee emp) throws SQLException {
        String sql = "INSERT INTO employees (name, email, salary) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, emp.getName());
        ps.setString(2, emp.getEmail());
        ps.setDouble(3, emp.getSalary());
        ps.executeUpdate();
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Employee emp = new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getDouble("salary")
            );
            list.add(emp);
        }

        return list;
    }

    public void updateEmployee(Employee emp) throws SQLException {
        String sql = "UPDATE employees SET name=?, email=?, salary=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, emp.getName());
        ps.setString(2, emp.getEmail());
        ps.setDouble(3, emp.getSalary());
        ps.setInt(4, emp.getId());
        ps.executeUpdate();
    }

    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}

