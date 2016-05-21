import java.util.EnumSet;


public enum CardRarity {
	FREE(0), COMMON(1), RARE(2), EPIC(3), LEGENDARY(4);
	
	private int value;
	
	CardRarity(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public static CardRarity fromString(String input){
		for(CardRarity r : EnumSet.allOf(CardRarity.class)){
			if(input.equalsIgnoreCase(r.name())){
				return r;
			}
		}
		return null;
	}
}
