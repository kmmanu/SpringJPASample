package org.manu.springjpa.derivedid;

import org.junit.Test;
import org.manu.springjpa.PersistenceTests;
import org.manu.springjpa.derivedId.Employee;
import org.manu.springjpa.derivedId.EmployeeHistory;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class DerivedIdTest extends PersistenceTests{

    @Test
    @Transactional
    public void should_update_employee_history_with_same_id_of_employee(){
        // Given
        Employee e = new Employee(200L, "John");
        EmployeeHistory eh = new EmployeeHistory();
        e.setEmployeeHistory(eh);

        // When
        em.persist(e);
        em.flush();
        em.clear();

        // Then
        EmployeeHistory employeeHistory = em.find(EmployeeHistory.class, 200L);
        assertThat(employeeHistory, notNullValue());
        System.out.println(employeeHistory);
    }

}
