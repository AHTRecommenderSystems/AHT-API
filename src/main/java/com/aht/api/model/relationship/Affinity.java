package com.aht.api.model.relationship;

import com.aht.api.model.node.Item;

public interface Affinity {
	public double getSimilitudeValue();
	public Item getFirstModelItem();
	public Item getSecondModelItem();
}
