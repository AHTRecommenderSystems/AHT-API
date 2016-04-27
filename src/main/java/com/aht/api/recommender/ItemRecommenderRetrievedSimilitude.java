package com.aht.api.recommender;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import com.aht.api.evaluator.ManhattanLength;
import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;
import com.aht.api.model.relationship.Event;
import com.aht.api.model.relationship.Affinity;
import com.aht.api.model.relationship.Neighbor;


public class ItemRecommenderRetrievedSimilitude implements ItemRecommender{
    public Set<Item> getTopNRecommendationByItem(Item item, int N) {
        Set<Item> topNRecommendations = new HashSet<Item>();
        Set<Affinity> affinities = item.getModelAffinities();
        // Sort Affinities

        for (Affinity affinity: affinities) {
            Item temp = affinity.getFirstModelItem();
            if(temp.getModelId() == item.getModelId()){
                temp = affinity.getSecondModelItem();
            }
            topNRecommendations.add(temp);
        }
        return topNRecommendations;
    }

    public Set<Item> getTopNRecommendationByUser(User user, int N) {
        ManhattanLength manhattanLength = new ManhattanLength();
        Set<Neighbor> neighbors = user.getModelNeighbors();
        // TODO: Sort neighbors
        List<User> users = new ArrayList<User>();
        List<Object> values = new ArrayList<Object>();
        for (Neighbor neighbor: neighbors) {
            User temp = neighbor.getFirstModelUser();
            if(temp.getModelId() == user.getModelId()) {
                temp = neighbor.getSecondModelUser();
            }
            values.add(manhattanLength.getEvaluationForUsers(user, temp));
            users.add(temp);
        }

        Set<Item> topNRecommendations = new HashSet<Item>();
        for(User u: users){
            // getList of items by user event sorted by value
            Set<Event> events = u.getModelEvents();
            for(Event e: events){
                Item temp = e.getModelItem();
                topNRecommendations.add(temp);
            }
        }
        return topNRecommendations;
    }

    public Set<Item> getTopNRecommendationByItemSet(Set<Item> items, int N) {
        return null;
    }

    public Item getUniqueRecommendationByItem(Item item) {
        return null;
    }

    public Item getUniqueRecommendationByUser(User user) {
        return null;
    }

}
