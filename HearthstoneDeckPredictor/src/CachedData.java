import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class CachedData {
		
		public static ArrayList<ArrayList<Deck>> allDecks;
		public static ArrayList<Card> allCards;
		public static HashMap<String,String> nameToId;
		public static HashMap<String,Card> nameToCard;
		public static HashMap<String,String> idToName;
		public static HashMap<String,Card> idToCard;
		
		public static void makeCache() throws IOException{
			allDecks = DeckUtility.makeAllDecksFromFile("decks\\");
			allCards = HearthstoneJsonParser.makeCards("src\\files\\JSON\\HearthstoneJson\\cards.collectible.json");
			System.out.println("All images loaded");
			nameToId = new HashMap<String, String>();
			nameToCard = new HashMap<String, Card>();
			idToName = new HashMap<String, String>();
			idToCard = new HashMap<String, Card>();
			for(Card c : allCards){
				//System.out.println(c.getName() + " " + c.getCardId());
				nameToId.put(c.getName(), c.getCardId());
				nameToCard.put(c.getName(), c);
				idToName.put(c.getCardId(), c.getName());
				idToCard.put(c.getCardId(), c);
			}
		}
}
