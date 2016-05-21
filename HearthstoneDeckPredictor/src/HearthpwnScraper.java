import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HearthpwnScraper {

	final static String BASEURL = "http://www.hearthpwn.com/";
	
	// purpose: retrieves the cards from a specific specified by url
	// parameters: url - URL containing http://www.hearthpwn.com/decks
	// returns: String Main.DELIMITERed by semicolons containing 1 (ONE) entry 
	//          which contains i entries of the form CardName,Quantity ending with \r\n
	// depends on: ---
	public static String getCards(String url) throws IOException{
		String name;
		int amt;
		Document doc = Jsoup.connect(url).timeout(10*1000).userAgent("Mozilla").get();
		Elements cards = doc.select("[data-dust]"); 
		StringBuilder sb = new StringBuilder();
		for(Element card : cards){
			name = card.text();
			amt = Integer.parseInt(card.attr("data-Count"));
			sb.append(name + Main.DELIMITER + amt + Main.DELIMITER);
			//System.out.println("card: " + name + " count: " + amt);
		}
		sb.append("\r\n");
		
		return sb.toString();
	}

	// purpose: retrieve the cards and deck names of decks from the specified hearthpwn link
	// parameters: url - URL containing http://www.hearthpwn.com/decks
	// returns: String deliminated by semicolons containing n entries, each starting with DeckName 
	// 			and then i entries of the form CardName,Quantity ending with \r\n
	// depends on: ScraperUtility -> getCard
	public static String getDecks(String url) throws IOException{
		Element e;
		String deckLink, deckName;
		Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
		Elements decks = doc.getElementsByClass("tip").not(".innkeeper");
		StringBuilder sb = new StringBuilder();
		for(Element deck : decks){
			e = deck.select("a").first();
			if(e != null){
				//System.out.println("Got Deck");
				deckLink = BASEURL + e.attr("href");
				deckName = e.text();
				sb.append(deckName + Main.DELIMITER + getCards(deckLink));
				//System.out.println("deck name: " + deckName + " link: " + deckLink);
			}
		}
		return sb.toString();
	}
}	
