import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;


public class ZoneLogReader implements Runnable, CardObservable{
	
	private final String handToPlay = "zone from OPPOSING HAND";
	private final String secretToPlay = "zone from OPPOSING SECRET";
	private final String opponentHero = "zone from  -> OPPOSING PLAY (Hero)";
	// RESPPONSIBILITY OF DETECTING STARTING AND ENDING HAS BEEN MOVED TO PowerLogReader
	//private final String startMsg = "ZoneMgr.AddServerZoneChanges() - taskListId=1 "; 
	//private final String endMsg = "value=LOSING"; //TEMPORARY Doesn't work when someone concedes, find a new way to 
	//detect losses in the zone log file or use power log file
	
	private final int startId = 4;
	private final int endId = 67;
	
	private DeckClass currentHero;
	private String logPath;
	private boolean keepReading = true;
	private String lastCard = "";
	private boolean hasChanged;
	private ArrayList<CardObserver> observers = new ArrayList<CardObserver>();
	private HashSet<Integer> idsSeen = new HashSet<Integer>();


	public ZoneLogReader(String logPath){
		this.logPath = logPath;
	}

	public void run() {
		try{
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(new File(logPath)));
			while(keepReading){
				line = reader.readLine();
				//System.out.println(line);
				if(line != null){
					//System.out.println(line);
					processLine(line);
					//System.out.println(line);
				}else{
					Thread.sleep(100);
				}
			}
			reader.close();
		}catch (Exception e) {
			e.printStackTrace();
			//System.err.println("Error: " + e.getMessage());
		}
	}

	public void reset(){
		
		
		
		idsSeen.clear();
		hasChanged = false;
		lastCard = "";
	}
	
	public void processLine(String line){
		//System.out.println(line);
		String card;
		DeckClass dClass;
		boolean hasChanged = true;
		/*if(isStart(line)){
			gameLive = true;
			idsSeen.clear();
			System.out.println("New game is live");
		}else if(isEnd(line)){
			gameLive = false;
			System.out.println("Game has ended");
		}else*/ 
		if(!(card = getOpponentCardPlayed(line)).equals("")){
			lastCard = card;
			System.out.println(card + " was played by opponent");
		}else if((dClass = getOpponentHero(line)) != null){
			currentHero = dClass;
			System.out.println("Opponent is a " + dClass.name());
			*****MUST CHANGE**********
			observers.get(0).updateHero(currentHero);
		}else{
			hasChanged = false;
		}
		this.hasChanged = hasChanged;
		notifyObservers();
	}

	private DeckClass nameToDeckClass(String name){
		//DRUID
		if(name.contains("Malfurion")){
			return DeckClass.DRUID;
		//HUNTER
		}else if(name.contains("Rexxar") || name.contains("Alleria")){
			return DeckClass.HUNTER;
		//MAGE
		}else if(name.contains("Jaina") || name.contains("Medivh") || name.contains("Khadgar")){
			return DeckClass.MAGE;
		//PALADIN
		}else if(name.contains("Uther") || name.contains("Liadrin")){
			return DeckClass.PALADIN;
		//PRIEST
		}else if(name.contains("Anduin")){
			return DeckClass.PRIEST;
		//ROGUE
		}else if(name.contains("Valeera")){
			return DeckClass.ROGUE;
		//SHAMAN
		}else if(name.contains("Thrall")){
			return DeckClass.SHAMAN;
		//WARLOCK
		}else if(name.contains("Gul'dan")){
			return DeckClass.WARLOCK;
		//WARRIOR
		}else if(name.contains("Garrosh") || name.contains("Magni")){
			return DeckClass.WARRIOR;
		//NOT FOUND
		}else{
			return null;
		}
	}
	
	public DeckClass getOpponentHero(String line){
		DeckClass dClass = null;
		int nameIndex;
		int idIndex;
		int id;
		String name;
		if(line.contains(opponentHero)){
			nameIndex = line.indexOf("[name=");
			if(nameIndex >= 0){
				idIndex = line.indexOf("id=",nameIndex);
				name = line.substring(nameIndex+6,idIndex-1);
				dClass = nameToDeckClass(name);
				id = Integer.parseInt(line.substring(idIndex+3,line.indexOf("zone=",idIndex)-1));
				if(id < startId || id > endId || idsSeen.contains(id)){
					dClass = null;
				}else{
					idsSeen.add(id);
				}
			}
		}
		return dClass;
	}
	
	private String getOpponentCardPlayed(String line){
		String cardName = "";
		int nameIndex;
		int idIndex;
		int id;
		if(line.contains(handToPlay) || line.contains(secretToPlay)){
			nameIndex = line.indexOf("[name=");
			if(nameIndex >= 0){
				idIndex = line.indexOf("id=",nameIndex);
				cardName = line.substring(nameIndex+6,idIndex-1);
				id = Integer.parseInt(line.substring(idIndex+3,line.indexOf("zone=",idIndex)-1));
				if(id < startId || id > endId || idsSeen.contains(id)){
					cardName = "";
				}else{
					idsSeen.add(id);
				}
			}
		}
		return cardName;
	}

	//detect when a friendly card has been drawn or played from deck to PLAY or GRAVEYARD immediately 
	//deck tracker basically...
	//NOT COMPLETED DONT USE
	private String getFriendlyCardPlayed(String line){
		String cardName = "";
		int nameIndex;
		int idIndex;
		int id;
		if(line.contains("")){
			nameIndex = line.indexOf("[name=");
			if(nameIndex >= 0){
				idIndex = line.indexOf("id=",nameIndex);
				cardName = line.substring(nameIndex+6,idIndex-1);
				id = Integer.parseInt(line.substring(idIndex+3,line.indexOf("zone=",idIndex)-1));
				if(id < startId || id > endId){
					cardName = "";
				}
			}
		}
		return cardName;
	}


	/*private boolean isStart(String line){
		return line.contains(startMsg);
	}
	
	private boolean isEnd(String line){
		return line.contains(endMsg);
	}*/

	public void addObserver(CardObserver o) {
		observers.add(o);
	}

	public void notifyObservers() {
		if(hasChanged){
			for(int i = 0; i < observers.size(); i++){
				//System.out.println(lastCard);
				observers.get(i).updateCard(lastCard);
			}
			hasChanged = false;
		}
	}

	public void removeObserver(CardObserver o) {
		int position = observers.indexOf(o);
		if(position >= 0){
			observers.remove(o);
		}
	}

	public void setChanged(boolean hasChanged) {
		this.hasChanged = hasChanged;
	}

	public void setKeepReading(boolean keepReading) {
		this.keepReading = keepReading;
	}





}
