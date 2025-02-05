package gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import repo.BoardGameRepo;
import repo.SupplierRepo;
import repo.PurchaseRepo;
import repo.SaleRepo;

public class IMSGui {

	public IMSGui (SupplierRepo supplierRepo, BoardGameRepo boardGameRepo, PurchaseRepo purchaseRepo, SaleRepo saleRepo) {
		
        JTabbedPane tabPane = new JTabbedPane();
        tabPane.addTab("Boardgames", new BoardGamePanel(boardGameRepo));
        tabPane.addTab("Sales", new SalesPanel(saleRepo));
        tabPane.addTab("Purchases", new PurchasesPanel(purchaseRepo));
        tabPane.addTab("Suppliers", new SuppliersPanel(supplierRepo));
        
        JFrame frame = new JFrame("JTabbedPane Example");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(tabPane);
        frame.setSize(1000, 600);
        frame.setVisible(true);
	}
	
}
