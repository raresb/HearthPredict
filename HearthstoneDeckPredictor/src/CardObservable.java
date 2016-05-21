
public interface CardObservable {
	
		public void addObserver(CardObserver o);
		
		public void notifyObservers();
		
		public void removeObserver(CardObserver o);
		
		public void setChanged(boolean hasChanged);
		
}
