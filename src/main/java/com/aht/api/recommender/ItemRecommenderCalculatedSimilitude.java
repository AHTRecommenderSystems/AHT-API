package com.aht.api.recommender;

import java.text.Collator;
import java.util.*;

import com.aht.api.evaluator.Evaluator;
import com.aht.api.evaluator.ManhattanLength;
import com.aht.api.model.node.Item;
import com.aht.api.model.node.User;
import com.aht.api.model.node.Characteristic;
import com.aht.api.model.relationship.Event;
import org.springframework.stereotype.Component;

@Component
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
		int i = 0;
		for(Wrapper wrapper : wrappedRecommendations){
			i++;
			topNRecommendations.add(wrapper.getItem());
			if(i == N){
				break;
			}
		}
		return topNRecommendations;
	}

	public List<Item> getTopNRecommendationByUser(User user, int N) {
		List<Item> recommendations = new LinkedList<>();
		LinkedList<WrapperUser> wrapperUsers = new LinkedList<>();
		List<Event> events = user.getModelEvents();
		for(Event event: events){
			List<Event> modelEvents = event.getModelItem().getModelEvents();
			for(Event event1: modelEvents){
				User neighbor = event1.getModelUser();
				double distance = evaluator.getEvaluationForUsers(user, neighbor);
				wrapperUsers.add(new WrapperUser(neighbor, distance));
			}
		}
		Collections.sort(wrapperUsers, new Comparator<WrapperUser>() {
			@Override
			public int compare(WrapperUser o1, WrapperUser o2) {
				if(o1.getValue() < o2.getValue()){
					return -1;
				} else {
					return 1;
				}
			}
		});
		for(WrapperUser wrapperUser : wrapperUsers){
			List<Event> modelEvents = wrapperUser.getUser().getModelEvents();
			Collections.sort(modelEvents, new Comparator<Event>() {
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
			for(Event event:modelEvents){
				/* If current value is equals to biggest one or at least 1 unit low */
				if(event.getModelValue() == modelEvents.get(0).getModelValue() || event.getModelValue() == modelEvents.get(0).getModelValue() - 1){
					i++;
					recommendations.add(event.getModelItem());
					if(i == N){
						break;
					}
				}
			}
		}

		return recommendations;
	}

	public List<Item> getTopNRecommendationByItemSet(List<Item> items, int N) {
		List<Item> recommendations = new LinkedList<>();
		LinkedList<Wrapper> wrappers = new LinkedList<>();
		int i = 0;
		for(Item item : items){
			List<Item> partialRecommendations = getTopNRecommendationByItem(item, N);
			for(Item reco : partialRecommendations){
				double distance = evaluator.getEvaluationForItems(item, reco);
				wrappers.add(new Wrapper(reco, distance));
			}
		}
		Collections.sort(wrappers, new Comparator<Wrapper>() {
			@Override
			public int compare(Wrapper o1, Wrapper o2) {
				if(o1.getValue() < o2.getValue()){
					return -1;
				} else {
					return 1;
				}
			}
		});
		for(Wrapper wrapper: wrappers){
			i++;
			recommendations.add(wrapper.getItem());
			if(i == N){
				break;
			}
		}
		return recommendations;
	}

	public Item getUniqueRecommendationByItem(Item item) {
		return getTopNRecommendationByItem(item, 1).get(0);
	  }

	public Item getUniqueRecommendationByUser(User user) {
		  return getTopNRecommendationByUser(user, 1).get(0);
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

	private class WrapperUser{
		private double value;
		private User user;

		private WrapperUser(User user, double value){
			this.user = user;
			this.value = value;
		}

		public double getValue() {
			return value;
		}

		public void setValue(double value) {
			this.value = value;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
	}
}
