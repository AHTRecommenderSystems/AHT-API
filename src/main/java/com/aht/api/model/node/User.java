package com.aht.api.model.node;

import com.aht.api.model.relationship.Event;
import com.aht.api.model.relationship.Neighbor;

import java.util.List;

public interface User {
	public Object getModelId();
	public List<Neighbor> getModelNeighbors();
	public List<Event> getModelEvents();
}
