package gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import repo.SupplierRepo;


public class IMSGui {
	   

	public IMSGui (SupplierRepo supplierRepo) {
        JTabbedPane tabPane = new JTabbedPane();
        tabPane.addTab("Boardgames", new BoardGamePanel());
        tabPane.addTab("Sales", new SalesPanel());
        tabPane.addTab("Purchases", new PurchasesPanel());
        tabPane.addTab("Suppliers", new SuppliersPanel(supplierRepo));
        
     
           
        }
        
        JFrame frame = new JFrame("JTabbedPane Example");
        frame.add(tabPane);
        frame.setSize(400, 300);
        frame.setVisible(true);
	}
	
}
