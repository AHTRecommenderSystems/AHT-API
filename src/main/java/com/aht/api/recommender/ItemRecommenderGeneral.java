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
    @Override
    public Set<Item> getTopNRecommendationByItem(Item item, int N) {
        Set<Item> topNRecommendations = new HashSet<Item>();
        for(Characteristic characteristic : item.getCharacteristics()){
            System.out.println(characteristic.getItems());
        }
        return topNRecommendations;
    }

    @Override
    public Set<Item> getTopNRecommendationByUSer(User user, int N) {
        return null;
    }

    @Override
    public Set<Item> getTopNRecommendationByItemSet(Set<Item> items, int N) {
        return null;
    }

    @Override
    public Set<Item> getUniqueRecommendationByItem(Item item, int N) {
        return null;
    }

    @Override
    public Set<Item> getTopNRecommendations(int N) {
        return null;
    }
}
