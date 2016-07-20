import java.util.HashMap;
import java.util.Map.Entry;


public class Deck {
	private DeckClass dClass;
	private String name;
	private HashMap<String,Integer> deck = new HashMap<String,Integer>();

	public Deck(){
		
	}
	
	public Deck(DeckClass dClass, String name, HashMap<String,Integer> deck){
		this.dClass = dClass;
		this.name = name;
		this.deck = deck;
	}

	public Deck(HashMap<String,Integer> deck){
		this.name = "No Name";
		this.dClass = DeckClass.ANY;
		this.deck = deck;
	}

	public Deck(DeckClass dClass, String line){
		String[] cards = line.split(Main.DELIMITER);
		this.dClass = dClass;
		this.name = cards[0];
		int i = 1;
		while(i < cards.length){
			if(i+1 >= cards.length){
				break;
			}
			deck.put(cards[i], Integer.parseInt(cards[i+1]));
			i+=2;
		}
	}
	
	public void addCard(String name, int quantity){
		if(deck.containsKey(name)){
			deck.put(name, deck.get(name) + quantity);
		}else{
			deck.put(name, quantity);
		}
	}
	
	//this method tells you if the most recently added card belongs in both decks
	//name is the most recently added card,  THIS METHOD WILL NOT ADD THE CARD,
	// CARD HAS TO HAVE ALREADY BEEN ADDED.
	public int similarityToDeckPlusNewCard(Deck d, String name){
		int same = 0;
		//System.out.println(name);
		//System.out.println(deck.size());
		//System.out.println(d.getDeck().size());
		if(d.getDeck().containsKey(name)){
			if(d.getDeck().get(name) >= (deck.get(name))){
				same = 1;
			}
		}
		return same;
	}
	
	public int similarityToDeck(Deck d){
		int same = 0;
		for(String name : deck.keySet()){
			if(d.getDeck().containsKey(name)){
				same += Math.min(deck.get(name), d.getDeck().get(name));
			}
		}
		return same;
	}
	
	public int distanceToDeck(Deck d){
		int dist = 0;
		for(String name : deck.keySet()){
			if(d.getDeck().containsKey(name)){
				dist += Math.abs(deck.get(name) - d.getDeck().get(name));
			}else{
				dist += deck.get(name);
			}
		}
		for(String name : d.deck.keySet()){
			if(!deck.containsKey(name)){
				dist += d.deck.get(name);
			}
		}
		return dist;
	}

	public int distanceToCard(Card c){
		int dist = 0;
		if(deck.containsKey(c.getName())){
			dist = Math.abs(deck.get(c.getName()) - 1);
		}else{
			dist = Math.abs(0 - 1);
		}
		return dist;
	}
	
	public int distanceToCard(String cardName){
		int dist = 0;
		if(deck.containsKey(cardName)){
			dist = Math.abs(deck.get(cardName) - 1);
		}else{
			dist = Math.abs(0 - 1);
		}
		return dist;
	}
	
	public boolean canBeSame(Deck d){
		boolean same = false;
		if(this.distanceToDeck(d) == 0){
			same = true;
		}
		return same;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(name + "\r\n");
		for(Entry<String, Integer> entry : deck.entrySet()){
			sb.append("[" + entry.getKey() + "," + entry.getValue() + "]\r\n");
		}
		return sb.toString();
	}

	public DeckClass getdClass() {
		return dClass;
	}
	
	public String getName() {
		return name;
	}

	public HashMap<String, Integer> getDeck() {
		return deck;
	}

}
