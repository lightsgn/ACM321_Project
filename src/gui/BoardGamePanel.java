package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class BoardGamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public BoardGamePanel() {
		setLayout(null);
		
		JButton btnSave = new JButton("save");
		btnSave.setBounds(304, 263, 117, 25);
		add(btnSave);
		
		JLabel lblHi = new JLabel("hi");
		lblHi.setBounds(139, 42, 70, 15);
		add(lblHi);

	}
}
