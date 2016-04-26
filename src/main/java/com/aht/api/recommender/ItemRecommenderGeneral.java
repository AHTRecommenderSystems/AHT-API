package com.aht.api.recommender;

import java.util.Set;
import java.util.HashSet;
import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;
import com.aht.api.model.node.Characteristic;

public class ItemRecommenderGeneral implements ItemRecommender {

	public Set<Item> getTopNRecommendationByItem(Item item, int N) {
		Set<Item> topNRecommendations = new HashSet<Item>();
		for (Characteristic characteristic : item.getModelCharacteristics()) {
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
