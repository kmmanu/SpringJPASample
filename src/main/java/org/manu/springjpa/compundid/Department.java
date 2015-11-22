package org.manu.springjpa.compundid;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Department {
    @Id
    private Long id;

    private String name;

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
