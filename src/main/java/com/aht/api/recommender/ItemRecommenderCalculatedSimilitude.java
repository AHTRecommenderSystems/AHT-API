package com.aht.api.recommender;

import java.util.Set;
import java.util.HashSet;

import com.aht.api.evaluator.ManhattanLength;
import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;
import com.aht.api.model.node.Characteristic;

public class ItemRecommenderCalculatedSimilitude implements ItemRecommender {
	ManhattanLength evaluator = new ManhattanLength();

	public Set<Item> getTopNRecommendationByItem(Item item, int N) {
		Set<Item> topNRecommendations = new HashSet<Item>();
		for (Characteristic characteristic : item.getModelCharacteristics()) {
			for(Item neighbor: characteristic.getModelItems()){
				double value = evaluator.getEvaluationForItems(item, neighbor);
			}
			System.out.println(characteristic + "\t" + characteristic.getModelItems());
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
