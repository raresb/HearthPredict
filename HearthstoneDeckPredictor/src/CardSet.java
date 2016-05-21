import java.util.EnumSet;


public enum CardSet {
	HERO_SKINS(0), REWARD(1), PROMO(2), CORE(3), EXPERT1(4), NAXX(5), GVG(6), BRM(7), TGT(8), LOE(9), OG(10);
	
	private int value;
	
	CardSet(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public static CardSet fromString(String input){
		for(CardSet s : EnumSet.allOf(CardSet.class)){
			if(input.equalsIgnoreCase(s.name())){
				return s;
			}
		}
		return null;
	}

}
