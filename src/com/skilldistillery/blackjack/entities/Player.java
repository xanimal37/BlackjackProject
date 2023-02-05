package com.skilldistillery.blackjack.entities;

public abstract class Player {
	
	protected boolean isWinner=false;
	
	protected Hand hand;
	// for now this is either player or dealer
	protected String name;
	
	//setter
	public void setWinner() {
		isWinner = true;
	}
	
	//getter
	public boolean getWinner() {
		return isWinner;
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name + " Player Hand | " + hand;
	}
	
	public void addCard(Card c) {
		hand.addCard(c);
	}
	
	//flip all cards to show
	public void showCards() {
		hand.show();
	}
	


}
