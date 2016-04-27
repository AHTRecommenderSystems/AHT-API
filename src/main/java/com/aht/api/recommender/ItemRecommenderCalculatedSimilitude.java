package com.aht.api.recommender;

import java.util.LinkedList;
import java.util.List;

import com.aht.api.evaluator.Evaluator;
import com.aht.api.evaluator.ManhattanLength;
import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;
import com.aht.api.model.node.Characteristic;

public class ItemRecommenderCalculatedSimilitude implements ItemRecommender {
	private Evaluator evaluator = new ManhattanLength();
	public List<Item> getTopNRecommendationByItem(Item item, int N) {
		List<Item> topNRecommendations = new LinkedList<Item>();
		for (Characteristic characteristic : item.getModelCharacteristics()) {
			for(Item neighbor: characteristic.getModelItems()){
				double value = evaluator.getEvaluationForItems(item, neighbor);
			}
			System.out.println(characteristic + "\t" + characteristic.getModelItems());
		}
		return topNRecommendations;
	}

	public List<Item> getTopNRecommendationByUser(User user, int N) {
		return null;
	}

	public List<Item> getTopNRecommendationByItemSet(List<Item> items, int N) {
		return null;
	}

	public Item getUniqueRecommendationByItem(Item item) {
		  return null;
	  }

	public Item getUniqueRecommendationByUser(User user) {
		  return null;
	  }
}
