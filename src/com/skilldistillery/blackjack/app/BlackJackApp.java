package com.skilldistillery.blackjack.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.blackjack.entities.BJDealer;
import com.skilldistillery.blackjack.entities.BJPlayer;
import com.skilldistillery.blackjack.entities.IDealer;

public class BlackJackApp {

	BJDealer dealer = new BJDealer();

	private List<BJPlayer> players;

	public BlackJackApp() {
		// this will hold players and their hand status
		// we can check this each turn to see who won!
		players = new ArrayList<BJPlayer>();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		BlackJackApp app = new BlackJackApp();

		app.run(sc);
	}

	private void run(Scanner input) {
		System.out.println("***************************");
		System.out.println("|  Welcome to BlackJack!  |");
		System.out.println("***************************");

		appLoop(input);
		// repeat or end

		System.out.println("***************************");
		System.out.println("|        Goodbye!!        |");
		System.out.println("***************************");
	}

	// shows the menu for outside the game loop
	private void appLoop(Scanner input) {

		boolean inMainMenuLoop = true;
		while (inMainMenuLoop) {
			displayAppMenu();
			String userInput = input.nextLine();
			userInput = userInput.toUpperCase();
			switch (userInput) {
			case "Y":
				System.out.println("Ok! Get ready to play!");
				System.out.println("How many players? 1-3");
				int numPlayers = input.nextInt();
				input.nextLine();
				// for testing, num players is 1
				setUpGame(numPlayers);
				gameLoop(input);
				break;
			case "N":
				System.out.println("All right, let me know if you change your mind!");
				inMainMenuLoop = false;
				break;
			default:
				System.out.println("Please enter a valid option.");
				break;
			}
		}
	}

	private void displayAppMenu() {
		System.out.println("Would you like to play BlackJack? Y/N");
	}

	// the loop for when the game is running
	private void gameLoop(Scanner input) {
		boolean inGameLoop = true;
		while (inGameLoop) {
			//players could have natural 21
			checkGameStatus();
			// players play
			takeTurns(input);

			// check for bust or win
			inGameLoop = false;
			checkGameStatus();
			showResults();

		}
	}

	// show the menu for inside the game
	private void displayGameMenu(BJPlayer p) {
		System.out.println("*** What would you like to do? ***");
		System.out.println("* 1. Hit                         *");
		System.out.println("* 2. Stand                       *");
		System.out.println("* 3. Split                       *");
		System.out.println("**********************************");
	}

	// sets up the game both players with hands
	// first card face up
	// last dealer card face down
	private void setUpGame(int numberOfPlayers) {
		//clear the old player list
		players.clear();
		
		System.out.println("Setting up the game for " + numberOfPlayers + " players.");
		// create players, finish with a dealer (so they go last)
		// create all players
		for (int i = 0; i < numberOfPlayers; i++) {
			BJPlayer newPlayer = new BJPlayer();
			newPlayer.setName("Player " + (i+1));
			players.add(newPlayer);
		}
		// add dealer as a player (already created by app)
		players.add(dealer);

		for (int i = 0; i < 2; i++) {
			for (BJPlayer p : players) {
				// if second round of cards and the player is a dealer (IDealer)
				if (i == 1 && p instanceof IDealer) {
					p.addCard(dealer.dealCard(true));
				} else {
					p.addCard(dealer.dealCard(false));
				}
			}
		}
	}

	// goes through list of players allowing each to take a turn (including dealer)
	private void takeTurns(Scanner input) {
		for (BJPlayer p : players) {
			if (p.getCanPlay() && !(p instanceof IDealer)) {
				while (p.getCanPlay()) { // loop through choices until player must stop then move to next player
					p.lookAtHand();
					displayGameMenu(p);
					int choice = getPlayerChoice(input);
					switch (choice) {
					case 1:
						p.hit(dealer.dealCard(false));
						break;
					case 2:
						p.stand();
						p.setCanPlay(false); // player is done
						break;
					default:
						break;
					}
					checkPlayerStatus(p); // checks if player busted
				}

			}
			if (p.getCanPlay() && (p instanceof IDealer)) { // dealer has built in decisions
				((BJDealer) p).makeDecisions();
			}

		}

	}

	private int getPlayerChoice(Scanner input) {
		System.out.println("Getting player choice...");
		int choice = 0;
		while (choice != 1 && choice != 2) {
			try {
				choice = input.nextInt();
			} catch (Exception e) {
				System.out.println("Please enter a valid option.");
			} finally {
				input.nextLine();
			}

		}
		// System.out.println("Returning choice: " + choice);
		return choice;

	}

	private void checkPlayerStatus(BJPlayer player) {
		//
		if (player.getHasTwentyOne()) {
			player.setWinner(); // player is a winner
			player.setCanPlay(false); // player is done
		}
		if (player.getHasBusted()) {
			player.setCanPlay(false); // player is done
		}
	}

	private void showResults() {

		for (BJPlayer p : players) {
			p.lookAtHand();
			if (p.getWinner()) {
				System.out.println(p.getName() + " is a winner!");
			}
			if(!p.getWinner() && !(p instanceof IDealer)) {
				System.out.println(p.getName() + " lost!");
			}
			
		}
	}

	private void checkGameStatus() {
		// compare each player to dealer
		for (BJPlayer p : players) {
			if (!p.getHasBusted() && !(p instanceof IDealer)) {
				int playerDiff = 21 - p.checkHand();
				int dealerDiff = 21 - dealer.checkHand();
				if (playerDiff < dealerDiff) {
					p.setWinner();
				}
				if(playerDiff == dealerDiff) {
					p.setIsPush();
				}
			}
		}
	}

}
