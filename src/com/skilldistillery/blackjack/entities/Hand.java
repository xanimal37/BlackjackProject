package com.skilldistillery.blackjack.entities;

import java.util.List;

//this class is for a generic hand of cards
//can be used for ANY type of hand - can not be instantiated
public abstract class Hand {

	protected List<Card> cards;

	public abstract void addCard(Card card);

	public abstract Card removeCard();

	@Override
	public String toString() {
		String cardsInHand = "";
		for (Card card : cards) {
			cardsInHand += card.toString();
		}
		return cardsInHand;
	}

}