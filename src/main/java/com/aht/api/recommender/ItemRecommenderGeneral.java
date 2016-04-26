package com.aht.api.recommender;

import com.aht.api.model.node.Characteristic;
import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by azu on 20/04/16.
 */
public class ItemRecommenderGeneral implements ItemRecommender {
    public Set<Item> getTopNRecommendationByItem(Item item, int N) {
        Set<Item> topNRecommendations = new HashSet<Item>();
        for(Characteristic characteristic : item.getCharacteristics()){
            System.out.println(characteristic.getName() + "\t" + characteristic.getItems());
        }
        return topNRecommendations;
    }

    public Set<Item> getTopNRecommendationByUser(User user, int N) {
        return null;
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
