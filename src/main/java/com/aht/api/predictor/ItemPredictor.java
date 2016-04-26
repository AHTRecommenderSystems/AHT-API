package com.aht.api.predictor;

import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;

/**
 * Created by azu on 24/04/16.
 */
public interface ItemPredictor {
    public Object predictValue(User user, Item item);
}
