package com.skilldistillery.blackjack.entities;

//made a unique class becuase although BJ Dealer is a PLAYER...
//it has its own decision making logic
//deals cards to all players
//could include a dealer interface for other games

public class BJDealer extends BJPlayer implements IDealer{

	Deck deck;

	// setters
	// will use this later on when include multiple decks
	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public BJDealer() {
		hand = new BlackJackHand();
		name = "Dealer";
		deck = new Deck();
		deck.shuffle();
	}


	// BJ deals cards uniquely
	// sometimes they will be face down, sometimes face up
	// use a list to implement multiple players later

	public Card dealCard(boolean faceDown) {
		
		Card card = deck.dealCard();
		if (faceDown) {
			card.flip();
		}
		return card;

	}
	
	public void makeDecisions() {
		//dealer will reveal flipped card
		hand.show();
		
		//dealer logic
		//method from BJPlayer class
		if(canPlay) {
			//less than 21
			if(checkHand()<17) {
				hit(dealCard(false));
			}
			else {
				stand();
			}
		}
		
		//checkStatus();
		
		
	}
	
	

}
