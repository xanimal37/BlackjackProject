package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;

public class BlackJackHand extends Hand {

	// tracks the total value of the hand
	int totalValue = 0;
	// status - has hand busted?
	boolean hasBusted = false;
	//status - has hand hit 21?
	boolean hasTwentyOne = false;

	// constructor creates list that will hold hand
	public BlackJackHand() {
		cards = new ArrayList<Card>();
	}

	// getters
	public int getTotalValue() {
		return totalValue;
	}

	public boolean getHasBusted() {
		return hasBusted;
	}
	
	public boolean getHasTwentyOne() {
		return hasTwentyOne;
	}

	// need to track the total
	@Override
	public void addCard(Card card) {
		// check the total before the card is added
		if (isUnderTwentyOne(card)) {
			cards.add(card);
			totalValue += card.getValue();
			if(totalValue==21) {
				hasTwentyOne = true;
			}
		} else if(totalValue>21){
			bust();
		}
	}

	// in blackjack will never remove a card
	@Override
	public Card removeCard() {
		// TODO Auto-generated method stub
		return null;
	}

	// this method will check the total any time a card is added
	private boolean isUnderTwentyOne(Card card) {
		if ((totalValue + card.getValue()) <= 21) {
			return true;
		} else {
			return false;
		}
	}

	// message and change busted status for hand
	private void bust() {
		// System.out.println("Busted!");
		hasBusted = true;
	}

}
