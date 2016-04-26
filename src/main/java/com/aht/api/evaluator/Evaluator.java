package com.aht.api.evaluator;

import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;

public interface Evaluator {
    public double getEvaluationForItems(Item first, Item second);
    public double getEvaluationForUsers(User first, User second);

}
