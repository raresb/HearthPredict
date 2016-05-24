import java.awt.image.BufferedImage;


public class Card {
	private CardRarity rarity;
	private CardSet set;
	private CardType type;
	private String name;
	private String cardId;
	private int cost;
	private int attack;
	private int health;
	private int durability;
	private BufferedImage cardImg;
	
	public Card(CardRarity rarity, CardSet set, CardType type, String name, String cardId, int cost,
			int attack, int health, int durability, BufferedImage cardImg) {
		this.rarity = rarity;
		this.set = set;
		this.type = type;
		this.name = name;
		this.cardId = cardId;
		this.cost = cost;
		this.attack = attack;
		this.health = health;
		this.durability = durability;
		this.cardImg = cardImg;
	}
	
	public int compareTo(Card c){
		if(this.cost > c.cost){
			return 1;
		}else if(this.cost == c.cost){
			if(this.name.compareTo(c.name) < 0){
				return -1;
			}else{
				return 1;
			}
		}else{
			return -1;
		}
	}

	public CardRarity getRarity() {
		return rarity;
	}

	public CardSet getSet() {
		return set;
	}

	public CardType getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
	public String getCardId() {
		return cardId;
	}

	public int getCost() {
		return cost;
	}

	public int getAttack() {
		return attack;
	}

	public int getHealth() {
		return health;
	}

	public int getDurability() {
		return durability;
	}
	
	public BufferedImage getCardImg() {
		return cardImg;
	}

	public void setCardImg(BufferedImage cardImg) {
		this.cardImg = cardImg;
	}

	public String toString(){
		return name;
	}
	
}
