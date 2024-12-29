package gui;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public abstract class IMSPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JTable table;
	JPanel formPanel;

	public void addContent() {
		setLayout(new GridLayout());
        table = new JTable(getRows(), getColumnNames());
        formPanel = new JPanel();
        JScrollPane tablePanel = new JScrollPane(table);
        //add(new JLabel(getTitle()));
        createForm(formPanel);
        add(tablePanel);
        add(formPanel);
		
	}
	
	
	protected abstract void initializeForm(JPanel panel);

	public abstract String getTitle();
	
	public abstract String[][] getRows();
	
	public abstract String[] getColumnNames();
	
	public void createForm(JPanel formPanel) {
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new GridLayout(10 , 1));
        boxPanel.setBackground(new Color(240, 240, 255));
        JPanel flowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowPanel.setBackground(new Color(240, 255, 240));
        flowPanel.add(boxPanel);
        formPanel.add(flowPanel);
        initializeForm(boxPanel);
        
	}
	
}













