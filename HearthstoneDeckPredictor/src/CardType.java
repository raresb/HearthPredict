import java.util.EnumSet;


public enum CardType {
	HERO(0), MINION(1), SPELL(2), WEAPON(3);
	
	private int value;
	
	CardType(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public static CardType fromString(String input){
		for(CardType t : EnumSet.allOf(CardType.class)){
			if(input.equalsIgnoreCase(t.name())){
				return t;
			}
		}
		return null;
	}
}
