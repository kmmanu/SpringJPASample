package org.manu.springjpa.compundid;

import javax.persistence.*;

@Entity
@IdClass(ProjectId.class)
public class Project {

    @Id
    private String name;

    @Id
    @ManyToOne
    private Department dept;

    public Project(String name, Department dept) {
        this.name = name;
        this.dept = dept;
    }

    public Project() {
    }

    public String getName() {
        return name;
    }

    public Department getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", dept=" + dept +
                '}';
    }
}
