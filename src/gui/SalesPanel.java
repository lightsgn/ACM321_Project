package gui;

import javax.swing.*;
import repo.SaleRepo;
import structures.Sale;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalesPanel extends JPanel {

	private SaleRepo saleRepo; // SaleRepo nesnesi
	private static final long serialVersionUID = 1L;

	public SalesPanel(SaleRepo saleRepo) {
		setLayout(new BorderLayout());
		this.saleRepo = saleRepo;

		JTextField productIdField = new JTextField(10);
		JTextField quantityField = new JTextField(10);
		JTextField priceField = new JTextField(10);
		JTextField saleDateField = new JTextField(10);

		JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		formPanel.add(new JLabel("Product ID:"));
		formPanel.add(productIdField);
		formPanel.add(new JLabel("Quantity:"));
		formPanel.add(quantityField);
		formPanel.add(new JLabel("Price:"));
		formPanel.add(priceField);
		formPanel.add(new JLabel("Sale Date (YYYY-MM-DD):"));
		formPanel.add(saleDateField);

		JButton addSaleButton = new JButton("Add Sale");
		addSaleButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {

					int productId = Integer.parseInt(productIdField.getText().trim());
					int quantity = Integer.parseInt(quantityField.getText().trim());
					double price = Double.parseDouble(priceField.getText().trim());
					String saleDate = saleDateField.getText().trim();

					Sale sale = new Sale(0, productId, quantity, price, saleDate);

					saleRepo.addSale(sale);

					JOptionPane.showMessageDialog(SalesPanel.this, "Sale added successfully!");

					productIdField.setText("");
					quantityField.setText("");
					priceField.setText("");
					saleDateField.setText("");

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(SalesPanel.this,
							"Please enter valid numeric values for Product ID, Quantity, and Price.", "Input Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {

					JOptionPane.showMessageDialog(SalesPanel.this, "Error: " + ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		add(formPanel, BorderLayout.CENTER);
		add(addSaleButton, BorderLayout.SOUTH);
	}

}
