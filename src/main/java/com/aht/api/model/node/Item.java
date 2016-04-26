package com.aht.api.model.node;

import com.aht.api.model.relationship.Affinity;
import com.aht.api.model.relationship.Event;
import java.util.Set;
import com.aht.api.model.relationship.Affinity;

public interface Item {
	public Object getId();
	public String getName();
	public void setName(String name);
	public Set<Event> getEvents();
	public Set<Characteristic> getModelCharacteristics();
	public Set<Affinity> getModelAffinities();
}
