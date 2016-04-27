package com.aht.api.model.relationship;

import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;

public interface Event {
	public Item getModelItem();
	public User getModelUser();
	public double getModelValue();
}
