package com.aht.api.recommender;

import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;

import java.util.Set;

/**
 * Created by azu on 20/04/16.
 */
public interface ItemRecommender {
    public Set<Item> getTopNRecommendationByItem(Item item, int N);
    public Set<Item> getTopNRecommendationByUSer(User user, int N);
    public Set<Item> getTopNRecommendationByItemSet(Set<Item> items, int N);
    public Set<Item> getUniqueRecommendationByItem(Item item, int N);
    public Set<Item> getTopNRecommendations(int N);
}
