package com.skilldistillery.blackjack.entities;
import java.util.ArrayList;
import java.util.List;

//this class is for a generic hand of cards
//can be used for ANY type of hand - can not be instantiated
public abstract class Hand {

	protected List<Card> cards;

	public abstract void addCard(Card card);

	public abstract Card removeCard();

	// get cards
	// returns a copy of cards
	public List<Card> getCards() {
		//copy to new array list
		List<Card> cardsCopy = new ArrayList<>();
		cardsCopy.addAll(cards);
		return cardsCopy;
	}

	// clear hand/empty hand
	// clears the list of cards
	public void clearHand() {
		cards.clear();
	}

	@Override
	public String toString() {
		String cardsInHand = "";
		for (Card card : cards) {
			cardsInHand += card.toString() + " | ";
		}
		return cardsInHand;
	}

}
