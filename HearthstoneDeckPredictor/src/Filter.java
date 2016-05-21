
public enum Filter {
	HOT(1), NEW(2), TOPWEEK(3), 
	TOPMONTH(4), TOPALL(5);
	
	private int value;
	
	Filter(int value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
