package gui;

import java.util.List;

import repo.BoardGameRepo;
import structures.BoardGame;


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
		List<BoardGame> boardGames = repo.listAll();
        String[][] rows = new String[boardGames.size()][];
        for (int i = 0; i < boardGames.size(); i++)
            rows[i] = getRow(boardGames.get(i));
        return rows;
	}

	@Override
	public String[] getColumnNames() {
		return new String[] {
            "Id", "Name", "Maker", "Type", "Mechanic", "Player Count", "Age Limit", "Dice Usage", "Card Usage", "Average Play Time", "Price", "Quantity Available", "Quantity Sold"
        };
	}
	
	public String[] getRow(BoardGame bg) {
        return new String[] {
            Integer.toString(bg.id),
            bg.name,
            bg.maker,
            bg.type,
            bg.mechanic,
            Integer.toString(bg.playerCount),
            Integer.toString(bg.ageLimit),
            Boolean.toString(bg.diceUsage),
            Boolean.toString(bg.cardUsage),
            Integer.toString(bg.averagePlayTime),
            Integer.toString(bg.price),
            Integer.toString(bg.quantityAvailable),
            Integer.toString(bg.quantitySold)};
}
}
