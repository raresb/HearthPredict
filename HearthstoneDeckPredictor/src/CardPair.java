
public class CardPair implements Comparable<CardPair> {
	private Card card;
	private int quantity;
	
	public CardPair(Card card,int quantity){
		this.card = card;
		this.quantity = quantity;
	}

	public Card getCard() {
		return card;
	}


	public int getQuantity() {
		return quantity;
	}

	
	public int compareTo(CardPair c){
		if(this.card.compareTo(c.card) > 0){
			return 1;
		}else{
			return -1;
		}
	}
	
	public String toString(){
		return "(" + card.getName() + "," + quantity + ")";
	}
	
}
