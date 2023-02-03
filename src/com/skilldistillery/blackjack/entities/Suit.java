package com.skilldistillery.blackjack.entities;

public enum Suit {
	SPADES("Spades"),HEARTS("Hearts"),CLUBS("Clubs"),DIAMONDS("Diamonds");
	
	private String name;
	
	Suit(String name){
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
