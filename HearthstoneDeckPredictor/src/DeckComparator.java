import java.util.ArrayList;
import java.util.HashMap;


public class DeckComparator {
		private ArrayList<Deck> decksToCheck; 
		private ArrayList<Integer> comparisonToDecks;
		private Deck currentDeck;
		
		public DeckComparator(ArrayList<Deck> decksToCheck){
			this.decksToCheck = decksToCheck;
			currentDeck = new Deck(new HashMap<String, Integer>());
			comparisonToDecks = new ArrayList<Integer>();
			for(int i = 0; i < decksToCheck.size(); i++){
				comparisonToDecks.add(0);
			}
			//System.out.println(comparisonToDecks.size());
		}
		
		/*public void restart(){
			for(int i = 0; i < comparisonToDecks.size(); i++){
				comparisonToDecks.set(i, 0);
			}
			currentDeck = new Deck(new HashMap<String, Integer>());
			
		}*/
		
		public void newCard(String name){
			currentDeck.addCard(name, 1);
			//System.out.println(name);
			//System.out.println(decksToCheck.size());
			for(int i = 0; i < decksToCheck.size(); i++){
				//System.out.println(currentDeck.getDeck().size());
				//System.out.println(decksToCheck.size());
				//System.out.println(comparisonToDecks.size());
				//System.out.println("Here");
				comparisonToDecks.set(i, comparisonToDecks.get(i)+currentDeck.similarityToDeckPlusNewCard(decksToCheck.get(i), name));
				//System.out.println("Here");
			}
		}
		
		public void calculateAllSimilarity(){
			for(int i = 0; i < decksToCheck.size(); i++){
				comparisonToDecks.add(currentDeck.similarityToDeck(decksToCheck.get(i)));
			}
		}
		
		public Deck closestDeck(){
			Deck closest = null;
			int maxSimilarity = -1;
			//System.out.println(comparisonToDecks);
			for(int i = 0; i < decksToCheck.size(); i++){
				if(comparisonToDecks.get(i) > maxSimilarity){
					closest = decksToCheck.get(i);
					maxSimilarity = comparisonToDecks.get(i);
				}
			}
			return closest;
		}

		public ArrayList<Deck> getDecksToCheck() {
			return decksToCheck;
		}

		public void setDecksToCheck(ArrayList<Deck> decksToCheck) {
			this.decksToCheck = decksToCheck;
		}

		public ArrayList<Integer> getComparisonToDecks() {
			return comparisonToDecks;
		}

		public void setComparisonToDecks(ArrayList<Integer> comparisonToDecks) {
			this.comparisonToDecks = comparisonToDecks;
		}

		public Deck getCurrentDeck() {
			return currentDeck;
		}

		public void setCurrentDeck(Deck currentDeck) {
			this.currentDeck = currentDeck;
		}
}
