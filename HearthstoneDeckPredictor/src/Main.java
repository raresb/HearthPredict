import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;


public class Main {
	
	public final static String DELIMITER = ";";
	public static void main(String[] args) throws Exception{

		//CacheHearthpwnDecks.cacheBasicDecks();
		CachedData.makeCache();
		CachedData.makeImageCache();
		HearthPredictModel model = new HearthPredictModel();
		OpponentTrackPanel pan = new OpponentTrackPanel();
		pan.setPreferredSize(new Dimension(300,800));
		model.addObserver(pan);
		HearthPredictController controller = new HearthPredictController(model);
		LogReader zone = new LogReader(controller, "D:\\Games\\Hearthstone\\Logs\\Zone.log");
		Thread t1 = new Thread(zone);
		LogReader power = new LogReader(controller, "D:\\Games\\Hearthstone\\Logs\\Power.log");
		Thread t2 = new Thread(power);
		t1.start();
		t2.start();
        model.getFrame().getContentPane().add(pan);
        model.getFrame().displayContents();
        
        
	}
}
