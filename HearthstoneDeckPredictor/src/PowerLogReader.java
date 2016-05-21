import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class PowerLogReader implements Runnable, GameStateObservable {

	private final String gameState = "GameState.DebugPrintPower()";
	private final String start = "CREATE_GAME";
	private final String end = "value=WON";
	
	private ArrayList<GameStateObserver> observers = new ArrayList<GameStateObserver>();
	private boolean keepReading = true;
	private boolean gameLive = false;
	private String logPath;
	private boolean hasChanged = false;

	public PowerLogReader(String logPath){
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
	
	public void processLine(String line){
		boolean hasChanged = true;
		if(isStart(line)){
			gameLive = true;
		}else if(isEnd(line)){
			gameLive = false;
		}else{
			hasChanged = false;
		}
		this.hasChanged = hasChanged;
		notifyObservers();
	}
	
	public boolean isStart(String line){
		if(line.contains(gameState) && line.contains(start)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isEnd(String line){
		if(line.contains(gameState) && line.contains(end)){
			return true;
		}else{
			return false;
		}
	}
	
	public void addObserver(GameStateObserver o) {
		observers.add(o);
	}

	public void removeObserver(GameStateObserver o) {
		int position = observers.indexOf(o);
		if(position >= 0){
			observers.remove(o);
		}
	}

	public void notifyObservers() {
		if(hasChanged){
			for(int i = 0; i < observers.size(); i++){
				observers.get(i).update(gameLive);
			}
		}
	}

	public void setChanged(boolean hasChanged) {
		this.hasChanged = hasChanged;

	}



}
