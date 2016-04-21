package com.aht.api.model.node;

import java.util.Set;

public interface Characteristic {
	public Object getId();
	public String getName();
	public void setName(String name);
	public Set<Item> getItems();
}
