package com.aht.api.model.node;

import java.util.Set;
import com.aht.api.model.relationship.Event;
import com.aht.api.model.relationship.Neighbor;

public interface User {
	public Object getId();
	public String getName();
	public void setName(String name);
	public Set<Neighbor> getModelNeighbors();
	public Set<Event> getModelEvents();
}
