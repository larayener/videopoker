import java.util.ArrayList;

// add your own banner here

public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
        private double bet;

	// you may choose to use more instance variables
		
	public Player(){		
	    // create a player here
		bankroll = 100;
		hand = new ArrayList<Card>();
		bet = 0;
	}

	public void addCard(Card c){
	    // add the card c to the player's hand
		hand.add(c);
	}

	public void removeCard(Card c){
	    // remove the card c from the player's hand
		hand.remove(c);
        }
	
		public Card getCard(int sira) {
			return hand.get(sira);
		}
		
        public void bets(double amt){
            // player makes a bet
        	bet = amt;
        	bankroll = bankroll - bet;
        }

        public void winnings(double odds){
            //	adjust bankroll if player wins
        	bankroll = bankroll + (odds * bet);
        	bet = 0;
        	hand = new ArrayList<Card>();
        }

        public double getBankroll(){
            // return current balance of bankroll
        	return bankroll;
        }
        
        public String getHand() {
        	String s = "";
        	for (Card c : hand) {
        		s+= c.toString() + " , ";
        	}
        	return s;
        }
        
        public ArrayList<Card> getArrayHand() {
        	return hand;
        }

        // you may wish to use more methods here
}
