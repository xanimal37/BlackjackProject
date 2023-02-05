package com.skilldistillery.blackjack.testing;

import com.skilldistillery.blackjack.entities.BlackJackHand;
import com.skilldistillery.blackjack.entities.Deck;

public class HandTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deck deck = new Deck();
		BlackJackHand hand = new BlackJackHand();
		
		deck.shuffle();
		
		hand.addCard(deck.dealCard());
		hand.addCard(deck.dealCard());
		
		System.out.println(hand);
		
		hand.addCard(deck.dealCard());
		
		System.out.println(hand);
		

	}

}
