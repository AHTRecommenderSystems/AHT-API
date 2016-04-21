package com.aht.api.model.relationship;

import com.aht.api.model.node.User;

public interface Neighbor {
	public Object getId();
	public Object getSimilitude();
	public void setSimilitude(Object similitude);
	public User getFirstUser();
	public User getSecondUser();
}
