package com.aht.api.recommender.evaluator;

import com.aht.api.model.node.Characteristic;
import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;
import com.aht.api.recommender.dataStructure.Vector;

import java.util.*;

/**
 * Created by azu on 20/04/16.
 */

/**
 * Manhattan Length
 * Creates an Evaluator to get the distance between two items or users. The most distance, the most different they are between them.
 */
public class ManhattanLength implements Evaluator{
    /**
     * Returns a integer distance between two items. The most distance, the most different they are.
     * @param firstItem
     * @param secondItem
     * @return int evaluation
     */
    @Override
    public Object getEvaluationForItems(Item firstItem, Item secondItem) {
        Vector vector1 = new Vector(getCharacteristicsVector(firstItem));
        Vector vector2 = new Vector(getCharacteristicsVector(secondItem));
        Vector both = new Vector(vector1.merge(vector2));
        System.out.println(vector1 + "\n" + vector2);
        System.out.println(both);
        int common = 0;
        for(Object value : vector1.getVector()){
            if(vector2.includes(value)){
                common += 1;
            }
        }
        //double result = (double) common / (double) both.size();
        int result = both.size() - common;
        System.out.println("Distancia entre los dos: " + result);
        return result;
    }

    /**
     * Gets an integer distance between two users. The most distance, the most different they are.
     * @param firstUser
     * @param secondUser
     * @return int evaluation
     */
    @Override
    public Object getEvaluationForUsers(User firstUser, User secondUser){
        // TODO: get events from each user
        // TODO: Compare items each user has interacted with
        // TODO: For items both users had interacted with, check values.
        // TODO: Get Manhattan distance for current values.
        return null;
    }

    /**
     * Given an item, will return the vector of characteristics for that item.
     * @param item
     * @return Object[] characteristics
     */
    private Object[] getCharacteristicsVector(Item item){
        Set<Characteristic> categories = item.getCharacteristics();
        Object[] vector = new Object[item.getCharacteristics().size()];

        for(int i=0; i < categories.size(); i++){
            vector[i] = categories.toArray(new Characteristic[categories.size()])[i].getId();
        }
        return vector;
    }
}
