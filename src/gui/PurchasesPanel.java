package gui;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

	@Override
	protected void initializeForm(JPanel panel) {
		JTextField idField = new JTextField();
		JTextField supplierIdField = new JTextField();
		JTextField boardgameIdField = new JTextField();
		JTextField dateField = new JTextField();
		JTextField quantityField = new JTextField();
		JTextField unitPriceField = new JTextField();
		JTextField vatField = new JTextField();

		
		panel.add(new JLabel("Id "));
		panel.add(idField);
		panel.add(new JLabel("Supplier Id "));
		panel.add(supplierIdField);
		panel.add(new JLabel("Boardgame Id "));
		panel.add(boardgameIdField);
		panel.add(new JLabel("Date "));
		panel.add(dateField);
		panel.add(new JLabel("Quantity "));
		panel.add(quantityField);
		panel.add(new JLabel("Unit Price "));
		panel.add(unitPriceField);
		panel.add(new JLabel("Vat "));
		panel.add(vatField);
		
		JButton saveButton = new JButton("Save");
	    panel.add(saveButton);
	    
	    
		
	}
}