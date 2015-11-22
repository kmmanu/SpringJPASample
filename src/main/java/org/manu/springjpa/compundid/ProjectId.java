package org.manu.springjpa.compundid;

import java.io.Serializable;

public class ProjectId  implements Serializable{
    /**
     * project name
     */
    private String name;

    private Long dept;

    public ProjectId(String name, Long dept) {
        this.name = name;
        this.dept = dept;
    }

    ProjectId() {
    }

    public String getName() {
        return name;
    }

    public Long getDept() {
        return dept;
    }
}
