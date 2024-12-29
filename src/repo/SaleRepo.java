package repo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import structures.Sale;



public class SaleRepo {
	private Connection con;

	public SaleRepo(Connection con) {
		this.con = con;
	}

	public List<SaleRepo> listAll() {
		try {
			return fetchAll();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List<SaleRepo> fetchAll() {
		return null;
	}

	public SaleRepo(int int1, int int2, int int3, double double1, String string) {

	}

	public void addSale(Sale sale) throws SQLException {
		String sql = "INSERT INTO sales (product_id, quantity, price, sale_date) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, sale.getProductId());
			stmt.setInt(2, sale.getQuantity());
			stmt.setDouble(3, sale.getPrice());
			stmt.setString(4, sale.getSaleDate());
			stmt.executeUpdate();
		}
	}

	private String getSaleDate() {
		return null;
	}

	private double getPrice() {
		return 0;
	}

	private int getQuantity() {
		return 0;
	}

	private int getProductId() {
		return 0;
	}

	public List<SaleRepo> getAllSales() throws SQLException {
		String sql = "SELECT * FROM sales";
		List<SaleRepo> sales = new ArrayList<>();
		try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				SaleRepo sale = new SaleRepo(rs.getInt("sale_id"), rs.getInt("product_id"), rs.getInt("quantity"),
						rs.getDouble("price"), rs.getString("sale_date"));
				sales.add(sale);
			}
		}
		return sales;
	}

	public void updateSale(SaleRepo sale) throws SQLException {
		String sql = "UPDATE sales SET product_id = ?, quantity = ?, price = ?, sale_date = ? WHERE sale_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, sale.getProductId());
			stmt.setInt(2, sale.getQuantity());
			stmt.setDouble(3, sale.getPrice());
			stmt.setString(4, sale.getSaleDate());
			stmt.setInt(5, sale.getSaleId());
			stmt.executeUpdate();
		}
	}

	private int getSaleId() {
		return 0;
	}

	public void deleteSale(int saleId) throws SQLException {
		String sql = "DELETE FROM sales WHERE sale_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, saleId);
			stmt.executeUpdate();
		}
	}

	

}
