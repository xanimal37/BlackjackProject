package com.skilldistillery.blackjack.entities;

public abstract class Player {
	
	protected boolean isWinner=false;
	
	protected Hand hand;
	// for now this is either player or dealer
	protected String name;
	
	//setter
	public void setIsWinner(boolean isWinner) {
		this.isWinner = isWinner;
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
	
	


}
