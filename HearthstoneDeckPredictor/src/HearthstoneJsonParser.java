import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class HearthstoneJsonParser {

	private static String readFile(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(path));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
		}
		reader.close();
		return sb.toString();

	}

	private static Card makeCardFromJsonObject(JsonObject obj){
		Card c = new Card(CardRarity.fromString(obj.getString("rarity")), 
				CardSet.fromString(obj.getString("set")), 
				CardType.fromString(obj.getString("type")),
				obj.getString("id"), obj.getString("name"),obj.getInt("cost",-1),
				obj.getInt("attack",-1),obj.getInt("health",-1),
				obj.getInt("durability",-1));
		/*if(c.getName().contains(";")){
			System.out.println(c.getName());
		}*/
		return c;
	}

	public static ArrayList<Card> makeCards(String path) throws IOException{
		String json = readFile(path);
		StringReader reader = new StringReader(json);
		JsonReader jsonReader = Json.createReader(reader);
		JsonArray JsonCards = jsonReader.readArray();
		int numCards = JsonCards.size();
		ArrayList<Card> cards = new ArrayList<Card>();
		for(int i = 0; i < numCards; i++){
			JsonObject card = JsonCards.getJsonObject(i); 
			cards.add(makeCardFromJsonObject(card));
		}
		reader.close();
		/*HashSet<String> CardType = new HashSet<String>();
		for(Card c : cards){
			CardType.add(c.getRarity().name());
		}
		System.out.println(CardType);*/
		return cards;

	}


}
