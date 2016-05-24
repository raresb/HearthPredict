import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;


public class Main {
	
	public final static String DELIMITER = ";";
	public static void main(String[] args) throws Exception{
		//CacheHearthpwnDecks.cacheBasicDecks();
		/*DeckComparator compare = new DeckComparator(DeckUtility.makeDecksFromFile("druidDecks.txt"));
		compare.setCurrentDeck(new Deck(DeckClass.DRUID,"New Malygos Druid;Moonfire;2;Living Roots;2;Raven Idol;2;Wild Growth;2;Wrath;2;Druid of the Flame;2;Feral Rage;2;Fandral Staghelm;1"));
		compare.calculateAllSimilarity();
		for(int d : compare.getComparisonToDecks()){
			System.out.println(d);
		}
		System.out.print(compare.closestDeck());*/
		/*ZoneLogReader zlr = new ZoneLogReader("D:\\Games\\Hearthstone\\Logs\\Zone.log");
		System.out.println(zlr.getOpponentHero("D 23:36:48.9161130 ZoneChangeList.ProcessChanges() - id=1 local=False [name=Jaina Proudmoore id=64 zone=PLAY zonePos=0 cardId=HERO_08 player=1] " +
				"zone from  -> OPPOSING PLAY (Hero)"));
		Thread t = new Thread(zlr);
		t.start();*/
		//HearthPredict hp = new HearthPredict();
		CachedData.makeCache();
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
        JFrame frame = new JFrame("Transparent Window");
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setAlwaysOnTop(true);
        // Without this, the window is draggable from any non transparent
        // point, including points  inside textboxes.
        frame.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
        frame.getContentPane().setLayout(new java.awt.BorderLayout());
        frame.getContentPane().add(pan);
        frame.pack();
        frame.setVisible(true);
        
		//System.out.println(ParseJson.makeCards("src//files//cards.collectible.json"));
		/*Deck d = new Deck("C'Thun Druid,Innervate,2,Darnassus Aspirant,2,Wild Growth,2,Wrath,2,Mulch,1,Klaxxi Amber-Weaver,2,Swipe,2," +
				"Nourish,1,Dark Arakkoa,2,Beckoner of Evil,2,Crazed Alchemist,2,Twilight Geomancer,2,Brann Bronzebeard,1,Disciple of C'Thun,2," +
				"Defender of Argus,1,Azure Drake,2,Doomcaller,1,C'Thun,1,");
		Deck d1 = new Deck("Get C'Thuned,Innervate,2,Darnassus Aspirant,2,Wild Growth,2,Wrath,2,Mulch,1,Klaxxi Amber-Weaver,2," +
				"Swipe,2,Nourish,1,Dark Arakkoa,1,Beckoner of Evil,2,Brann Bronzebeard,1,Disciple of C'Thun,2,Twilight Elder,2," +
				"Defender of Argus,1,Azure Drake,2,Crazed Worshipper,2,Twin Emperor Vek'lor,1,Doomcaller,1,C'Thun,1,");

		//System.out.println(d);
		System.out.println(d.distanceToDeck(d1));*/
		/*System.out.println(TempostormJsonParser.getCards("https://tempostorm.com/api/decks/findOne?filter=" +
				"{%22where%22:{%22slug%22:%22aggro-shaman-standard-snapshot-1%22},%22fields%22:{}," +
				"%22include%22:[{%22relation%22:%22cards%22,%22scope%22:{%22include%22:[%22card%22]}}]}"));
				*/
		/*System.out.println(ZoneLogParser.getOpponentCardPlayed("D 22:25:55.6673001 ZoneChangeList.ProcessChanges() - " +
				"id=16 local=False [name=Cruel Taskmaster id=43 zone=PLAY zonePos=7 cardId=EX1_603 player=2] zone from OPPOSING HAND -> OPPOSING PLAY"));*/

	}
}
