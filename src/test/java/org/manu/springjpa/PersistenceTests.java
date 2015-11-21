package org.manu.springjpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.manu.springjpa.derivedId.Employee;
import org.manu.springjpa.derivedId.EmployeeHistory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class PersistenceTests {

	@PersistenceContext
	protected EntityManager em;
}
