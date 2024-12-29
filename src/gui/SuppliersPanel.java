package gui;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	
	public void initializeForm(JPanel boxPanel) {
		JTextField idField = new JTextField();
		JTextField nameField = new JTextField();
		JTextField contactNumberField = new JTextField();
		JTextField addressField = new JTextField();
		JTextField isActiveField = new JTextField();
		
		boxPanel.add(new JLabel("Id"));
		boxPanel.add(idField);
		boxPanel.add(new JLabel("Name"));
		boxPanel.add(nameField);
		boxPanel.add(new JLabel("Contact Number"));
		boxPanel.add(contactNumberField);
		boxPanel.add(new JLabel("Address"));
		boxPanel.add(addressField);
		boxPanel.add(new JLabel("Is It Active"));
		boxPanel.add(isActiveField);
		boxPanel.add(new JButton("save"));
	
		
		
	}
	
}
