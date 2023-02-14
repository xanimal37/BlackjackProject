package com.skilldistillery.blackjack.entities;

import java.util.Objects;

public class Card {

	private Suit suit;
	private Rank rank;
	private int value;

	// determine if card is flipped
	// determines visibility
	boolean isFaceUp = true;

	// constructors
	public Card() {
	} // default

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
		this.value = rank.getValue();
	}

	// getters + setters
	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	public void setValue(int value) {
		this.value=value;
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
		if (isFaceUp) {
			return "[" + rank + " of " + suit.toString() + "]";
		} else {
			return "[Hidden Card]";
		}
	}

	// fip the card from its current status (determines visibility)
	public void flip() {
		isFaceUp = !isFaceUp;
	}

	// returns boolean denoting if the card is face up or not
	public boolean getIsFaceUp() {
		return isFaceUp;
	}

}
