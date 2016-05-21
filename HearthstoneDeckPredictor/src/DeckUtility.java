import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class DeckUtility {
	
		public static ArrayList<ArrayList<Deck>> makeAllDecksFromFile(String path) throws IOException{
			ArrayList<ArrayList<Deck>> allDecks = new ArrayList<ArrayList<Deck>>();
			for(DeckClass dClass : DeckClass.values()){
				if(!dClass.equals(DeckClass.ANY)){
					//System.out.println((path + dClass.name().toLowerCase()+"Decks.txt"));
					allDecks.add(makeDecksFromFile(path + dClass.name().toLowerCase()+"Decks.txt"));
					
				}
			}
			return allDecks;
		}
	
		public static ArrayList<Deck> makeDecksFromFile(String path) throws IOException{
			ArrayList<Deck> decks = new ArrayList<Deck>();
			Deck d;
			String line, properties;
			String[] propertiesSplit;
			BufferedReader reader = new BufferedReader(new FileReader(path));
			properties = reader.readLine();
			//System.out.println(properties);
			propertiesSplit = properties.split(Main.DELIMITER);
			DeckClass dClass = DeckClass.fromString(propertiesSplit[1]);
			while((line = reader.readLine()) != null){
				d = new Deck(dClass,line);
				decks.add(d);
			}
			reader.close();
			return decks;
		} 
		
		public static ArrayList<Deck> getClassDecks(DeckClass dClass, ArrayList<ArrayList<Deck>> allDecks){
			ArrayList<Deck> classDecks = new ArrayList<Deck>();
			for(ArrayList<Deck> decks : allDecks){
				//System.out.println(decks.get(0).getdClass());
				if(!decks.isEmpty() && decks.get(0).getdClass().equals(dClass)){
					return decks;
				}
			}
			return classDecks;
		}
}
