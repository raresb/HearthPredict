import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


public class HearthPredictModel implements ModelObservable{	
	private ArrayList<ArrayList<Deck>> allDecks;
	private ArrayList<Card> allCards;
	private DeckComparator deckCompare;
	private boolean gameLive = false;
	private boolean gameRunning = false;
	private DeckClass oppHero;
	private HashSet<Integer> idsSeen = new HashSet<Integer>();
	private ArrayList<Card> inOrderCards = new ArrayList<Card>();
	private ArrayList<ModelObserver> observers = new ArrayList<ModelObserver>();
	
	public HearthPredictModel() throws IOException{
		allDecks = CachedData.allDecks;
		allCards = CachedData.allCards;
	}
	
	public void reset(){
		deckCompare = null;
		gameLive = false;
		oppHero = DeckClass.ANY;
		idsSeen.clear();
	}

	public ArrayList<ArrayList<Deck>> getAllDecks() {
		return allDecks;
	}

	public void setAllDecks(ArrayList<ArrayList<Deck>> allDecks) {
		this.allDecks = allDecks;
	}

	public ArrayList<Card> getAllCards() {
		return allCards;
	}

	public void setAllCards(ArrayList<Card> allCards) {
		this.allCards = allCards;
	}

	public DeckComparator getDeckCompare() {
		return deckCompare;
	}

	public void setDeckCompare(DeckComparator deckCompare) {
		this.deckCompare = deckCompare;
	}

	public boolean isGameLive() {
		return gameLive;
	}

	public void setGameLive(boolean gameLive) {
		this.gameLive = gameLive;
	}

	public boolean isGameRunning() {
		return gameRunning;
	}

	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}

	public DeckClass getOppHero() {
		return oppHero;
	}

	public void setOppHero(DeckClass oppHero) {
		this.oppHero = oppHero;
	}

	public HashSet<Integer> getIdsSeen() {
		return idsSeen;
	}

	public void setIdsSeen(HashSet<Integer> idsSeen) {
		this.idsSeen = idsSeen;
	}

	public ArrayList<Card> getInOrderCards() {
		return inOrderCards;
	}

	public void setInOrderCards(ArrayList<Card> inOrderCards) {
		this.inOrderCards = inOrderCards;
	}
	
	/*public void addCardInOrder(Card c){
		boolean added = false;
		Card current;
		for(int i = 0; i < inOrderCards.size(); i++){
			current = inOrderCards.get(i);
			if(c.getCardId().equals(current.getCardId())){
				added = true;
				break;
			}
			if(c.getCost() <current.getCost() || (c.getCost() == current.getCost() && c.getName().compareTo(current.getName()) < 0)){
				inOrderCards.add(i, c);
				added = true;
				break;
			}
		}
		if(!added){
			inOrderCards.add(c);
		}
	}*/

	public void addObserver(ModelObserver o) {
		observers.add(o);
	}

	public void removeObserver(ModelObserver o) {
		if(observers.contains(o)){
			observers.remove(o);
		}
	}

	public void notifyObservers() {
		for(ModelObserver model : observers){
			model.update(this);
		}
	}
	
}
