package repo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import structures.Supplier;

public class SupplierRepo {
	
	
	public List<Supplier> listAll() {
		try {
			return fetchAll();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private List<Supplier> fetchAll() throws Exception{
		
		String url = "jdbc:sqlite:db/project.db"; 
		
		Connection con = DriverManager.getConnection(url);
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
	    con.close();
	    return suppliers;
	}
	
}
