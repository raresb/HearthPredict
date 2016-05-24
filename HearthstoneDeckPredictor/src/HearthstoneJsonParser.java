import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;

import javax.imageio.ImageIO;
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

	private static String getImgName(String name){
		String imgName = "";
		name = name.toLowerCase();
		char c;
		for(int i = 0; i < name.length(); i++){
			c = name.charAt(i);
			if((c <= 'z' && c >= 'a') || (c <= '9' && c>= '0')){
				imgName += c;
			}else if(c == ' ' || c== '\'' || c == '-'){
				imgName += '-';
			}
		}
		imgName += ".png";
		return imgName;
	}
	
	private static Card makeCardFromJsonObject(JsonObject obj){
		BufferedImage img = null;
		try {
			if(obj.getString("set").equals("OG")){
				img = ImageIO.read(new File("Images\\Bars\\" + obj.getString("id") + ".png"));
			}else if(obj.getString("type").equals("HERO")){
				
			}else{
				img = ImageIO.read(new File("Images\\" + getImgName(obj.getString("name"))));
			}
		} catch (IOException e) {
			//System.out.println(("Images\\" + getImgName(obj.getString("name")) + " " + ("Images\\Bars\\" + obj.getString("id") + ".png")) + " " + obj.getString("set"));
			e.printStackTrace();
		}
		Card c = new Card(CardRarity.fromString(obj.getString("rarity")), 
				CardSet.fromString(obj.getString("set")), 
				CardType.fromString(obj.getString("type")),
				obj.getString("name"),obj.getString("id"),obj.getInt("cost",-1),
				obj.getInt("attack",-1),obj.getInt("health",-1),
				obj.getInt("durability",-1),img);
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
