package gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import repo.BoardGameRepo;
import repo.SupplierRepo;

import repo.BoardGameRepo;

public class IMSGui {
	  




	public IMSGui (SupplierRepo supplierRepo, BoardGameRepo boardGameRepo) {
        JTabbedPane tabPane = new JTabbedPane();
        tabPane.addTab("Boardgames", new BoardGamePanel(boardGameRepo));
        tabPane.addTab("Sales", new SalesPanel());
        tabPane.addTab("Purchases", new PurchasesPanel());
        tabPane.addTab("Suppliers", new SuppliersPanel(supplierRepo));
        
        JFrame frame = new JFrame("JTabbedPane Example");
        frame.add(tabPane);
        frame.setSize(400, 300);
        frame.setVisible(true);
	}
	
}
