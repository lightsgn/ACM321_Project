package gui;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import repo.BoardGameRepo;
import structures.BoardGame;

public class BoardGamePanel extends IMSPanel {
    private static final long serialVersionUID = 1L;
    private BoardGameRepo repo;

    public BoardGamePanel(BoardGameRepo repo) {
        this.repo = repo;
        addContent();
    }

    public String getTitle() {
        return "Board Games";
    }

    @Override
    public String[][] getRows() {
        List<BoardGame> boardGames = repo.listAll();
        String[][] rows = new String[boardGames.size()][];
        for (int i = 0; i < boardGames.size(); i++)
            rows[i] = getRow(boardGames.get(i));
        return rows;
    }

    @Override
    public String[] getColumnNames() {
        return new String[] {
            "Id", "Name", "Maker", "Type", "Mechanic", "Player Count", "Age Limit", "Dice Usage", "Card Usage", "Average Play Time", "Price", "Quantity Available", "Quantity Sold"
        };
    }

    public String[] getRow(BoardGame bg) {
        return new String[] {
            Integer.toString(bg.id),
            bg.name,
            bg.maker,
            bg.type,
            bg.mechanic,
            bg.playerCount,
            bg.ageLimit,
            Boolean.toString(bg.diceUsage),
            Boolean.toString(bg.cardUsage),
            bg.averagePlayTime,
            Integer.toString(bg.price),
            Integer.toString(bg.quantityAvailable),
            Integer.toString(bg.quantitySold)};
    }

    @Override
    protected void initializeForm(JPanel boxPanel) {
        boxPanel.setLayout(new java.awt.GridLayout(16, 2, 10, 10)); // 2-column layout
        boxPanel.setBackground(java.awt.Color.decode("#f0f8ff")); // Pastel blue background
        boxPanel.setPreferredSize(new Dimension(200, 400)); // Size

        // Text and color settings
        java.awt.Color labelColor = java.awt.Color.decode("#4682b4"); // Darker pastel blue
        java.awt.Font font = new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12);

        // Fields
        JTextField idField = createStyledTextField();
        JTextField nameField = createStyledTextField();
        JTextField makerField = createStyledTextField();
        JTextField typeField = createStyledTextField();
        JTextField mechanicField = createStyledTextField();
        JTextField playerCountField = createStyledTextField();
        JTextField ageLimitField = createStyledTextField();
        JTextField diceUsageField = createStyledTextField();
        JTextField cardUsageField = createStyledTextField();
        JTextField averagePlayTimeField = createStyledTextField();
        JTextField priceField = createStyledTextField();
        JTextField quantityAvailableField = createStyledTextField();
        JTextField quantitySoldField = createStyledTextField();

        // Adding each label and field
        addStyledFormRow(boxPanel, "Id", idField, labelColor, font);
        addStyledFormRow(boxPanel, "Name", nameField, labelColor, font);
        addStyledFormRow(boxPanel, "Maker", makerField, labelColor, font);
        addStyledFormRow(boxPanel, "Type", typeField, labelColor, font);
        addStyledFormRow(boxPanel, "Mechanic", mechanicField, labelColor, font);
        addStyledFormRow(boxPanel, "Player Count", playerCountField, labelColor, font);
        addStyledFormRow(boxPanel, "Age Limit", ageLimitField, labelColor, font);
        addStyledFormRow(boxPanel, "Dice Usage", diceUsageField, labelColor, font);
        addStyledFormRow(boxPanel, "Card Usage", cardUsageField, labelColor, font);
        addStyledFormRow(boxPanel, "Average Play Time", averagePlayTimeField, labelColor, font);
        addStyledFormRow(boxPanel, "Price", priceField, labelColor, font);
        addStyledFormRow(boxPanel, "Quantity Available", quantityAvailableField, labelColor, font);
        addStyledFormRow(boxPanel, "Quantity Sold", quantitySoldField, labelColor, font);

        // Button panel
        JPanel buttonPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(java.awt.Color.decode("#f0f8ff"));

        JButton saveButton = createStyledButton("Save", labelColor, font);
        JButton updateButton = createStyledButton("Update", labelColor, font);
        JButton deleteButton = createStyledButton("Delete", labelColor, font);
        JButton exportButton = createStyledButton("Export to CSV", labelColor, font);
        JButton importButton = createStyledButton("Import from CSV", labelColor, font);

        buttonPanel.add(saveButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(importButton);

        boxPanel.add(new JLabel()); // Empty space
        boxPanel.add(buttonPanel);

        // Save action
        saveButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                String maker = makerField.getText().trim();
                String type = typeField.getText().trim();
                String mechanic = mechanicField.getText().trim();
                String playerCount = playerCountField.getText().trim();
                String ageLimit = ageLimitField.getText().trim();
                boolean diceUsage = Boolean.parseBoolean(diceUsageField.getText().trim());
                boolean cardUsage = Boolean.parseBoolean(cardUsageField.getText().trim());
                String averagePlayTime = averagePlayTimeField.getText().trim();
                int price = Integer.parseInt(priceField.getText().trim());
                int quantityAvailable = Integer.parseInt(quantityAvailableField.getText().trim());
                int quantitySold = Integer.parseInt(quantitySoldField.getText().trim());

                BoardGame boardGame = new BoardGame(
                    id, name, maker, type, mechanic, playerCount, ageLimit,
                    diceUsage, cardUsage, averagePlayTime, price, quantityAvailable, quantitySold
                );

                repo.addBoardGame(boardGame);

                JOptionPane.showMessageDialog(null, "Board Game saved successfully!");

                idField.setText("");
                nameField.setText("");
                makerField.setText("");
                typeField.setText("");
                mechanicField.setText("");
                playerCountField.setText("");
                ageLimitField.setText("");
                diceUsageField.setText("");
                cardUsageField.setText("");
                averagePlayTimeField.setText("");
                priceField.setText("");
                quantityAvailableField.setText("");
                quantitySoldField.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Delete action
        deleteButton.addActionListener(e -> {
            try {
                if (idField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Id field cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int id = Integer.parseInt(idField.getText().trim());

                boolean exists = repo.listAll().stream().anyMatch(game -> game.id == id);

                if (!exists) {
                    JOptionPane.showMessageDialog(null, "No record found with this ID!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                repo.deleteBoardGame(id);

                JOptionPane.showMessageDialog(null, "Board Game deleted successfully!");

                idField.setText("");
                nameField.setText("");
                makerField.setText("");
                typeField.setText("");
                mechanicField.setText("");
                playerCountField.setText("");
                ageLimitField.setText("");
                diceUsageField.setText("");
                cardUsageField.setText("");
                averagePlayTimeField.setText("");
                priceField.setText("");
                quantityAvailableField.setText("");
                quantitySoldField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid ID!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        exportButton.addActionListener(e -> {
            try {
                exportToCSV("boardgames.csv");
                JOptionPane.showMessageDialog(null, "Data exported to boardgames.csv successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error exporting data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        importButton.addActionListener(e -> {
            try {
                importFromCSV("boardgames.csv");
                JOptionPane.showMessageDialog(null, "Data imported from boardgames.csv successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error importing data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void exportToCSV(String filePath) throws Exception {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Id,Name,Maker,Type,Mechanic,PlayerCount,AgeLimit,DiceUsage,CardUsage,AveragePlayTime,Price,QuantityAvailable,QuantitySold\n");
            List<BoardGame> boardGames = repo.listAll();
            for (BoardGame game : boardGames) {
                writer.write(game.id + "," + game.name + "," + game.maker + "," + game.type + "," + game.mechanic + ","
                        + game.playerCount + "," + game.ageLimit + "," + game.diceUsage + "," + game.cardUsage + ","
                        + game.averagePlayTime + "," + game.price + "," + game.quantityAvailable + ","
                        + game.quantitySold + "\n");
            }
        }
    }

    private void importFromCSV(String filePath) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Skip first line
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                BoardGame game = new BoardGame(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], data[5],
                        data[6], Boolean.parseBoolean(data[7]), Boolean.parseBoolean(data[8]), data[9],
                        Integer.parseInt(data[10]), Integer.parseInt(data[11]), Integer.parseInt(data[12]));
                repo.addBoardGame(game);
            }
        }
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
        textField.setBackground(java.awt.Color.WHITE);
        textField.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY)); // Light border
        return textField;
    }

    private JButton createStyledButton(String text, java.awt.Color color, java.awt.Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(color);
        button.setForeground(java.awt.Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private void addStyledFormRow(JPanel panel, String labelText, JTextField textField, java.awt.Color labelColor, java.awt.Font labelFont) {
        JLabel label = new JLabel(labelText);
        label.setForeground(labelColor);
        label.setFont(labelFont);
        panel.add(label);
        panel.add(textField);
    }
}
