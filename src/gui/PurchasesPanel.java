package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import repo.PurchaseRepo;
import structures.Purchase;

public class PurchasesPanel extends JPanel {
    private static final long serialVersionUID = 1L; // Seri versiyon UID

    private JTextField supplierIdField;
    private JTextField boardgameIdField;
    private JTextField dateField;
    private JTextField quantityField;
    private JTextField unitPriceField;
    private JTextField vatField;
    private JButton saveButton;
    private JTable purchasesTable;
    private PurchaseRepo purchaseRepo;

    public PurchasesPanel(PurchaseRepo purchaseRepo) {
        this.purchaseRepo = purchaseRepo;
        setLayout(new BorderLayout());

        // Form alanlarını oluşturma
        JPanel formPanel = new JPanel(new GridLayout(7, 2));
        formPanel.add(new JLabel("Supplier ID:"));
        supplierIdField = new JTextField();
        formPanel.add(supplierIdField);

        formPanel.add(new JLabel("Boardgame ID:"));
        boardgameIdField = new JTextField();
        formPanel.add(boardgameIdField);

        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        formPanel.add(dateField);

        formPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        formPanel.add(quantityField);

        formPanel.add(new JLabel("Unit Price:"));
        unitPriceField = new JTextField();
        formPanel.add(unitPriceField);

        formPanel.add(new JLabel("VAT:"));
        vatField = new JTextField();
        formPanel.add(vatField);

        // Save butonunu ekleme
        saveButton = new JButton("Save");
        formPanel.add(saveButton);
        formPanel.add(new JLabel()); // Boş alan doldurucu

        add(formPanel, BorderLayout.NORTH);

        // Tabloyu ekleme
        purchasesTable = new JTable();
        refreshTable();
        add(new JScrollPane(purchasesTable), BorderLayout.CENTER);

        // Save butonuna listener ekleme
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSaveAction();
            }
        });
    }

    private void handleSaveAction() {
        try {
            // Form alanlarını doğrula
            if (supplierIdField.getText().trim().isEmpty() ||
                boardgameIdField.getText().trim().isEmpty() ||
                dateField.getText().trim().isEmpty() ||
                quantityField.getText().trim().isEmpty() ||
                unitPriceField.getText().trim().isEmpty() ||
                vatField.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "All fields must be filled!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Kullanıcı girdilerini al ve Purchase objesi oluştur
            Purchase newPurchase = new Purchase(
                0, // ID, veritabanı tarafından otomatik atanır
                supplierIdField.getText(),
                boardgameIdField.getText(),
                dateField.getText(),
                Integer.parseInt(quantityField.getText()),
                Double.parseDouble(unitPriceField.getText()),
                Integer.parseInt(vatField.getText())
            );

            // Veritabanına ekle
            purchaseRepo.addPurchase(newPurchase);

            // Kullanıcıya başarı mesajı göster
            JOptionPane.showMessageDialog(this, "Purchase saved successfully!");

            // Tabloyu güncelle
            refreshTable();

            // Form alanlarını temizle
            clearFormFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void clearFormFields() {
        supplierIdField.setText("");
        boardgameIdField.setText("");
        dateField.setText("");
        quantityField.setText("");
        unitPriceField.setText("");
        vatField.setText("");
    }

    private void refreshTable() {
        try {
            // Veritabanından tüm satın alımları getir
            List<Purchase> purchases = purchaseRepo.listAll();

            // Tablo modelini oluştur
            String[] columnNames = { "ID", "Supplier ID", "Boardgame ID", "Date", "Quantity", "Unit Price", "VAT" };
            String[][] data = new String[purchases.size()][7];

            for (int i = 0; i < purchases.size(); i++) {
                Purchase p = purchases.get(i);
                data[i][0] = String.valueOf(p.id);
                data[i][1] = p.supplierId;
                data[i][2] = p.boardgameId;
                data[i][3] = p.date;
                data[i][4] = String.valueOf(p.quantity);
                data[i][5] = String.valueOf(p.unitPrice);
                data[i][6] = String.valueOf(p.vat);
            }

            purchasesTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading purchases: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}

