package org.manu.springjpa.compundid;

import org.junit.Test;
import org.manu.springjpa.PersistenceTests;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class CompoundIdTest extends PersistenceTests {

    @Test
    @Transactional
    public void should_insert_dept_and_its_project(){
        // Given
        String deptName = "paymets";
        long deptId = 10L;
        Department dept = new Department(deptId, deptName);
        String projectName = "bank withdrawal";
        Project prj = new Project(projectName, dept);

        // When
        em.persist(dept);
        em.persist(prj);
        em.flush();
        em.clear();

        // Then
//        Department department = em.find(Department.class, deptId);
//        System.out.println("Dept = " + department);
//        assertThat(department, notNullValue());

        Project project = em.find(Project.class, new ProjectId(projectName, deptId));
        System.out.println("Project = " + project);
        assertThat(project, notNullValue());
    }
}
