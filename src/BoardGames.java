

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BoardGames {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		BoardGames app = new BoardGames();
		app.printSales();
	}
	
	/**
	 * Create the application.
	 */
	public BoardGames() {
		
	}
	
	private void printSales() throws Exception{
		String url = "jdbc:sqlite:db/project.db"; 
		
		Connection con = DriverManager.getConnection(url);
		Statement st = con.createStatement();
		String query="select * from Sales where sales_id>0";
		ResultSet rs = st.executeQuery(query);
	    while(rs.next()) {
          // read the result set
	    	int salesId =rs.getInt("sales_id");
	    	int prodId =rs.getInt("product_id");
	    	int tPrice =rs.getInt("total_price");
	    	System.out.println("sales: "+ salesId + ", productId = " + prodId + ", tPrice = " + tPrice); 
	    } 
	    rs.close();
	    st.close();
	    con.close();
	}
	
}
