package com.skilldistillery.blackjack.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.blackjack.entities.BJDealer;
import com.skilldistillery.blackjack.entities.BJOption;
import com.skilldistillery.blackjack.entities.BJPlayer;
import com.skilldistillery.blackjack.entities.IDealer;

public class BlackJackApp {

	BJDealer dealer;

	private List<BJPlayer> players;

	public BlackJackApp() {
		// this will hold players and their hand status
		// we can check this each turn to see who won!
		players = new ArrayList<BJPlayer>();
		dealer = new BJDealer();
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
				int numPlayers = getNumberOfPlayers(input);
				setUpGame(numPlayers);
				gameLoop(input, numPlayers);
				break;
			case "N":
				System.out.println("All right, let me know if you change your mind!");
				inMainMenuLoop = false;
				break;
			case "Q":
				System.exit(0);
				break;
			default:
				System.out.println("Please enter a valid option.");
				break;
			}
		}
	}

	// just asks if you want to play
	private void displayAppMenu() {
		System.out.println("***************************************");
		System.out.println("Would you like to play BlackJack? Y/N/Q");
		System.out.println("***************************************");
	}

	// get the number of players
	private int getNumberOfPlayers(Scanner input) {
		int numPlayers = 1;
		System.out.println("How many players will be at the table? (1-4)");
		try {
			numPlayers = input.nextInt();
			if (numPlayers < 1) {
				numPlayers = 1;
			}
			if (numPlayers > 4) {
				numPlayers = 4;
			}
		} catch (Exception e) {
			System.out.println("Please enter a valid number of players.");
		} finally {
			input.nextLine();
		}
		return numPlayers;
	}

	// set up the game to its initial state
	private void setUpGame(int numPlayers) {
		// clear existing player list
		players.clear();
		// clear existing dealer
		dealer = new BJDealer();
		dealer.buildShoe(numPlayers);
		// create dealer
		// create players
		for (int i = 0; i < numPlayers; i++) {
			BJPlayer newPlayer = new BJPlayer();
			newPlayer.setName("Player " + (i + 1));
			players.add(newPlayer);
		}
		// dealer always goes last so goes at end of list
		players.add(dealer);
		resetPlayers(); // sets all flags to starging
		// dealCards
		dealCards();
	}

	private void dealCards() {
		// each player gets two cards, second delear card is face down
		for (int i = 0; i < 2; i++) {
			for (BJPlayer p : players) {
				if (p instanceof IDealer && i == 1) {
					p.takeCard(dealer.dealCard(true));
				} else {
					p.takeCard(dealer.dealCard(false));
				}
			}
		}
	}

	// the loop for when the game is running
	private void gameLoop(Scanner input, int numPlayers) {
		boolean inGameLoop = true;
		while (inGameLoop) {
			// multiple rounds of blackjack until all players quit
			// or dealer runs out of cards
			// first, check that dealer has enough cards
			if (dealer.checkShoe(numPlayers)) {
				// if ok, play a round
				inGameLoop = playRound(input);
			} else {
				System.out.println("Sorry, the dealer is out of cards. Returning to the lobby...");
				inGameLoop = false;
			}
		}
	}

	// reset the status of all players
	private void resetPlayers() {
		for (BJPlayer p : players) {
			p.emptyHand();
			p.resetPlayer();
		}
	}

	// just displays options for the player
	private void showPlayerMenu(BJPlayer player) {
		System.out.println("****************");
		BJOption[] options = BJOption.values();
		for (BJOption opt : options) {
			System.out.println("*** " + opt.toString());
		}
		System.out.println("****************");
	}

	private int getPlayerGameInput(BJPlayer player, Scanner input) {
		int choice = 0;
		while (choice < 1 || choice > 4) {
			System.out.println("\n" + player.getName() + ", what will you do?");
			try {
				choice = input.nextInt();
			} catch (Exception e) {
				System.out.println("Please make a valid choice");
			} finally {
				input.nextLine();
			}
		}
		return choice;
	}

	// determines the winners after each round
	// each player is playing against the dealer, not one another
	private void calculateResults() {
		for (BJPlayer p : players) {
			if (p instanceof IDealer) {
				p.printPlayerStatus();
			} else {
				int result = p.compareTo(dealer);

				if (result == 0) {
					p.setIsPush(true);
				}
				if (result > 0) {
					p.setIsWinner(true);
				} else {

				}
			}

		}
	}

	// this displays the game result
	private void showResults() {
		System.out.println("\nShowing the results of this round ****");
		for (BJPlayer p : players) {
			p.printPlayerStatus();
		}
		System.out.println("\n");
	}

	// this is the code for a round of play
	private boolean playRound(Scanner input) {
		//this boolean allows us to leave the game on choice 4
		boolean keepGoing = true;
		System.out.println("Starting a new round...");
		// always reset before the round
		resetPlayers();
		// deal cards
		dealCards();
		// take turns
		for (BJPlayer p : players) {
			if (p instanceof IDealer && keepGoing == true) {
				while (p.getCanPlay()) {
					// do dealer things
					((IDealer) p).makeDecisions();
				}
			} else {
				// might have blackjack already
				p.checkStatus();
				while (p.getCanPlay() && keepGoing == true) {
					// show player hand
					p.printPlayerStatus();
					// show dealer hand for refrence
					dealer.printPlayerStatus();
					// wait for player choice
					showPlayerMenu(p);
					int playerChoice = getPlayerGameInput(p, input);
					switch (playerChoice) {
					case 1:
						p.hit(dealer.dealCard(false));
						break;
					case 2:
						p.stand();
						break;
					case 4:
						System.out.println("Returning to the lobby...");
						keepGoing = false;
						break;
					default:
						break;
					}
				}
			}
		}
		if (keepGoing == true) {
			calculateResults();
			showResults();
		}
		return keepGoing;
	}
}
