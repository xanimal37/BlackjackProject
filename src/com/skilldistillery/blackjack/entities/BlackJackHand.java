package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;

public class BlackJackHand extends Hand {

	// constructor creates list that will hold hand
	public BlackJackHand() {
		cards = new ArrayList<Card>();
	}

	// getters
	public int getBlackJackValue() {
		int total = 0;
		for (Card c : cards) {
			total += c.getValue();
		}
		return total;
	}

	// flip all cards to show face up
	public void turnCardsFaceUp() {
		for (Card c : cards) {
			if (!c.getIsFaceUp()) {
				c.flip();
			}
		}
	}

	// add a card to the hand
	public void addCard(Card card) {
		cards.add(card);
		if (this.getBlackJackValue() > 21) {
			swapAceValue();
		}
	}

	// this method will find an ace to change to one if going over
	private void swapAceValue() {
		for (Card c : cards) {
			if (c.getValue() == 11) { // only aces will have this value so no need check rank
				c.setValue(1);
			}
		}
	}

	// remove a card
	@Override
	public Card removeCard() {
		// TODO Auto-generated method stub
		return null;
	}

}
