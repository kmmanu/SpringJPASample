package org.manu.springjpa;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ContextConfiguration(locations = "classpath:/META-INF/spring/app-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PersistenceTests {

	@PersistenceContext
	protected EntityManager em;
}
