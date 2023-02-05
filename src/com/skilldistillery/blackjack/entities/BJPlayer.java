package com.skilldistillery.blackjack.entities;

public class BJPlayer extends Player {

	// keeps taking turns
	boolean canPlay = true;
	// tied the dealer
	boolean isPush = false;

	public BJPlayer() {
		hand = new BlackJackHand();
	}

	public boolean getCanPlay() {
		return canPlay;
	}

	public void setCanPlay(Boolean canPlay) {
		this.canPlay = canPlay;
	}

	public void setIsPush() {
		isPush = true;
	}

	public boolean getIsPush() {
		return isPush;
	}

	public void hit(Card card) {
		System.out.println(name + " hits!");
		hand.addCard(card);
		if (getHasBusted()) {
			System.out.println(name + " BUSTED!");
			setCanPlay(false);
		}
		if (getHasTwentyOne()) {
			System.out.println(name + " BLACKJACK!!");
			setCanPlay(false);
		}
	}

	public void stand() {
		System.out.println(name + " stands!");
	}

	public void split() {
	}

	public int checkHand() {
		return ((BlackJackHand) hand).getTotalValue();
	}

	public boolean getHasBusted() {
		return ((BlackJackHand) hand).getHasBusted();
	}

	public boolean getHasTwentyOne() {
		if (((BlackJackHand) hand).getHasTwentyOne()) {
			return true;
		} else
			return false;
	}

	public void lookAtHand() {
		String statusString = "";
		if (!getCanPlay()) {
			if (getHasBusted()) {
				statusString += " * BUSTED * ";
			}
			if (getHasTwentyOne()) {
				statusString += " * BLACKJACK * ";
			}
			if (getIsPush()) {
				statusString += " * PUSH * ";
			}
		}
		System.out.println(name + " | " + hand + statusString);
	}

}
