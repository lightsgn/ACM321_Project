package repo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import structures.Supplier;

public class SupplierRepo {
	
	Connection con;
	
	public SupplierRepo(Connection con) {
		this.con = con;
	}

	public List<Supplier> listAll() {
		try {
			return listAllX();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private List<Supplier> listAllX() throws Exception{
		Statement st = con.createStatement();
		String query="select * from suppliers";
		ResultSet rs = st.executeQuery(query);
		
		List<Supplier> suppliers = new ArrayList<Supplier>();
	    while(rs.next()) {
          // read the result set
	    	int id =rs.getInt("id");
	    	String name =rs.getString("name");
	    	String contactNumber =rs.getString("contact_number");
	    	String address =rs.getString("address");
	    	boolean isActive =rs.getBoolean("is_active");
	    	Supplier supplier = new Supplier(id, name, contactNumber, address, isActive);
	    	suppliers.add(supplier);
	    } 
	    rs.close();
	    st.close();
	    return suppliers;
	}
	
	public Supplier read(int id) {
		String query="select * from suppliers WHERE id = ?";
		Supplier supplier = null;
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
		    if (rs.next()) {
		    	String name =rs.getString("name");
		    	String contactNumber =rs.getString("contact_number");
		    	String address =rs.getString("address");
		    	boolean isActive =rs.getBoolean("is_active");
		    	supplier = new Supplier(id, name, contactNumber, address, isActive);
		    } 
		    rs.close();
		    st.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	    return supplier;
	}
	

	public String create(Supplier supplier) {
		String sql = "INSERT INTO suppliers (id, name, contact_number, address, is_active) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, supplier.id);
			stmt.setString(2, supplier.name);
			stmt.setString(3, supplier.contactNumber);
			stmt.setString(4, supplier.address);
			stmt.setInt(5, supplier.isActive ? 1 : 0);
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			return e.getMessage();
		}
		return "Supplier created!";
	}
	
	public String update(Supplier supplier) {
		String sql = "UPDATE suppliers SET name = ?, contact_number = ?, address = ?, is_active = ? WHERE id = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, supplier.name);
			stmt.setString(2, supplier.contactNumber);
			stmt.setString(3, supplier.address);
			stmt.setInt(4, supplier.isActive ? 1 : 0);
			stmt.setInt(5, supplier.id);
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			return e.getMessage();
		}
		return "Supplier updated!";
	}
	
	public String delete(int id) {
		String sql = "DELETE FROM suppliers WHERE id = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			return e.getMessage();
		}
		return "Supplier deleted!";
	}
	
}
