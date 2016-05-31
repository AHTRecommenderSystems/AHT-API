package com.aht.api.recommender;

import java.util.*;

import com.aht.api.evaluator.ManhattanLength;
import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;
import com.aht.api.model.relationship.Event;
import com.aht.api.model.relationship.Affinity;
import com.aht.api.model.relationship.Neighbor;


public class ItemRecommenderRetrievedSimilitude implements ItemRecommender{
    public List<Item> getTopNRecommendationByItem(Item item, int N) {
        List<Item> topNRecommendations = new LinkedList<Item>();
        List<Affinity> affinities = item.getModelAffinities();
        Collections.sort(affinities, new Comparator<Affinity>() {
            @Override
            public int compare(Affinity o1, Affinity o2) {
                if(o1.getSimilitudeValue() < o2.getSimilitudeValue()){
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        int i = 0;
        for (Affinity affinity: affinities) {
            Item temp = affinity.getFirstModelItem();
            if(temp.getModelId() == item.getModelId()){
                temp = affinity.getSecondModelItem();
            }
            if(topNRecommendations.indexOf(temp) == -1){
                i++;
                topNRecommendations.add(temp);
            }
            if(i == N){
                break;
            }
        }
        return topNRecommendations;
    }

    public List<Item> getTopNRecommendationByUser(User user, int N) {
        ManhattanLength manhattanLength = new ManhattanLength();
        List<Neighbor> neighbors = user.getModelNeighbors();
        Collections.sort(neighbors, new Comparator<Neighbor>() {
            @Override
            public int compare(Neighbor o1, Neighbor o2) {
                if(o1.getSimilitudeValue() < o2.getSimilitudeValue()){
                    return -1;
                } else {
                    return 1;
                }
            }
        });
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

        List<Item> topNRecommendations = new LinkedList<Item>();
        for(User u: users){
            // getList of items by user event sorted by value
            List<Event> events = u.getModelEvents();
            Collections.sort(events, new Comparator<Event>() {
                @Override
                public int compare(Event o1, Event o2) {
                    if(o1.getModelValue() > o2.getModelValue()){
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });
            int i = 0;
            for(Event e: events){
                Item temp = e.getModelItem();
                if(topNRecommendations.indexOf(temp) == -1){
                    i++;
                    topNRecommendations.add(temp);
                }
                if(i == N)
                    break;
            }
        }
        return topNRecommendations;
    }

    public List<Item> getTopNRecommendationByItemSet(List<Item> items, int N) {
        List<Item> recommendations = new LinkedList<>();
        LinkedList<Wrapper> wrappers = new LinkedList<>();
        for(Item item: items){
            List<Item> partialRecommendations = getTopNRecommendationByItem(item, N);
            for(Item reco : partialRecommendations){
                List<Affinity> modelAffinities = reco.getModelAffinities();
                for(Affinity affinity : modelAffinities){
                    if(affinity.getFirstModelItem().equals(item) && affinity.getSecondModelItem().equals(reco) || affinity.getFirstModelItem().equals(reco) && affinity.getSecondModelItem().equals(item)){
                        double distance = affinity.getSimilitudeValue();
                        wrappers.add(new Wrapper(reco, distance));
                    }
                }
            }
        }
        Collections.sort(wrappers, new Comparator<Wrapper>() {
            @Override
            public int compare(Wrapper o1, Wrapper o2) {
                if(o1.getDistance() < o2.getDistance()){
                    return -1;
                } else {
                    return  1;
                }
            }
        });
        int i = 0;
        for(Wrapper wrapper : wrappers){
            Item temp = wrapper.getItem();
            if(recommendations.indexOf(temp) == -1){
                i++;
                recommendations.add(temp);
            }
            if(i == N)
                break;
        }
        return recommendations;
    }

    public Item getUniqueRecommendationByItem(Item item) {
        return getTopNRecommendationByItem(item,1).get(0);
    }

    public Item getUniqueRecommendationByUser(User user) {
        return getTopNRecommendationByUser(user, 1).get(0);
    }

    private class Wrapper{
        private Item item;
        private double value;
        private double distance;

        private Wrapper(Item item, double distance){
            this.item = item;
            this.distance = distance;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }
    }

}
