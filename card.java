public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r){
		suit = s;
		rank = r;
	}
	
	public int compareTo(Card c){
		// use this method to compare cards so they 
		// may be easily sorted
//		if (this.rank > c.getRank()) {
//			return 1;
//		}
//		else if (this.rank < c.getRank()) {
//			return -1;
//		}
//		else {
//			return 0;
//		}
		return this.rank - c.getRank();
	}
	
	public String toString(){
		// use this method to easily print a Card object
		String s = "";
		switch (suit) {
		case 1: s = "Spades";
		break;
		case 2: s = "Clubs";
		break;
		case 3: s = "Diamonds";
		break;
		case 4: s = "Hearts";
		break;
		}
		switch (rank) {
		case 1: s += " ace";
		break;
		case 11: s += " jack";
		break;
		case 12: s += " queen";
		break;
		case 13: s += " king";
		break;
		default: s+= " " + rank;
		}
		return s;
	}
	
	public int getRank() {
		return rank;
	}
	
	public int getSuit() {
		return suit;
	}
	
	public static void main(String[] args){
		Card c = new Card(4,4);
		System.out.println(c);
		Card d = new Card(1,12);
		System.out.println(d);
		System.out.println(c.compareTo(d));
	}
	
	public boolean equals(Card c) {
		if (this.getRank() == c.getRank() && this.getSuit() == c.getSuit()) {
			return true;
		}
		else {
			return false;
		}
	}

}
