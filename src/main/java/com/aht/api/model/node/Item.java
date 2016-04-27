package com.aht.api.model.node;

import com.aht.api.model.relationship.Affinity;
import com.aht.api.model.relationship.Event;
import java.util.List;

public interface Item {
	public Object getModelId();
	public List<Event> getModelEvents();
	public List<Characteristic> getModelCharacteristics();
	public List<Affinity> getModelAffinities();
}
