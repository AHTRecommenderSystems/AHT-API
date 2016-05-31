package com.aht.api.predictor;

import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;
import com.aht.api.model.relationship.Affinity;
import com.aht.api.model.relationship.Event;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by azu on 24/04/16.
 */
public class ItemPredictorRetrievedSimilitude implements ItemPredictor {
    public double predictValue(User user, Item item) {
        //TODO: Create item prediction value for NEo4j implementation
        /* Get user events
        *  For Each: Get event value
        *  Search for most similar items in events
        *  Analyse and return value for current item
        */
        List<Event> events = user.getModelEvents();
        LinkedList<Wrapper> wrappers = new LinkedList<>();
        for(Event event : events){
            double value = event.getModelValue();
            List<Affinity> affinities = event.getModelItem().getModelAffinities();
            for(Affinity affinity:affinities){
                if(affinity.getFirstModelItem().equals(event.getModelItem()) && affinity.getSecondModelItem().equals(item)){
                    wrappers.add(new Wrapper(event.getModelItem(), value, affinity.getSimilitudeValue()));
                } else if(affinity.getFirstModelItem().equals(item) && affinity.getSecondModelItem().equals(event.getModelItem())){
                    wrappers.add(new Wrapper(event.getModelItem(), value, affinity.getSimilitudeValue()));
                }
            }
        }
        Collections.sort(wrappers, new Comparator<Wrapper>() {
            @Override
            public int compare(Wrapper o1, Wrapper o2) {
                if(o1.getDistance() < o2.getDistance()){
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        return wrappers.get(0).getValue();
    }

    private class Wrapper{
        private Item item;
        private double value;
        private double distance;

        private Wrapper(Item item, double value, double distance){
            this.item = item;
            this.value = value;
            this.distance = distance;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }
    }
}
