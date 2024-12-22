package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class MainGui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui gui = new MainGui();
					gui.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1004, 590);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(406, 66, 382, 380);
		frame.getContentPane().add(panel);
		
		JButton btnSales = new JButton("BOARDGAMES");
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoardGamePanel p = new BoardGamePanel();
				frame.getContentPane().removeAll(); // Remove all components from the frame
				frame.add(p); // Add the new panel
				frame.revalidate(); // Revalidate the frame
				frame.repaint(); // Repaint the frame
				
			}
		});
		btnSales.setBounds(147, 66, 144, 86);
		frame.getContentPane().add(btnSales);
		
		JButton btnSales_1 = new JButton("SALES");
		btnSales_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSales_1.setBounds(147, 164, 144, 86);
		frame.getContentPane().add(btnSales_1);
		
		JButton btnPurchases = new JButton("PURCHASES");
		btnPurchases.setBounds(147, 262, 144, 86);
		frame.getContentPane().add(btnPurchases);
		
		JButton btnSuppliers = new JButton("SUPPLIERS");
		btnSuppliers.setBounds(147, 360, 144, 86);
		frame.getContentPane().add(btnSuppliers);
		
		
	}
}
