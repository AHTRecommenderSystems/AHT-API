package com.aht.api.predictor;

import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;

/**
 * Created by azu on 24/04/16.
 */
public class ItemPredictorNeo4j implements ItemPredictor {
    public Object predictValue(User user, Item item) {
        //TODO: Create item prediction value for NEo4j implementation
        /* Get user events
        *  For Each: Get event value
        *  Search for most similar items in events
        *  Analyse and return value for current item
        */
        return null;
    }
}
