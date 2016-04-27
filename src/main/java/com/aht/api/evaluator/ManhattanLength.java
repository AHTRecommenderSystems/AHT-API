package com.aht.api.evaluator;

import com.aht.api.evaluator.dataStructure.Vector;
import com.aht.api.model.node.Characteristic;
import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;
import com.aht.api.model.relationship.Event;

import java.util.List;

/**
 * Manhattan Length
 * Creates an Evaluator to get the distance between two items or users. The most distance, the most different they are between them.
 */
public class ManhattanLength implements Evaluator{

    /**
     * Returns a integer distance between two items. The most distance, the most different they are.
     * @param firstItem
     * @param secondItem
     * @return double evaluation
     */
    public double getEvaluationForItems(Item firstItem, Item secondItem) {
        Vector vector1 = new Vector(getCharacteristicsVector(firstItem));
        Vector vector2 = new Vector(getCharacteristicsVector(secondItem));
        Vector both = new Vector(vector1.merge(vector2));
        double[] normalized1 = vector1.normalize(both);
        double[] normalized2 = vector2.normalize(both);
        int distance = 0;
        for(int i=0; i < normalized1.length; i++){
            distance += Math.abs(normalized1[i] - normalized2[i]);
        }
        double result = ((double)both.size() - (double) distance) / (double) both.size();
        System.out.println("Distancia entre los dos: " + result);
        return result;
    }

    /**
     * Gets an integer distance between two users. The most distance, the most different they are.
     * @param firstUser
     * @param secondUser
     * @return double evaluation
     */
    public double getEvaluationForUsers(User firstUser, User secondUser){
        Vector vector1 = new Vector(getItemsVector(firstUser));
        Vector vector2 = new Vector(getItemsVector(secondUser));
        Vector both = new Vector(vector1.merge(vector2));
        int common = 0;
        for(Object value : vector1.getVector()){
            if(vector2.includes(value)){
                common += 1;
            }
        }
        double result = ((double)both.size() - (double) common) / (double) both.size();
        System.out.println("Distancia entre los dos: " + result);
        return result;
    }

    /**
     * Given an item, will return the vector of characteristics for that item.
     * @param item
     * @return Object[] characteristics identifiers
     */
    private Object[] getCharacteristicsVector(Item item){
        List<Characteristic> characteristics = item.getModelCharacteristics();
        Object[] vector = new Object[item.getModelCharacteristics().size()];

        for(int i=0; i < characteristics.size(); i++){
            vector[i] = characteristics.toArray(new Characteristic[characteristics.size()])[i].getModelId();
        }
        return vector;
    }

    /**
     * Given an user, will return the vector of items the user has interacted with.
     * @param user
     * @return Object[] items identifiers
     */
    private Object[] getItemsVector(User user){
        List<Event> events = user.getModelEvents();
        Vector vector = new Vector(new Object[events.size()]);
        for(Event event: events) {
            vector.add(event.getModelItem().getModelId());
        }
        return vector.getVector();
    }
}
