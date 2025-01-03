package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import structures.Purchase;

public class PurchaseRepo {

    private Connection con;

    // Constructor: Bağlantıyı dışarıdan alıyor
    public PurchaseRepo(Connection con) {
        this.con = con;
    }

    // Yeni bir Purchase kaydı eklemek için metot
    public void addPurchase(Purchase purchase) throws Exception {
    	
    	if (con == null || con.isClosed()) {
    	    throw new Exception("Database connection is not active!");
    	}
    	
        String query = "INSERT INTO purchases (supplier_id, boardgame_id, date, quantity, unit_price, vat) "
                     + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, purchase.supplierId);
            pstmt.setString(2, purchase.boardgameId);
            pstmt.setString(3, purchase.date);
            pstmt.setInt(4, purchase.quantity);
            pstmt.setDouble(5, purchase.unitPrice);
            pstmt.setInt(6, purchase.vat);
            pstmt.executeUpdate();
        }
    }

    // Mevcut bir Purchase kaydını güncellemek için metot
    public void updatePurchase(Purchase purchase) throws Exception {
    	
    	if (con == null || con.isClosed()) {
    	    throw new Exception("Database connection is not active!");
    	}
    	
    	
        String query = "UPDATE purchases SET supplier_id = ?, boardgame_id = ?, date = ?, quantity = ?, "
                     + "unit_price = ?, vat = ? WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, purchase.supplierId);
            pstmt.setString(2, purchase.boardgameId);
            pstmt.setString(3, purchase.date);
            pstmt.setInt(4, purchase.quantity);
            pstmt.setDouble(5, purchase.unitPrice);
            pstmt.setInt(6, purchase.vat);
            pstmt.setInt(7, purchase.id);
            pstmt.executeUpdate();
        }
    }

    // Tüm Purchase kayıtlarını listelemek için metot
    public List<Purchase> listAll() {
        try {
            return fetchAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Purchase tablolarını veri tabanından çekmek için yardımcı metot
    private List<Purchase> fetchAll() throws Exception {
    	
    	if (con == null || con.isClosed()) {
    	    throw new Exception("Database connection is not active!");
    	}
    	
    	
        String query = "SELECT * FROM purchases";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            List<Purchase> purchases = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String supplierId = rs.getString("supplier_id");
                String boardgameId = rs.getString("boardgame_id");
                String date = rs.getString("date");
                int quantity = rs.getInt("quantity");
                double unitPrice = rs.getDouble("unit_price");
                int vat = rs.getInt("vat");

                Purchase purchase = new Purchase(id, supplierId, boardgameId, date, quantity, unitPrice, vat);
                purchases.add(purchase);
            }

            return purchases;
        }
    }
}
