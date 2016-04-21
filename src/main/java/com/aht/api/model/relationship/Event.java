package com.aht.api.model.relationship;

import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;

public interface Event {
	public Object getId();
	public String getOn();
	public void setOn(String on);
	public Item getItem();
	public User getUser();
}
