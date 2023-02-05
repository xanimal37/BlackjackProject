package com.skilldistillery.blackjack.entities;

public class BJPlayer extends Player {

	// keeps taking turns
	boolean canPlay = true;
	// tied the dealer
	boolean isPush = false;
	// has 21
	boolean hasTwentyOne = false;
	// has busted
	boolean hasBusted = false;

	public BJPlayer() {
		hand = new BlackJackHand();
	}

	public boolean getCanPlay() {
		return canPlay;
	}

	public boolean getIsPush() {
		return isPush;
	}

	public boolean getHasBusted() {
		return hasBusted;
	}
	
	public void setIsPush() {
		isPush = true;
	}

	public void checkStatus() {
		int total = ((BlackJackHand) hand).getTotalValue();

		if (total > 21) {
			System.out.println(name + " BUSTED!");
			hasBusted = true;
			canPlay = false;
		} else if (total == 21) {
			hand.show();
			System.out.println(name + " BLACKJACK!");
			hasTwentyOne = true;
			canPlay = false;
		} else {

		}
	}

	public void hit(Card card) {
		System.out.println(name + " hits!");
		((BlackJackHand)hand).addCard(card);
		checkStatus();

	}

	public void stand() {
		System.out.println(name + " stands!");
		canPlay = false;
	}

	public void split() {
	}

	public int checkHand() {
		return ((BlackJackHand) hand).getTotalValue();
	}

	public void lookAtHand() {
		String statusString = "";
		if (!canPlay) {
			if (hasBusted) {
				statusString += " * BUSTED * ";
			}
			if (hasTwentyOne) {
				statusString += " * BLACKJACK * ";
			}
			if (isPush) {
				statusString += " * PUSH * ";
			}
		}
		System.out.println(name + " | " + hand + statusString);
	}

}
