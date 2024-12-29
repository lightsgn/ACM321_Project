package structures;

public class Purchase {
	public int id;
	public String supplierId;
	public String boardgameId;
	public String date;
	public int quantity;
	public double unitPrice;
	public int vat;
	
	
	public Purchase(int id, String supplierId, String boardgameId, String date, int quantity,double unitPrice,int vat) {
		this.id = id;
		this.supplierId=supplierId;
		this.boardgameId=boardgameId;
		this.date=date;
		this.quantity=quantity;
		this.unitPrice=unitPrice;
		this.vat=vat;
	}
}
