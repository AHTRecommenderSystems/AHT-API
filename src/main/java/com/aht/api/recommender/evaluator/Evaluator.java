package com.aht.api.recommender.evaluator;

import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;

/**
 * Created by azu on 20/04/16.
 */
public interface Evaluator {
    public Object getEvaluationForItems(Item first, Item second);
    public Object getEvaluationForUsers(User first, User second);
}
