package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	private List<Card> cards;
	
	public Deck() {
		cards = new ArrayList<>();
		
		Suit[] suits = Suit.values();
		Rank[] ranks = Rank.values();
		
		for(Suit s : suits) {
			for(Rank r : ranks) {
				cards.add(new Card(r,s));
			}
		}
	}
	
	public int checkDeckSize() {
		return cards.size();
	}
	
	public Card dealCard() {
		return cards.remove(0);
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public List<Card> getCards(){
		return cards;
	}
	
	public int getNumCards() {
		return cards.size();
	}
	
}
