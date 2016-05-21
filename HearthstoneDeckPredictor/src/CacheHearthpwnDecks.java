import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class CacheHearthpwnDecks {

	final String CLASSFILTER = "filter-class=";
	final String DECKFILTER = "filter-deck-tag=";
	final String CONSTRUCTEDFILTER = "filter-show-constructed-only=";
	final String STANDARDFILTER = "filter-show-standard=";
	final String PAGEFILTER = "page=";
	final String BASEURL = "http://www.hearthpwn.com/decks?";


	private DeckClass dClass;
	private Filter filter;
	private int page;
	private boolean isStandard;
	private boolean isConstructed;
	private String properties;
	private String URL;

	public CacheHearthpwnDecks(DeckClass dClass, Filter filter, int page,
			boolean standard, boolean constructed) {
		this.dClass = dClass;
		this.filter = filter;
		this.page = page;
		this.isStandard = standard;
		this.isConstructed = constructed;
		this.properties = System.currentTimeMillis() + Main.DELIMITER + dClass.name() + Main.DELIMITER
				+ filter.name() + Main.DELIMITER + page + Main.DELIMITER + standard + Main.DELIMITER + constructed + ",\r\n";
		updateURL();
	}

	public DeckClass getdClass() {
		return dClass;
	}

	public Filter getFilter() {
		return filter;
	}

	public int getPage() {
		return page;
	}

	public boolean isStandard() {
		return isStandard;
	}

	public boolean isConstructed() {
		return isConstructed;
	}

	public String getProperties() {
		return properties;
	}

	public String getURL() {
		return URL;
	}

	public void updateURL(){
		this.URL = BASEURL + CLASSFILTER + powerOfTwo(dClass.getValue()+2) + "&" + DECKFILTER + filter.getValue()
				+ "&" + CONSTRUCTEDFILTER + isConstructed + "&" + STANDARDFILTER + isStandard + "&" + PAGEFILTER + page; 
	}

	private static int powerOfTwo(int power){
		int value = 1;
		for(int i = 0; i < power; i++){
			value*=2;
		}
		return value;
	}

	public void writeToFile() throws IOException{
		FileWriter writer = new FileWriter(dClass.name().toLowerCase() + "Decks.txt");
		BufferedWriter bufferedWriter = new BufferedWriter(writer);
		bufferedWriter.write(properties);
		bufferedWriter.write(HearthpwnScraper.getDecks(URL));
		bufferedWriter.close();
	}

	public static void cacheBasicDecks() throws IOException{
		CacheHearthpwnDecks cd;
		Long start,end;
		start = System.currentTimeMillis();
		for(DeckClass d : DeckClass.values()){
			if(!d.equals(DeckClass.ANY)){
				cd = new CacheHearthpwnDecks(d,Filter.HOT,1,true,true);
				System.out.println(cd.URL);
				cd.writeToFile();
				end = System.currentTimeMillis();
				System.out.println("Done " + d.name() + "in "+ (end-start));
				start = end;
			}
		}
	}


}
