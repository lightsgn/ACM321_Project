package structures;

public class BoardGame {
	public int id;
	public String name;
	public String maker;
	public String type;
	public String mechanic;
	public int playerCount;
	public int ageLimit;
	public boolean diceUsage;
	public boolean cardUsage;
	public int averagePlayTime;
	public int price;
	public int quantityAvailable;
	public int quantitySold;
	
	
	
	public BoardGame(int id, String name, String maker, String type,String mechanic, int playerCount, 
			int ageLimit, boolean diceUsage, boolean cardUsage, int averagePlayTime, int price, int quantityAvailable, int quantitySold) {
		this.id = id;
		this.name = name;
		this.maker = maker;
		this.type = type;
		this.mechanic = mechanic;
		this.playerCount = playerCount;
		this.ageLimit = ageLimit;
		this.diceUsage =diceUsage;
		this.cardUsage = cardUsage;
		this.averagePlayTime = averagePlayTime;
		this.price = price;
		this.quantityAvailable = quantityAvailable;
		this.quantitySold = quantitySold;
		
		
	}

}
