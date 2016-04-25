package com.aht.api.model.node;

import java.util.Set;
import com.aht.api.model.relationship.Affinity;

public interface Item {
	public Object getId();
	public String getName();
	public void setName(String name);
	public Set<Characteristic> getModelCharacteristics();
	public Set<Affinity> getModelAffinities();
}
