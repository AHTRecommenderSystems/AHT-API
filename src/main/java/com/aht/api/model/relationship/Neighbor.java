package com.aht.api.model.relationship;

import com.aht.api.model.node.User;

public interface Neighbor {
	public double getSimilitudeValue();
	public User getFirstModelUser();
	public User getSecondModelUser();
}
