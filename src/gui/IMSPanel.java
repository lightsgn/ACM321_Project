
package gui;

import java.awt.GridLayout;
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
        initializeForm(formPanel);
        add(tablePanel);
        add(formPanel);
		
	}
	
	
	protected abstract void initializeForm(JPanel panel);

	public abstract String getTitle();
	
	public abstract String[][] getRows();
	
	public abstract String[] getColumnNames();
	
	
}