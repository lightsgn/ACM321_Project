package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import repo.SupplierRepo;
import structures.Supplier;

public class SuppliersPanel extends IMSPanel {
	private static final long serialVersionUID = 1L;
	private SupplierRepo repo;
	private JTextField idField;
	private JTextField nameField;
	private JTextField contactNoField;
	private JTextField addressField;
	private JTextField resultField;
	private JCheckBox isActiveField ;

	
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
	
	public void initializeForm(JPanel formPanel) {
		formPanel.setLayout(null);
		
		JLabel lblId = new JLabel("Supplier Id");
		lblId.setBounds(52, 40, 102, 15);
		formPanel.add(lblId);
		
		idField = new JTextField();
		idField.setBounds(184, 36, 185, 19);
		formPanel.add(idField);
		idField.setColumns(10);
		
		JLabel lblId_1 = new JLabel("Name");
		lblId_1.setBounds(52, 69, 102, 15);
		formPanel.add(lblId_1);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(184, 65, 185, 19);
		formPanel.add(nameField);
		
		JLabel lblId_2 = new JLabel("Contact No");
		lblId_2.setBounds(52, 98, 102, 15);
		formPanel.add(lblId_2);
		
		contactNoField = new JTextField();
		contactNoField.setColumns(10);
		contactNoField.setBounds(184, 94, 185, 19);
		formPanel.add(contactNoField);
		
		JLabel lblId_3 = new JLabel("Address");
		lblId_3.setBounds(52, 127, 102, 15);
		formPanel.add(lblId_3);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(184, 123, 185, 19);
		formPanel.add(addressField);
		
		
		isActiveField = new JCheckBox("Is Active");
		isActiveField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		isActiveField.setBounds(184, 150, 129, 23);
		formPanel.add(isActiveField);
		
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idField.getText() == null || idField.getText().isBlank()) {
					showMessage("id field can not be empty");
					return;
				}
				Supplier supplier = readForm();
				String message = repo.update(supplier);
				JOptionPane.showMessageDialog(SuppliersPanel.this, message);
				resultField.setText(message);
			}
		});
		updateBtn.setBounds(260, 207, 97, 25);
		formPanel.add(updateBtn);
		
		JButton readBtn = new JButton("Read");
		readBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idField.getText() == null || idField.getText().isBlank()) {
					showMessage("id field can not be empty");
					return;
				}
				Supplier supplier = readForm();
				Supplier supplier2 = repo.read(supplier.id);
				if (supplier2 == null) {
					String message = "Record not found: " + supplier.id;
					resultField.setText(message);
					JOptionPane.showMessageDialog(SuppliersPanel.this, message  );
					return;
				}
				setForm(supplier2);
				
			}
		});
		readBtn.setBounds(151, 207, 97, 25);
		formPanel.add(readBtn);
		
		JButton createBtn = new JButton("Create");
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idField.getText() == null || idField.getText().isBlank()) {
					showMessage("id field can not be empty");
					return;
				}
				Supplier supplier = readForm();
				String message = repo.create(supplier);
				showMessage(message);
				
			}
		});
		createBtn.setBounds(37, 207, 97, 25);
		formPanel.add(createBtn);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idField.getText() == null || idField.getText().isBlank()) {
					showMessage("id field can not be empty");
					return;
				}
				Supplier supplier = readForm();
				String message = repo.delete(supplier.id);
				JOptionPane.showMessageDialog(SuppliersPanel.this, message);
				resultField.setText(message);
				clearForm();
			
			}
		});
		deleteBtn.setBounds(369, 207, 97, 25);
		formPanel.add(deleteBtn);
		
		JButton clearBtn = new JButton("Clear Form");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
			}
		});
		clearBtn.setBounds(197, 244, 117, 25);
		formPanel.add(clearBtn);
		
		JLabel lblNewLabel = new JLabel("Result");
		lblNewLabel.setBounds(37, 309, 70, 15);
		formPanel.add(lblNewLabel);
		
		resultField = new JTextField();
		resultField.setEditable(false);
		resultField.setEnabled(true);
		resultField.setBounds(97, 307, 369, 19);
		formPanel.add(resultField);
		resultField.setColumns(10);

	}

	protected void showMessage(String message) {
		JOptionPane.showMessageDialog(SuppliersPanel.this, message);
		resultField.setText(message);		
	}

	protected void clearForm() {
		idField.setText(null);
		nameField.setText(null);
		contactNoField.setText(null);
		addressField.setText(null);
		isActiveField.setSelected(false);
		resultField.setText(null);		
	}

	protected void setForm(Supplier supplier) {
		idField.setText(String.valueOf(supplier.id));
		nameField.setText(supplier.name);
		contactNoField.setText(supplier.contactNumber);
		addressField.setText(supplier.address);
		isActiveField.setSelected(supplier.isActive);
	}

	protected Supplier readForm() {
		int id = Integer.parseInt(idField.getText().trim());
		String name = nameField.getText().trim();
		String contactNo = contactNoField.getText().trim();
		String address = addressField.getText().trim();
		boolean isActive = isActiveField.isSelected();
		Supplier supplier = new Supplier(id, name, contactNo, address, isActive);
		return supplier;
	}
	
}
