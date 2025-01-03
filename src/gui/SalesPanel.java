package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import repo.SaleRepo;
import structures.Sale;
import java.util.List;

public class SalesPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable salesTable;
    private DefaultTableModel tableModel;
    private SaleRepo saleRepo;

    public SalesPanel(SaleRepo saleRepo) {
        this.saleRepo = saleRepo;

        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("🛍️ Sales Management 🛒", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(255, 69, 0));

        JPanel formPanel = createFormPanel();
        JScrollPane tableScrollPane = createTablePanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, formPanel, tableScrollPane);
        splitPane.setDividerLocation(300);

        add(titleLabel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);

        loadSalesData();
    }

    private JScrollPane createTablePanel() {
        String[] columnNames = {"Sale ID", "Customer ID", "Product ID", "Quantity", "Date", "Unit Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        salesTable = new JTable(tableModel);
        salesTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(salesTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Sales Records"));

        return scrollPane;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new BorderLayout(20, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(new Color(255, 223, 186));

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(new Color(255, 223, 186));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField customerIdField = new JTextField(15);
        JTextField productIdField = new JTextField(15);
        JTextField quantityField = new JTextField(15);
        JTextField saleDateField = new JTextField(15);
        JTextField priceField = new JTextField(15);
        JTextField saleIdField = new JTextField(15);

        JButton addSaleButton = new JButton("➕ Add Sale");
        JButton updateSaleButton = new JButton("🔄 Update Sale");
        JButton deleteSaleButton = new JButton("❌ Delete Sale");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(addSaleButton);
        buttonPanel.add(updateSaleButton);
        buttonPanel.add(deleteSaleButton);
        buttonPanel.setBackground(new Color(255, 223, 186));

        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(new JLabel("Sale ID (For Update/Delete): 🆔"), gbc);
        gbc.gridx = 1;
        leftPanel.add(saleIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        leftPanel.add(new JLabel("Customer ID: 🧑‍🤝‍🧑"), gbc);
        gbc.gridx = 1;
        leftPanel.add(customerIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(new JLabel("Product ID: 🏷️"), gbc);
        gbc.gridx = 1;
        leftPanel.add(productIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        leftPanel.add(new JLabel("Quantity: 🔢"), gbc);
        gbc.gridx = 1;
        leftPanel.add(quantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        leftPanel.add(new JLabel("Date (YYYY-MM-DD): 📅"), gbc);
        gbc.gridx = 1;
        leftPanel.add(saleDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        leftPanel.add(new JLabel("Price: 💵"), gbc);
        gbc.gridx = 1;
        leftPanel.add(priceField, gbc);

        formPanel.add(leftPanel, BorderLayout.CENTER);
        formPanel.add(buttonPanel, BorderLayout.SOUTH);

        addSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int productId = Integer.parseInt(productIdField.getText().trim());
                    int quantity = Integer.parseInt(quantityField.getText().trim());
                    double unitPrice = Double.parseDouble(priceField.getText().trim());
                    String saleDate = saleDateField.getText().trim();
                    int customerId = Integer.parseInt(customerIdField.getText().trim());

                    Sale sale = new Sale(0, saleDate, customerId, productId, quantity, unitPrice);

                    saleRepo.addSale(sale);
                    JOptionPane.showMessageDialog(SalesPanel.this, "Sale added successfully!");
                    loadSalesData();
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

        updateSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int saleId = Integer.parseInt(saleIdField.getText().trim());
                    int productId = Integer.parseInt(productIdField.getText().trim());
                    int quantity = Integer.parseInt(quantityField.getText().trim());
                    double unitPrice = Double.parseDouble(priceField.getText().trim());
                    String saleDate = saleDateField.getText().trim();
                    int customerId = 0;

                    Sale sale = new Sale(saleId, saleDate, customerId, productId, quantity, unitPrice);
                    boolean success = saleRepo.updateSale(sale);
                    if (success) {
                        JOptionPane.showMessageDialog(SalesPanel.this, "Your product has been updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(SalesPanel.this, "Sale ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(SalesPanel.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int saleId = Integer.parseInt(saleIdField.getText().trim());
                    boolean success = saleRepo.deleteSale(saleId);
                    if (success) {
                        JOptionPane.showMessageDialog(SalesPanel.this, "Your product has been deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(SalesPanel.this, "Sale ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(SalesPanel.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return formPanel;
    }

    private void loadSalesData() {
        tableModel.setRowCount(0); // Clear existing data
        try {
            List<Sale> sales = saleRepo.listAll();
            for (Sale sale : sales) {
                tableModel.addRow(new Object[]{
                        sale.id,
                        sale.customerId,
                        sale.productId,
                        sale.quantity,
                        sale.date,
                        sale.unitPrice
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading sales data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
