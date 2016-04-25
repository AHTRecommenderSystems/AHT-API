package com.aht.api.recommender.evaluator;

import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;

public interface Evaluator {
    public Object getEvaluationForItems(Item first, Item second);
    public Object getEvaluationForUsers(User first, User second);
}
