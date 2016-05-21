import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class CacheTempostormDecks {
	
	private String URL;
	private boolean isStandard;
	private int snapshotEdition;
	private int decks;
	
	public CacheTempostormDecks(String path) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line = reader.readLine();
		String[] info = line.split(Main.DELIMITER);
		
	}
	
}
