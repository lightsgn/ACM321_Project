package gui;

import java.util.List;

import repo.SupplierRepo;
import structures.Supplier;

public class SuppliersPanel extends IMSPanel {
	private static final long serialVersionUID = 1L;
	private SupplierRepo repo;
	
	public SuppliersPanel(SupplierRepo repo) {
		this.repo = repo;
		addContent();
	}

	public String getTitle(){
		return "Suppliers";
	}
	
	public String[][] getRows(){
		List<Supplier> suppliers = repo.listAll();
		String[][] rows = new String[suppliers.size()][];
		for(int i = 0; i< suppliers.size(); i++)
			rows[i] = getRow(suppliers.get(i));
		return rows;
	}
	
	public String[] getColumnNames(){
		return new String[] { "Id", "Name", "Contact Number", "Address", "Is Active" };
	}
	
	public String[] getRow(Supplier s) {
		return new String[]{Integer.toString(s.id), s.name, s.contactNumber, s.address, Boolean.toString(s.isActive)};
	}
}
