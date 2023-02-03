package com.skilldistillery.blackjack.testing;

import java.util.Scanner;

import com.skilldistillery.blackjack.entities.Card;
import com.skilldistillery.blackjack.entities.Deck;

public class DealingCards {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		Deck deck = new Deck();

		System.out.println(deck.checkDeckSize());

		System.out.println("How many cards do you want?");
		int numCards = sc.nextInt();
		sc.nextLine();

		deck.shuffle();

		int totalValue = 0;

		for (int i = 0; i < numCards; i++) {
			Card card = deck.dealCard();
			System.out.println(card.toString());
			totalValue += card.getValue();

		}
		System.out.println(totalValue);
	}
}
