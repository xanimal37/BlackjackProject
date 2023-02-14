package com.skilldistillery.blackjack.entities;

public class BJPlayer extends Player implements Comparable<BJPlayer> {

	// keeps taking turns
	boolean canPlay;
	// tied with dealer
	boolean isPush;
	// has busted
	boolean hasBusted;
	// has a blackjack!
	boolean hasBlackJack;

	public BJPlayer() {
		hand = new BlackJackHand();
		canPlay = true;
		isPush = false;
		hasBlackJack = false;
		hasBusted = false;
		isWinner = false;
	}

	// getters (accessors)
	// ******************
	public boolean getCanPlay() {
		return canPlay;
	}

	public boolean getIsPush() {
		return isPush;
	}

	public boolean getHasBusted() {
		return hasBusted;
	}

	public boolean getHasBlackJack() {
		return hasBlackJack;
	}

	// setters (mutators)
	// ******************
	public void setIsPush(boolean isPush) {
		this.isPush = isPush;
	}

	private void setCanPlay(boolean canplay) {
		this.canPlay = canplay;
	}

	private void setHasBusted(boolean hasBusted) {
		this.hasBusted = hasBusted;
	}

	private void setHasBlackJack(boolean hasBlackJack) {
		this.hasBlackJack = hasBlackJack;
	}

	// this method resets all player flags to the original status
	public void resetPlayer() {
		isPush = false;
		canPlay = true;
		hasBusted = false;
		hasBlackJack = false;
		isWinner = false;
	}

	// things a player would do
	// count the total and check status -- sets or removes player flags (check
	// status after every 'move')
	public void checkStatus() {
		int total = ((BlackJackHand) hand).getBlackJackValue();

		if (total > 21) {
			System.out.println(name + " BUSTED!! ");
			setHasBusted(true);
			setCanPlay(false);

		}
		if (total == 21) {
			// player has 21 or BLACKJACK
			setHasBlackJack(true);
			setCanPlay(false);

		} else {
		}
		System.out.println(total);
	}

	public int getHandValue() {
		return ((BlackJackHand) hand).getBlackJackValue();
	}

	public void printPlayerStatus() {
		StringBuilder statusString = new StringBuilder();
		if (!getCanPlay()) {
			if (getHasBusted()) {
				statusString.append(" * BUSTED * ");
			}
			if (getHasBlackJack()) {
				statusString.append(" * BLACKJACK * ");
			}
			if (getIsPush()) {
				statusString.append(" * PUSH * ");
			}
			if(getWinner()) {
				statusString.append(" * WINNER * ");
			}
		}
		System.out.println(name + " | " + hand + statusString.toString());
	}

	// playing methods

	// on deal
	public void takeCard(Card card) {
		((BlackJackHand) hand).addCard(card);
	}

	// emptyhands
	public void emptyHand() {
		hand.clearHand();
	}

	// actions the player can take until done
	public void hit(Card card) {
		System.out.println(name + " hits!");
		((BlackJackHand) hand).addCard(card);
		checkStatus();
	}

	public void stand() {
		System.out.println(name + " stands!");
		setCanPlay(false);
	}

	public void split() {
		// not implemented yet
	}

	@Override
	public int compareTo(BJPlayer o) {
		// if dealer has busted player wins
		if (o.getHasBusted() && !getHasBusted()) {
			return 1;
		}
		//if both have busted
		else if(o.getHasBusted() && getHasBusted()) {
			return 0;
		}
		// if neither has busted compare values
		else if (!o.getHasBusted() && !getHasBusted()) {
			return getHandValue() - o.getHandValue();
		}
		// else player loses
		else {
			return -1;
		}
	}

}
