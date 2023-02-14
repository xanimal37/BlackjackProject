package com.skilldistillery.blackjack.entities;

//made a unique class becuase although BJ Dealer is a PLAYER...
//it has its own decision making logic
//deals cards to all players
//could include a dealer interface for other games

public class BJDealer extends BJPlayer implements IDealer {

	Deck shoe;
	
	public BJDealer() {
		isPush = false;
		hasBlackJack = false;
		canPlay = true;
		hasBusted=false;
	}

	// set the number of decks in the constructor

	public void buildShoe(int numDecks) {
		// initialize shoe
		shoe = new Deck();
		hand = new BlackJackHand();
		name = "Dealer";
		for (int i = 1; i < numDecks; i++) {
			Deck deck = new Deck();
			for (Card c : deck.getCards()) {
				shoe.addCard(c);
			}
		}
		shoe.shuffle();
	}

	// BJ deals cards uniquely
	// sometimes they will be face down, sometimes face up
	// use a list to implement multiple players later
	public Card dealCard(boolean faceDown) {

		Card card = shoe.dealCard();
		if (faceDown) {
			card.flip();
		}
		return card;

	}

	public void makeDecisions() {
		// dealer will reveal flipped card
		((BlackJackHand) hand).turnCardsFaceUp();

		// dealer logic
		// method from BJPlayer class
		if (getCanPlay()) {
			// less than 21
			if (((BlackJackHand) hand).getTotalValue() < 17) {
				hit(dealCard(false));
			} else {
				stand();
			}
		}
	}

	public boolean checkShoe(int players) {
		// we will need at least two cards per player (inc dealer)
		int cardsNeeded = (players + 1) * 2;
		if (shoe.getNumCards() >= cardsNeeded) {
			return true;
		} else {
			return false;
		}
	}

}
