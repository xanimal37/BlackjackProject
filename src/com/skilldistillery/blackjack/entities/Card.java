package com.skilldistillery.blackjack.entities;

import java.util.Objects;

public class Card {

	private Suit suit;
	private Rank rank;
	
	//determine if card is flipped
	//determines visibility
	boolean isFaceUp = false;

	// constructors
	public Card() {
	} // default

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	// getters + setters
	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public int getValue() {
		return getRank().getValue();
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rank, suit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return rank == other.rank && suit == other.suit;
	}

	@Override
	public String toString() {
		return rank + " of " + suit.toString();
	}
	
	//fip the card from its current status (determines visibility)
	public void flip() {
		isFaceUp = !isFaceUp;
	}
	
	//returns boolean denoting if the card is face up or not
	public boolean getFaceUp() {
		return isFaceUp;
	}

}
