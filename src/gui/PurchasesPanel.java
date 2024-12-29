package gui;

import java.util.List;

import repo.PurchaseRepo;
import structures.Purchase;

public class PurchasesPanel extends IMSPanel {
	private static final long serialVersionUID = 1L;
	private PurchaseRepo repo;
	
	public PurchasesPanel(PurchaseRepo repo) {
		this.repo = repo;
		addContent();
	}

	public String getTitle(){
		return "Purchases";
	}
	
	public String[][] getRows(){
		List<Purchase> purchases = repo.listAll();
		String[][] rows = new String[purchases.size()][];
		for(int i = 0; i< purchases.size(); i++)
			rows[i] = getRow(purchases.get(i));
		return rows;
	}
	
	public String[] getColumnNames(){
		return new String[] {"id", "supplierId", "boardgameId","date", "quantity","unitPrice","vat" };
	}
	
	public String[] getRow(Purchase s) {
		return new String[]{Integer.toString(s.id)
				,s.supplierId
				,s.boardgameId
				,s.date
				,Integer.toString(s.quantity)
				,Double.toString(s.unitPrice)
				,Integer.toString(s.vat)
				
				};
	}
}