import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;


public class CardDisplay {
		
	Card card; 
	int actualQuantity, seenQuantity;
	int x, y;
	
	public CardDisplay(Card card, int actualQuantity, int seenQuantity, int x, int y) {
		this.card = card;
		this.actualQuantity = actualQuantity;
		this.seenQuantity = seenQuantity;
		this.x = x;
		this.y = y;
	}
	
	public void drawCard(Graphics g){
		BufferedImage cardImg = card.getCardImg();
		g.drawImage(cardImg,x+CachedData.cardBorder.getWidth()-cardImg.getWidth(),y,null);
		g.drawImage(CachedData.cardBorder,x,y,null);
		if(actualQuantity == 2 || card.getRarity().equals(CardRarity.LEGENDARY)){
			g.drawImage(CachedData.cardCountbox,x+CachedData.cardBorder.getWidth()-5,y,null);
			if(actualQuantity == 2){
				g.drawImage(CachedData.count2,x+CachedData.cardBorder.getWidth()-5+(CachedData.cardCountbox.getWidth()-CachedData.count2.getWidth())/2
						,y+(CachedData.cardCountbox.getHeight()-CachedData.count2.getHeight())/2,null);
			}else{
				g.drawImage(CachedData.countLegendary,x+CachedData.cardBorder.getWidth()-5+(CachedData.cardCountbox.getWidth()-CachedData.countLegendary.getWidth())/2
						,y+(CachedData.cardCountbox.getHeight()-CachedData.countLegendary.getHeight())/2,null);			
			}
		}
		if(seenQuantity < actualQuantity){
			float percentage = .5f; 
			int brightness = (int)(256 - 256 * percentage);
			g.setColor(new Color(0,0,0,brightness));
			g.fillRect(x + CachedData.cardBorder.getWidth(), y, CachedData.cardCountbox.getWidth(), CachedData.cardCountbox.getHeight());
			if(seenQuantity == 0){
				g.fillRect(x, y, CachedData.cardBorder.getWidth(), CachedData.cardCountbox.getHeight());
			}
		}
		Graphics2D g2d = (Graphics2D) g;
		Font font = new Font("Serif", Font.BOLD, 18);
		g2d.setFont(font);
		g2d.setColor(Color.BLACK);
		g2d.drawString(card.getName(), x + 30, y + 20);
		
	}
	
}
