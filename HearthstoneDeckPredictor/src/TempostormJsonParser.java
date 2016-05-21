import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class TempostormJsonParser {
	
	private static String getJsonFromUrl(String url) throws IOException{
		StringBuilder sb = new StringBuilder();
		URL tempostorm = new URL(url);
		BufferedReader reader = new BufferedReader(new InputStreamReader(tempostorm.openStream()));
		String line;
		while((line = reader.readLine()) != null){
			sb.append(line);
		}
		reader.close();
		return sb.toString();
	}
	
	public static String getCards(String url) throws IOException{
		StringBuilder deck = new StringBuilder();
		String deckName, className, cardName;
		int cardQuantity;
		String json = getJsonFromUrl(url);
		StringReader reader = new StringReader(json);
		
		JsonReader jsonReader = Json.createReader(reader);
		JsonObject info = jsonReader.readObject();
		JsonObject card;
		
		deckName = info.getString("name");
		className = info.getString("heroName");
		JsonArray cards = info.getJsonArray("cards");
		
		deck.append(deckName + Main.DELIMITER + className + Main.DELIMITER);
		
		int size = cards.size();
		
		for(int i = 0; i < size; i++){
			card = cards.getJsonObject(i);
			cardQuantity = card.getInt("cardQuantity");
			cardName = card.getJsonObject("card").getString("name");
			deck.append(cardName + Main.DELIMITER + cardQuantity + Main.DELIMITER);
		}
		deck.append("\r\n");
		return deck.toString();
		
	}
	
}
