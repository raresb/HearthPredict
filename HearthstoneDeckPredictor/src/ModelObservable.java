
public interface ModelObservable {
	
	public void addObserver(ModelObserver o);
	
	public void removeObserver(ModelObserver o);
	
	public void notifyObservers();
	
	public void setChanged(boolean changed);
	
}
