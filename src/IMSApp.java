import java.sql.Connection;
import java.sql.DriverManager;

import gui.IMSGui;
import repo.SupplierRepo;
import repo.BoardGameRepo;
import repo.PurchaseRepo;
import repo.SaleRepo;


public class IMSApp {
	public static void main(String[] args) throws Exception {
		Connection con = getConnection();
		if(con == null) con = createDatabase();
		SupplierRepo repo = new SupplierRepo(con);
		SaleRepo saleRepo = new SaleRepo(con);
		BoardGameRepo boardGameRepo = new BoardGameRepo(con);
		PurchaseRepo purchaseRepo = new PurchaseRepo(con);
		new IMSGui(repo, boardGameRepo,purchaseRepo,saleRepo);
		con.close();
		
	}
	
	public static Connection getConnection() throws Exception {
        String url = "jdbc:sqlite:db/project.db";
        Connection con = null;
        try{
        	con = DriverManager.getConnection(url);
        }catch(Exception e) {
        	System.out.println("Database does not exist");
        }
        return con;
		
	}
	
	private static Connection createDatabase() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
