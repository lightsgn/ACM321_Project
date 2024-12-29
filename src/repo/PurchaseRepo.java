package repo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import structures.Purchase;

public class PurchaseRepo {
	
	Connection con;
	public PurchaseRepo(Connection con) {
		this.con=con;
		
	}
	
	
	public List<Purchase> listAll() {
		try {
			return fetchAll();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private List<Purchase> fetchAll() throws Exception{
		
		String url = "jdbc:sqlite:db/project.db"; 
		
		Connection con = DriverManager.getConnection(url);
		Statement st = con.createStatement();
		String query="select * from purchases";
		ResultSet rs = st.executeQuery(query);
		
		List<Purchase> purchases = new ArrayList<Purchase>();
	    while(rs.next()) {
          // read the result set
	    	int id =rs.getInt("id");
	    	String supplierId =rs.getString("supplier_id");
	    	String boardgameId =rs.getString("boardgame_id");
	    	String date =rs.getString("date");
	    	int quantity =rs.getInt("quantity");
	    	int unitPrice =rs.getInt("unit_price");
	    	int vat =rs.getInt("vat");

	    	Purchase purchase = new Purchase(id
	    			, supplierId, boardgameId, date, quantity,unitPrice,vat);
	    	purchases.add(purchase);
	    } 
	    rs.close();
	    st.close();
	    con.close();
	    return purchases;
	}
	
}
