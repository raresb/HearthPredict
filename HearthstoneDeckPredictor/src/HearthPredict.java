import java.io.IOException;
import java.util.ArrayList;


public class HearthPredict implements CardObserver, GameStateObserver{
	
		//ArrayList<ArrayList<Deck>> allDecks = DeckUtility.makeAllDecksFromFile("");
		//ArrayList<Card> allCards = HearthstoneJsonParser.makeCards("src\\flies\\JSON\\HearthstoneJson");
		private ArrayList<ArrayList<Deck>> allDecks;
		private ArrayList<Card> allCards;
		private DeckComparator deckCompare;
		private boolean gameLive = false;
		private ZoneLogReader log1;
		private PowerLogReader log2;
		
		public HearthPredict() throws IOException{
			allDecks = DeckUtility.makeAllDecksFromFile("");
			//System.out.println(allDecks.size());
			/*for(ArrayList<Deck> d : allDecks){
				for(Deck deck : d){
					System.out.println("deck");
				}
			}*/
			allCards = HearthstoneJsonParser.makeCards("src\\files\\JSON\\HearthstoneJson\\cards.collectible.json");
			//System.out.println(deckCompare.getDecksToCheck().size());
			log1 = new ZoneLogReader("D:\\Games\\Hearthstone\\Logs\\Zone.log");
			log1.addObserver(this);
			
			log2 = new PowerLogReader("D:\\Games\\Hearthstone\\Logs\\Power.log");
			log2.addObserver(this);
			
			Thread t1 = new Thread(log1);
			Thread t2 = new Thread(log2);
			
			t1.start();
			t2.start();
		}
		
		public void update(boolean gameLive){
			this.gameLive = gameLive;
			System.out.println("The game is live is " + gameLive);
			if(!gameLive){
				log1.reset();
			}
		}
		
		public void updateCard(String lastCard) {
			System.out.println("CARD UPDATE!");
			System.out.println(lastCard);
			if(!lastCard.equals("")){
				deckCompare.newCard(lastCard);
				System.out.println(deckCompare.closestDeck());
			}
			
		}

		public void updateHero(DeckClass d) {
			deckCompare = new DeckComparator(DeckUtility.getClassDecks(d, allDecks));
		}
}
