package com.skilldistillery.blackjack.app;

import java.util.Scanner;

public class BlackJackApp {

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

		// 1.show menu for main app
		// 2. process user choice
		appLoop(input);
		// 3. quit app or start game loop

		// 4. when someone wins, go back to appLoop

		// 5. repeat
		// repeat or end
		System.out.println("***************************");
		System.out.println("|        Goodbye!!        |");
		System.out.println("***************************");
	}
	// app loop

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
			displayGameStatus();
			displayGameMenu();
		}
	}

	//displayGamestatus shows the status of both hands for the player
	private void displayGameStatus() {
		System.out.println("You are holding: ");
		System.out.println("The dealer is holding: ");
	}
	// show the menu for inside the game
	private void displayGameMenu() {
		System.out.println("*** What would you like to do? ***");
		System.out.println("* 1. Hit                         *");
		System.out.println("* 2. Stand                       *");
		System.out.println("* 3. Split                       *");
		System.out.println("**********************************");

	}

}
