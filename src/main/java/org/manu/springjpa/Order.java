package org.manu.springjpa;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;

@Entity
@Table(name="T_ORDER")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String customer;
	
	@OneToMany(cascade=CascadeType.ALL)
	private Collection<Item> items = new LinkedHashSet<Item>();

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}

	public void addItem(Item item){
		item.setOrder(this);
		items.add(item);
	}

	public Long getId() {
		return id;
	}
}
