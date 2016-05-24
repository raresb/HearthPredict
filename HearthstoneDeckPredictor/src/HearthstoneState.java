import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class HearthstoneState implements Runnable {

	HearthPredictController controller;
	
	public HearthstoneState(HearthPredictController controller){
		this.controller = controller;
	}

	public void run(){
		
		while(true){
			
			String line;
			String pidInfo ="";

			
			try {
				Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
				BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));

				while ((line = input.readLine()) != null){
					pidInfo+=line; 
				}
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			

			if(pidInfo.contains("Hearthstone.exe")){
				controller.getModel().setGameRunning(true);
				System.out.println("Hearthstone is running");
			}else{
				controller.getModel().setGameRunning(false);
				System.out.println("Heartstone is not running");
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

	}
}
