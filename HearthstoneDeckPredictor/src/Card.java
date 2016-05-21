
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
	
	public Card(CardRarity rarity, CardSet set, CardType type, String name, String cardId, int cost,
			int attack, int health, int durability) {
		this.rarity = rarity;
		this.set = set;
		this.type = type;
		this.name = name;
		this.cardId = cardId;
		this.cost = cost;
		this.attack = attack;
		this.health = health;
		this.durability = durability;
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
	
	public String toString(){
		return name;
	}
	
}
