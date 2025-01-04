package repo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import structures.Sale;

public class SaleRepo {
	private static final String DB_URL = null;
	private Connection con;

	public SaleRepo(Connection con) {
		if (con == null) {
			throw new IllegalArgumentException("Database connection cannot be null");
		}
		this.con = con;
	}

	public List<Sale> listAll() {
		try {
			return fetchAll();
		} catch (Exception e) {
			throw new RuntimeException("Error fetching sales: " + e.getMessage(), e);
		}
		
	}

	private List<Sale> fetchAll() throws SQLException {
		String query = "SELECT sale_id, sale_date, customer_id, product_id, quantity, unit_price FROM sales";
	    List<Sale> sales = new ArrayList<>();

	    try (Connection conn = DriverManager.getConnection(DB_URL);
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            int saleId = rs.getInt("sale_id");
	            String saleDate = rs.getString("sale_date");
	            int customerId = rs.getInt("customer_id");
	            int productId = rs.getInt("product_id");
	            int quantity = rs.getInt("quantity");
	            double unitPrice = rs.getDouble("unit_price");

	            sales.add(new Sale(saleId, saleDate, customerId, productId, quantity, unitPrice));
	        }
		}
		return sales;
	}

	public void addSale(Sale sale) throws SQLException {
		String sql = "INSERT INTO sales (sale_date, customer_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, sale.getSaleDate());
			stmt.setInt(2, sale.getCustomerId());
			stmt.setInt(3, sale.getProductId());
			stmt.setInt(4, sale.getQuantity());
			stmt.setDouble(5, sale.getPrice());
			stmt.executeUpdate();
		}
	}

	public boolean updateSale(Sale sale) throws SQLException {
		String sql = "UPDATE sales SET product_id = ?, quantity = ?, unit_price = ?, sale_date = ?, customer_id = ? WHERE sale_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, sale.getProductId());
			stmt.setInt(2, sale.getQuantity());
			stmt.setDouble(3, sale.getPrice());
			stmt.setString(4, sale.getSaleDate());
			stmt.setInt(5, sale.getCustomerId());
			stmt.setInt(6, sale.getSaleId());
			stmt.executeUpdate();
		}
		return false;
	}

	public boolean deleteSale(int saleId) throws SQLException {
		String sql = "DELETE FROM sales WHERE sale_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, saleId);
			stmt.executeUpdate();
		}
		return false;
	}
}