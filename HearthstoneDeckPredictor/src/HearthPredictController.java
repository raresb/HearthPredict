
public class HearthPredictController {
	
	private final String handToPlay = "zone from OPPOSING HAND";
	private final String secretToPlay = "zone from OPPOSING SECRET";
	private final String deckToPlay = "zone from OPPOSING DECK";
	private final String opponentHero = "zone from  -> OPPOSING PLAY (Hero)";

	private final String gameState = "GameState.DebugPrintPower()";
	private final String start = "CREATE_GAME";
	private final String endWon = "value=WON";
	private final String endTied = "value=TIED";
	
	//need to change how this is represented
	private final int startId = 4;
	private final int endId = 80;
	
	private HearthPredictModel model;
	
	public HearthPredictController(HearthPredictModel model){
		this.model = model;
	}
	
	public void processLine(String line){
		String card;
		DeckClass dClass; 
		if(!(card = getOpponentCardPlayed(line)).equals("")){
			model.getDeckCompare().newCard(card);
			System.out.println(card + " was played by opponent");
			
			//System.out.println(model.getDeckCompare().getCurrentDeck().getDeck());
			//System.out.println(model.getInOrderCards());
			//System.out.println(model.getDeckCompare().closestDeck().getDeck());
			model.notifyObservers(); 
		}else if((dClass = getOpponentHero(line)) != null){
			model.setOppHero(dClass);
			model.setDeckCompare(new DeckComparator(DeckUtility.getClassDecks(model.getOppHero(), model.getAllDecks())));
			System.out.println("Opponent is a " + dClass.name());
		}else if(isStart(line)){
			model.reset();
			model.setGameLive(true);
			System.out.println("Game has started");
		}else if(isEnd(line)){
			model.setGameLive(false);
			model.reset();
			System.out.println("Game has ended");
		}
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
			//System.out.println("contains hero");
			nameIndex = line.indexOf("[name=");
			//System.out.println(nameIndex);
			if(nameIndex >= 0){
				idIndex = line.indexOf("id=",nameIndex);
				name = line.substring(nameIndex+6,idIndex-1);
				//System.out.println(name);
				dClass = nameToDeckClass(name);
				id = Integer.parseInt(line.substring(idIndex+3,line.indexOf("zone=",idIndex)-1));

				//System.out.println(model.getIdsSeen().contains(id));
				if(id < startId || id > endId || model.getIdsSeen().contains(id)){
					dClass = null;
				}else{
					model.getIdsSeen().add(id);
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
		if(line.contains(handToPlay) || line.contains(secretToPlay) || line.contains(deckToPlay)){
			nameIndex = line.indexOf("[name=");
			if(nameIndex >= 0){
				idIndex = line.indexOf("id=",nameIndex);
				cardName = line.substring(nameIndex+6,idIndex-1);
				id = Integer.parseInt(line.substring(idIndex+3,line.indexOf("zone=",idIndex)-1));
				if(id < startId || id > endId || model.getIdsSeen().contains(id)){
					cardName = "";
				}else{
					model.getIdsSeen().add(id);
				}
			}
		}
		return cardName;
	}
	
	public boolean isStart(String line){
		if(line.contains(gameState) && line.contains(start)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isEnd(String line){
		if(line.contains(gameState) && (line.contains(endWon) || line.contains(endTied))){
			return true;
		}else{
			return false;
		}
	}

	public HearthPredictModel getModel() {
		return model;
	}

	public void setModel(HearthPredictModel model) {
		this.model = model;
	}
	
}
