package gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public abstract class IMSPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public void addContent() {
		setLayout(new BorderLayout());
        JTable table = new JTable(getRows(), getColumnNames());
        JScrollPane scrollPane = new JScrollPane(table);
        add(new JLabel(getTitle()), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
	}
	
	public abstract String getTitle();
	
	public abstract String[][] getRows();
	
	public abstract String[] getColumnNames();
	
}













