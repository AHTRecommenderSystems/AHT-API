package com.aht.api.recommender;

import java.text.Collator;
import java.util.*;

import com.aht.api.evaluator.Evaluator;
import com.aht.api.evaluator.ManhattanLength;
import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;
import com.aht.api.model.node.Characteristic;

public class ItemRecommenderCalculatedSimilitude implements ItemRecommender {
	private ManhattanLength evaluator = new ManhattanLength();
	public List<Item> getTopNRecommendationByItem(Item item, int N) {
		List<Wrapper> wrappedRecommendations = new ArrayList<Wrapper>();
		for (Characteristic characteristic : item.getModelCharacteristics()) {
			for(Item neighbor: characteristic.getModelItems()){
				double value = evaluator.getEvaluationForItems(item, neighbor);
				Wrapper wrapper = new Wrapper(neighbor, value);
				wrappedRecommendations.add(wrapper);
			}
		}
		Collections.sort(wrappedRecommendations, new Comparator<Wrapper>() {
			public int compare(Wrapper wrapper1, Wrapper wrapper2)
			{
				if (wrapper1.getValue() < wrapper2.getValue()) return -1;
				if (wrapper1.getValue() > wrapper2.getValue()) return 1;
				return 0;
			}
		});

		List topNRecommendations = new LinkedList<>();
		for(Wrapper wrapper : wrappedRecommendations){
			topNRecommendations.add(wrapper.getItem());
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

	private class Wrapper {
		private double value;
		private Item item;

		public Wrapper(Item item, double value) {
			this.item = item;
			this.value = value;
		}

		public double getValue() {
			return value;
		}

		public void setValue(double value) {
			this.value = value;
		}

		public Item getItem() {
			return item;
		}

		public void setItem(Item item) {
			this.item = item;
		}
	}
}
