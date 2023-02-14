package com.skilldistillery.blackjack.testing;

import com.skilldistillery.blackjack.entities.BJPlayer;
import com.skilldistillery.blackjack.entities.Player;

public class InhTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BJPlayer p1 = new BJPlayer();
		Player p2 = new BJPlayer();
		
		p1.checkStatus();
		p2.getHand();
		
		((BJPlayer)p2).checkStatus();
	}

}
