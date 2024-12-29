package model;

import javax.swing.JPanel;

import gui.IMSPanel;

public class SalesPanel extends IMSPanel {
	private static final long serialVersionUID = 1L;

	public SalesPanel() {
		addContent();
	}

	public String getTitle() {
		return "Sales";
	}

	@Override
	public String[][] getRows() {
		// TODO Auto-generated method stub
		return new String[][] {};
	}

	@Override
	public String[] getColumnNames() {
		// TODO Auto-generated method stub
		return new String[] {};
	}

	@Override
	protected void initializeForm(JPanel panel) {
		// TODO Auto-generated method stub
		
	}
}
