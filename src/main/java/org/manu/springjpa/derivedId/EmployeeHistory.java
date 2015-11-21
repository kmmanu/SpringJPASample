package org.manu.springjpa.derivedId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class EmployeeHistory implements Serializable{
    @Id
    @OneToOne
    @JoinColumn(name = "EMP_ID")
    private Employee employee;
    private String fatherName;
    private String motherName;

    public Employee getEmployee() {
        return employee;
    }

    void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    @Override
    public String toString() {
        return "EmployeeHistory{" +
                "employee=" + employee +
                ", fatherName='" + fatherName + '\'' +
                ", motherName='" + motherName + '\'' +
                '}';
    }
}
