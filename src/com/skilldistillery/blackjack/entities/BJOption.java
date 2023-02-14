package com.skilldistillery.blackjack.entities;

//use this enum for the player options
public enum BJOption {
	HIT("Hit",1),STAND("Stand",2),SPLIT("Split",3),QUIT("Quit",4);

private String name;
private int value;
	
	BJOption(String name, int value){
		this.name = name;
		this.value = value;
	}
	
	public String toString() {
		return "" + value +". " + name;
	}
	
	public int getValue() {
		return value;
	}
}
