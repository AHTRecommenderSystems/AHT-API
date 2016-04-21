package com.aht.api.model.relationship;

import com.aht.api.model.node.Item;

public interface Affinity {
	public Object getId();
	public float getSimilitude();
	public void setSimilitude(float similitude);
	public Item getFirstItem();
	public Item getSecondItem();
}
