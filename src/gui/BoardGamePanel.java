package gui;

public class BoardGamePanel extends IMSPanel {
	private static final long serialVersionUID = 1L;

	public BoardGamePanel() {
		addContent();
	}
	
	public String getTitle(){
		return "Board Games";
	}

	@Override
	public String[][] getRows() {
		// TODO Auto-generated method stub
		return new String[][]{};
	}

	@Override
	public String[] getColumnNames() {
		// TODO Auto-generated method stub
		return new String[]{};
	}
}
