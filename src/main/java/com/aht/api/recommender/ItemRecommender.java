package com.aht.api.recommender;

import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;

import java.util.List;

public interface ItemRecommender {
    public List<Item> getTopNRecommendationByItem(Item item, int N);
    public List<Item> getTopNRecommendationByUser(User user, int N);
    public List<Item> getTopNRecommendationByItemSet(List<Item> items, int N);
    public Item getUniqueRecommendationByItem(Item item);
    public Item getUniqueRecommendationByUser(User user);
}
