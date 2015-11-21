package org.manu.springjpa.derivedId;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    private Long id;

    private String name;

    /**
     * mappedBy indicate that the owner of the relationship is History.
     * If cascade type is not given you cannot persist the target type (history).
     */
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private EmployeeHistory employeeHistory;

    Employee() {
    }

    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EmployeeHistory getEmployeeHistory() {
        return employeeHistory;
    }

    /**
     * EmployeeHistory is the owner of the relationship.
     * So when the relationship is updated via Employee,
     * the owner should be notified about the relationship via setEmployee() method.
     *
     * To ensure the relationship update only via one way,
     * made the setEmployee() visibility to package private.
     */
    public void setEmployeeHistory(EmployeeHistory employeeHistory) {
        this.employeeHistory = employeeHistory;
        employeeHistory.setEmployee(this);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
