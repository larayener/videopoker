import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// add your own banner here

public class Game {
	
	private Player p;
	private Deck cards;
	private int round;
	// you'll probably need some more here
	
	
	public Game(String[] testHand){
		// This constructor is to help test your code.
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testHand = {s1, s13, s12, s11, s10} = royal flush
		p = new Player();
		cards = new Deck();
		cards.shuffle();
		for (int i = 0; i < testHand.length; i++) {
			String s = testHand[i];
			char suitc = s.charAt(0);
			int rank = Integer.parseInt(s.substring(1, s.length()));
			int suit = 0;
			switch (suitc) {
			case 's': suit = 1;
			break;
			case 'c': suit = 2;
			break;
			case 'd': suit = 3;
			break;
			case 'h': suit = 4;
			break;
			}
			Card c = new Card(suit,rank);
			p.addCard(c);
			cards.remove(c);
		}
	}
	
	public Game(){
		// This no-argument constructor is to actually play a normal game
		p = new Player();
		cards = new Deck();
	}
	
	public void play(){
		// this method should play the game	
		round = 0;
		System.out.println("Welcome to video poker!");
		while (p.getBankroll() > 0) {
			round++;
		System.out.println("You currently have " + p.getBankroll() + " tokens. You can bet a maximum of 5 tokens per round.");
		System.out.println("How much do you want to bet?");
		Scanner s = new Scanner(System.in);
		int a = s.nextInt();
		while (a > 5 || a < 1) {
			System.out.println("You must bet a number of tokens between 1-5.");
			a = s.nextInt();
		}
		p.bets(a); 
		if (round != 1 || (round == 1 && p.getHand().length() == 0)) {
			cards.shuffle();
			dagit(5);
		}
		System.out.println(p.getHand());
		System.out.println("Please print the cards you want to reject with spaces in between. (i.e. If you want to reject the first card, type \"1\".) \nIf you want to accept all the cards, type \"0\".");
		int rejectsno = 0;
		s.nextLine();
		String k = s.nextLine();
		String[] j = k.split(" ");
		for (int i = 0; i < j.length; i++) {
			int aa = Integer.parseInt(j[i]);
			if (aa > 0) {
				Card c = p.getCard(aa - 1 - rejectsno);
				p.removeCard(c);
				rejectsno++;
			}
		}
		dagit(rejectsno);
		System.out.println(p.getHand());
		System.out.println(checkHand(p.getArrayHand()));
	}
	}
	
	public void dagit(int c) {
		for (int i = 0; i < c; i++) {
			p.addCard(cards.deal());
		}
	}
	
	public boolean sameSuit(ArrayList<Card> hand) {
		int suit = hand.get(0).getSuit();
		for (int i = 1; i < hand.size(); i++) {
			if (hand.get(i).getSuit() != suit) {
				return false;
			}
		}
		return true;
	}
	
	public int rankAnalysis(ArrayList<Card> hand) {
		int rank = 0;
		for (int i = 1; i < hand.size(); i++) {
			rank+= hand.get(i).compareTo(hand.get(i - 1));
		}
		return rank;
	}
	
	public int pointBreak(ArrayList<Card> hand) {
		for (int i = 1; i < hand.size(); i++) {
			if (hand.get(i).getRank() != hand.get(i - 1).getRank()) {
				return i;
			}
		}
		return hand.size();
	}
	
	public HashMap<Integer,Integer> analyzeDiffs(ArrayList<Card> hand) {
		HashMap<Integer,Integer> h = new HashMap<Integer,Integer>();
		for (int i = 0; i < hand.size(); i++) {
			Card c = hand.get(i);
			if (h.keySet().contains(c.getRank())) {
				int count = h.get(c.getRank());
				count++;
				h.put(c.getRank(), count);
			}
			else {
				h.put(c.getRank(), 1);
			}
		}
		return h;
	}
	
	public boolean kayma(ArrayList<Card> hand) {
		if (hand.get(0).getRank() != 1) {
			return false;
		}
		else if (hand.get(1).getRank() != 10){
			return false;
		}
		else if (hand.get(2).getRank() != 11){
			return false;
		}
		else if (hand.get(3).getRank() != 12){
			return false;
		}
		else if (hand.get(4).getRank() != 13){
			return false;
		}
		return true;
	}
	
	public String checkHand(ArrayList<Card> hand){
		// this method should take an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String
		hand.sort(null);
		int noOS = 0;
		int noOP = 0;
		int noOT = 0;
		int noOQ = 0;
		HashMap<Integer,Integer> hm = analyzeDiffs(hand);
		for (Integer e : hm.keySet()) {
			if (hm.get(e) == 1) {
				noOS++;
			}
			if (hm.get(e) == 2) {
				noOP++;
			}
			if (hm.get(e) == 3) {
				noOT++;
			}
			if (hm.get(e) == 4) {
				noOQ++;
			}
		}
		if (sameSuit(hand) && hand.get(0).getRank() == 1 && hand.get(1).getRank() == 10 && hand.get(2).getRank() == 11 && hand.get(3).getRank() == 12 && hand.get(4).getRank() == 13) {
			p.winnings(250);
			return "Royal Flush!!!";
		}
		else if (sameSuit(hand) && rankAnalysis(hand) == 4 && noOS == 5) {
			p.winnings(50);
			return "Straight Flush!";
		}
		else if (noOQ == 1) {
			p.winnings(25);
			return "Four of a Kind!";
		}
		else if (noOT == 1 && noOP == 1) {
			p.winnings(6);
			return "Full House.";
		}
		else if (sameSuit(hand)) {
			p.winnings(5);
			return "Flush.";
		}
		else if (rankAnalysis(hand) == 4 && noOS == 5) {
			p.winnings(4);
			return "Straight.";
		}
		else if (kayma(hand)) {
			p.winnings(4);
			return "Straight.";
		}
		else if (noOT == 1) {
			p.winnings(3);
			return "Three of a Kind.";
		}
		else if (noOP == 2) {
			p.winnings(2);
			return "Two Pair.";
		}
		else if (noOP == 1) {
			p.winnings(1);
			return "One Pair.";
		}
		else {
			p.winnings(0);
			return "No Pair.";
		}
	}
	
}
