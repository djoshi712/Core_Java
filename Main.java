class Employee {
    private int employeeId;
    private String name;
    private double salary;

    public Employee(int employeeId, String name, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
    }

    // Package-private or public method with role check
    public void setSalary(double newSalary, String role) {
        if ("manager".equals(role) && newSalary >= 0) {
            this.salary = newSalary;
            System.out.println("Salary updated to: " + this.salary);
        } else {
            System.out.println("Access Denied for role: " + role);
        }
    }

    public double getSalary() {
        return salary;
    }
}

class Manager {
    private String role = "manager";

    public void updateSalary(Employee emp, double newSalary) {
        emp.setSalary(newSalary, role);
    }
}

class Auditor {
    private String role = "auditor";

    public void tryToUpdateSalary(Employee emp, double newSalary) {
        emp.setSalary(newSalary, role); // Will fail due to access control
    }
}

public class Main {
    public static void main(String[] args) {
        Employee emp = new Employee(101, "Alice", 50000);
        Manager manager = new Manager();
        Auditor auditor = new Auditor();

        System.out.println("\nAuditor tries to update salary:");
        auditor.tryToUpdateSalary(emp, 60000);  // ❌ Should be denied

        System.out.println("\nManager tries to update salary:");
        manager.updateSalary(emp, 60000);       // ✅ Should work

        System.out.println("\nFinal Salary: " + emp.getSalary());
    }
}
