package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;

public class BlackJackHand extends Hand {

	// tracks the total value of the hand
	int totalValue = 0;

	// constructor creates list that will hold hand
	public BlackJackHand() {
		cards = new ArrayList<Card>();
	}

	// getters
	public int getTotalValue() {
		return totalValue;
	}
	
	//reset total value
	public void resetValue() {
		totalValue =0;
	}
	
	//flip all cards to show
	public void turnCardsFaceUp() {
		for(Card c : cards) {
			if(!c.getIsFaceUp()) {
				c.flip();
			}
		}
	}

	// need to track the total
	@Override
	public void addCard(Card card) {
		// check the total before the card is added
		cards.add(card);
		totalValue+=card.getValue();
		
	}

	// in blackjack will never remove a card
	@Override
	public Card removeCard() {
		// TODO Auto-generated method stub
		return null;
	}

}
