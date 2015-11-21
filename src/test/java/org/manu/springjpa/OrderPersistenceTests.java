package org.manu.springjpa;

import org.junit.Test;
import org.manu.springjpa.derivedId.Employee;
import org.manu.springjpa.derivedId.EmployeeHistory;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderPersistenceTests extends PersistenceTests {
    @Test
    @Transactional
    public void testSaveOrderWithItems() throws Exception {
        Order order = new Order();
        order.addItem(new Item());
        em.persist(order);
        em.flush();
        assertNotNull(order.getId());
    }

    @Test
    @Transactional
    public void testSaveAndGet() throws Exception {
        Order order = new Order();
        order.getItems().add(new Item());
        em.persist(order);
        em.flush();
        // Otherwise the query returns the existing order (and we didn't set the
        // parent in the item)...
        em.clear();
        Order other = (Order) em.find(Order.class, order.getId());
        assertEquals(1, other.getItems().size());
        assertEquals(other, other.getItems().iterator().next().getOrder());
    }

    @Test
    @Transactional
    public void testSaveAndFind() throws Exception {
        Order order = new Order();
        Item item = new Item();
        item.setProduct("foo");
        order.getItems().add(item);
        em.persist(order);
        em.flush();
        // Otherwise the query returns the existing order (and we didn't set the
        // parent in the item)...
        em.clear();
        Order other = (Order) em
                .createQuery(
                        "select o from Order o join o.items i where i.product=:product")
                .setParameter("product", "foo").getSingleResult();
        assertEquals(1, other.getItems().size());
        assertEquals(other, other.getItems().iterator().next().getOrder());
    }

}
