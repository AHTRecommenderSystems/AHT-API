package com.aht.api.model.node;

import com.aht.api.model.relationship.Affinity;

import java.util.Set;

public interface Item {
	public Object getId();
	public String getName();
	public void setName(String name);
	public Set<Characteristic> getCharacteristics();
	public Set<Affinity> getAffinities();
}
