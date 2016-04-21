package com.aht.api.model.node;

import com.aht.api.model.relationship.Event;
import com.aht.api.model.relationship.Neighbor;

import java.util.Set;

public interface User {
	public Object getId();
	public String getName();
	public void setName(String name);
	public Set<Neighbor> getNeighbors();
	public Set<Event> getEvents();
}
