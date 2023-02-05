package com.skilldistillery.blackjack.entities;

//have this incase we want to make other kinds of dealers
public interface IDealer {
	
	public Card dealCard(boolean faceDown);
	
	public void makeDecisions();

}
