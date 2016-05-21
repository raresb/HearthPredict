
public interface GameStateObservable {
	
		public void addObserver(GameStateObserver o);
		
		public void removeObserver(GameStateObserver o);
		
		public void notifyObservers();
		
		public void setChanged(boolean hasChanged);
		
}
