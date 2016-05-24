import java.awt.Graphics;
import java.util.Map;

import javax.swing.JPanel;

public class OpponentTrackPanel extends JPanel implements ModelObserver {

	HearthPredictModel model = null;

	int x = 0, y = 0;
	
	public OpponentTrackPanel(){
		super();
		this.setOpaque(false);
		this.setLayout(null);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		x = 0;
		y = 0;
		//System.out.println("Here");
		if(model != null && model.isGameLive()){
			System.out.println("Drawing");
			for(CardPair pair : DeckUtility.mapDeckToSortedPairDeck(model.getDeckCompare().closestDeck().getDeck())){
				//System.out.println(pair.getCard().getName() + " " + pair.getQuantity());
				for(int i = 0; i < pair.getQuantity(); i++){
					g.drawImage(pair.getCard().getCardImg(),x,y,null);
					y += pair.getCard().getCardImg().getHeight();
				}
			}
		}
	}

	public void update(HearthPredictModel model) {
		System.out.println("Update");
		this.model = model;
		revalidate();
		repaint();
	}

}
