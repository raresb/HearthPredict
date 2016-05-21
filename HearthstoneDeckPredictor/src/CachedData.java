import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class CachedData {
		
		public static ArrayList<ArrayList<Deck>> allDecks;
		public static ArrayList<Card> allCards;
		public static HashMap<String,String> nameToId;
		public static HashMap<String,String> idToName;
		public static HashMap<String,Card> idToCard;
		
		public static void makeCache() throws IOException{
			allDecks = DeckUtility.makeAllDecksFromFile("");
			allCards = HearthstoneJsonParser.makeCards("src\\files\\JSON\\HearthstoneJson\\cards.collectible.json");
			for(Card c : allCards){
				nameToId.put(c.getName(), c.getCardId());
				idToName.put(c.getCardId(), c.getName());
				idToCard.put(c.getCardId(), c);
			}
		}
}
