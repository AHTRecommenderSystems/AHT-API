package com.aht.api.evaluator;

import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;

/**
 * Evaluator should implement two different distance calculus for items and for users,
 * comparing its characteristics and events, respectively.
 * Both methods should return a normalized value between 0 an 1 describing a distance.
 */
public interface Evaluator {
    public double getEvaluationForItems(Item first, Item second);
    public double getEvaluationForUsers(User first, User second);
}
