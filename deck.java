import java.util.Random;

// add your own banner here

public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck

	// add more instance variables if needed
	
	public Deck(){
		// make a 52 card deck here
		cards = new Card[52];
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 14; j++) {
				cards[(i-1)*13+(j-1)] = new Card(i,j);
			}
		}
		top = 51;
	}
	
	public void shuffle(){
		// shuffle the deck here
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			int a = r.nextInt(52);
			int b = r.nextInt(52);
			Card c = cards[a];
			cards[a] = cards[b];
			cards[b] = c;
		}
	}
	
	public Card deal(){
		// deal the top card in the deck
		return cards[top--];
	}
	
	public void swap(int i, int j) {
		Card temp = cards[i];
		cards[i] = cards[j];
		cards[j] = temp;
	}
	
	public void remove(Card c) {
		for (int i = 0; i < cards.length; i++) {
			if (c.equals(cards[i])) {
				swap(i,top);
				top--;
				break;
			}
		}
	}
	
	// add more methods here if needed

}
