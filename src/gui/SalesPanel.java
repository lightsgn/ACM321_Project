package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import repo.SaleRepo;
import structures.Sale;

public class SalesPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public SalesPanel(SaleRepo saleRepo) {
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("🛍️ Sales Management 🛒", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(255, 69, 0)); // Turuncu (Dikkat çekici renk)

        JPanel formPanel = createFormPanel(saleRepo);

        add(titleLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }

    private JPanel createFormPanel(SaleRepo saleRepo) {
        JPanel formPanel = new JPanel(new BorderLayout(20, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(new Color(255, 223, 186)); // Yumuşak turuncu/peach rengi (Dikkat çekici)

        // Sol Panel (Alanlar)
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(new Color(255, 223, 186)); // Yumuşak turuncu
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Alanlar
        JTextField customerIdField = new JTextField();
        JTextField productIdField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField saleDateField = new JTextField();
        JTextField priceField = new JTextField();

        JButton addSaleButton = new JButton("➕ Add Sale");
        JButton updateSaleButton = new JButton("🔄 Update Sale");
        JButton deleteSaleButton = new JButton("❌ Delete Sale");

        // Düğme Stil Ayarları
        addSaleButton.setBackground(new Color(102, 204, 255)); // Açık mavi
        addSaleButton.setForeground(Color.BLACK);
        addSaleButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        updateSaleButton.setBackground(new Color(34, 139, 34)); // Yeşil
        updateSaleButton.setForeground(Color.BLACK);
        updateSaleButton.setFont(new Font("Segoe UI", Font.BOLD, 12));

        deleteSaleButton.setBackground(new Color(255, 69, 0)); // Kırmızımsı turuncu
        deleteSaleButton.setForeground(Color.BLACK);
        deleteSaleButton.setFont(new Font("Segoe UI", Font.BOLD, 12));

        // Sol Panel Alanlarının Eklenmesi
        gbc.gridx = 0; gbc.gridy = 0;
        leftPanel.add(new JLabel("Customer ID: 🧑‍🤝‍🧑"), gbc);
        gbc.gridx = 1;
        leftPanel.add(customerIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        leftPanel.add(new JLabel("Product ID: 🏷️"), gbc);
        gbc.gridx = 1;
        leftPanel.add(productIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        leftPanel.add(new JLabel("Quantity: 🔢"), gbc);
        gbc.gridx = 1;
        leftPanel.add(quantityField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        leftPanel.add(new JLabel("Sale Date (YYYY-MM-DD): 📅"), gbc);
        gbc.gridx = 1;
        leftPanel.add(saleDateField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        leftPanel.add(new JLabel("Price: 💵"), gbc);
        gbc.gridx = 1;
        leftPanel.add(priceField, gbc);

        gbc.gridx = 1; gbc.gridy = 5;
        leftPanel.add(addSaleButton, gbc);

        // Sağ Panel (Update/Delete)
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(255, 223, 186)); // Yumuşak turuncu
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(10, 10, 10, 10);
        gbcRight.anchor = GridBagConstraints.CENTER;

        JLabel saleIdLabel = new JLabel("Sale ID (For Update/Delete): 🆔");
        saleIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Modern yazı tipi

        JTextField saleIdField = new JTextField();
        saleIdField.setPreferredSize(new Dimension(180, 30)); // Daha büyük boyut

        gbcRight.gridx = 0; gbcRight.gridy = 0;
        rightPanel.add(saleIdLabel, gbcRight);
        gbcRight.gridx = 1;
        rightPanel.add(saleIdField, gbcRight);

        gbcRight.gridx = 0; gbcRight.gridy = 1;
        gbcRight.gridwidth = 2;
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonsPanel.setBackground(new Color(255, 223, 186)); // Yumuşak turuncu
        buttonsPanel.add(updateSaleButton);
        buttonsPanel.add(deleteSaleButton);
        rightPanel.add(buttonsPanel, gbcRight);

        // Sol ve Sağ Panellerin Form Paneline Eklenmesi
        formPanel.add(leftPanel, BorderLayout.CENTER);
        formPanel.add(rightPanel, BorderLayout.EAST);

        // ActionListener eklemeleri
        addSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int productId = Integer.parseInt(productIdField.getText().trim());
                    int quantity = Integer.parseInt(quantityField.getText().trim());
                    double unitPrice = Double.parseDouble(priceField.getText().trim());
                    String saleDate = saleDateField.getText().trim();
                    int customerId = 0 ;
					
                    Sale sale = new Sale(0,  saleDate,  customerId, productId,  quantity,  unitPrice); // Sale nesnesi oluştur

                    // Sale ekleme
                    saleRepo.addSale(sale);

                    JOptionPane.showMessageDialog(SalesPanel.this, "Sale added successfully!");

                    // Alanları temizle
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

        // Update Sale Button ActionListener
        updateSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int saleId = Integer.parseInt(saleIdField.getText().trim());
                    int productId = Integer.parseInt(productIdField.getText().trim());
                    int quantity = Integer.parseInt(quantityField.getText().trim());
                    double unitPrice = Double.parseDouble(priceField.getText().trim());
                    String saleDate = saleDateField.getText().trim();
                    int customerId = 0 ;
                    Sale sale = new Sale(0,  saleDate,  customerId, productId,  quantity,  unitPrice); 

                    boolean success = saleRepo.updateSale(sale);
                    if (success) {
                        JOptionPane.showMessageDialog(SalesPanel.this, "Your product has been updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(SalesPanel.this, "Sale ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(SalesPanel.this, "Error: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Delete Sale Button ActionListener
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
                    JOptionPane.showMessageDialog(SalesPanel.this, "Error: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return formPanel;
    }
}
