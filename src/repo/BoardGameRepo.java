package repo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import structures.BoardGame;

public class BoardGameRepo {

    public List<BoardGame> listAll() {
        try {
            return fetchAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<BoardGame> fetchAll() throws Exception {

        String url = "jdbc:sqlite:db/project.db";

        Connection con = DriverManager.getConnection(url);
        Statement st = con.createStatement();
        String query = "SELECT * FROM board_games";
        ResultSet rs = st.executeQuery(query);

        List<BoardGame> boardGames = new ArrayList<>();
        while (rs.next()) {
            // Read the result set
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String maker = rs.getString("maker");
            String type = rs.getString("type");
            String mechanic = rs.getString("mechanic");
            int playerCount = rs.getInt("player_count");
            int ageLimit = rs.getInt("age_limit");
            boolean diceUsage = rs.getBoolean("dice_usage");
            boolean cardUsage = rs.getBoolean("card_usage");
            int averagePlayTime = rs.getInt("average_play_time");
            int price = rs.getInt("price");
            int quantityAvailable = rs.getInt("quantity_available");
            int quantitySold = rs.getInt("quantity_sold");

            BoardGame boardGame = new BoardGame(id, name, maker, type, mechanic, playerCount, ageLimit, diceUsage, cardUsage, averagePlayTime, price, quantityAvailable, quantitySold);
            boardGames.add(boardGame);
        }

        rs.close();
        st.close();
        con.close();

        return boardGames;
    }
}

