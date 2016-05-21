import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


public class HearthPredictModel {	
	private ArrayList<ArrayList<Deck>> allDecks;
	private ArrayList<Card> allCards;
	private DeckComparator deckCompare;
	private boolean gameLive = false;
	private DeckClass oppHero;
	private HashSet<Integer> idsSeen = new HashSet<Integer>();
	
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
	
	
}
