import java.util.EnumSet;


public enum DeckClass {
	DRUID(0), HUNTER(1), MAGE(2), PALADIN(3),
	PRIEST(4), ROGUE(5), SHAMAN(6), WARLOCK(7),
	WARRIOR(8), ANY(9);
	
	private int value;
	
	DeckClass(int value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static DeckClass fromString(String input){
		for(DeckClass t : EnumSet.allOf(DeckClass.class)){
			if(input.equalsIgnoreCase(t.name())){
				return t;
			}
		}
		return null;
	}
	
}
