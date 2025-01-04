package repo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import structures.BoardGame;

public class BoardGameRepo {
	Connection con;
	
    public BoardGameRepo(Connection con) {
		this.con = con;
	}

	public List<BoardGame> listAll() {
        try {
            return fetchAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<BoardGame> fetchAll() throws Exception {

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
            String playerCount = rs.getString("player_count");
            String ageLimit = rs.getString("age_limit");
            boolean diceUsage = rs.getBoolean("dice_usage");
            boolean cardUsage = rs.getBoolean("card_usage");
            String averagePlayTime = rs.getString("average_play_time");
            int price = rs.getInt("price");
            int quantityAvailable = rs.getInt("quantity_available");
            int quantitySold = rs.getInt("quantity_sold");

            BoardGame boardGame = new BoardGame(id, name, maker, type, mechanic, playerCount, ageLimit, diceUsage, cardUsage, averagePlayTime, price, quantityAvailable, quantitySold);
            boardGames.add(boardGame);
        }

        rs.close();
        st.close();

        return boardGames;
    }

	public void addBoardGame(BoardGame boardGame) {
		// TODO Auto-generated method stub
	      String query = "INSERT INTO board_games (id, name, maker, type, mechanic, player_count, age_limit, dice_usage, card_usage, average_play_time, price, quantity_available, quantity_sold) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        try (PreparedStatement ps = con.prepareStatement(query)) {
	            ps.setInt(1, boardGame.id);
	            ps.setString(2, boardGame.name);
	            ps.setString(3, boardGame.maker);
	            ps.setString(4, boardGame.type);
	            ps.setString(5, boardGame.mechanic);
	            ps.setString(6, boardGame.playerCount);
	            ps.setString(7, boardGame.ageLimit);
	            ps.setBoolean(8, boardGame.diceUsage);
	            ps.setBoolean(9, boardGame.cardUsage);
	            ps.setString(10, boardGame.averagePlayTime);
	            ps.setInt(11, boardGame.price);
	            ps.setInt(12, boardGame.quantityAvailable);
	            ps.setInt(13, boardGame.quantitySold);

	            ps.executeUpdate();
	            System.out.println("Board Game added successfully: " + boardGame.name);

	        } catch (Exception e) {
	            throw new RuntimeException("Error adding board game: " + e.getMessage(), e);}
	        }
	        
	        public void updateBoardGame(BoardGame boardGame) {
	            String query = "UPDATE board_games SET name = ?, maker = ?, type = ?, mechanic = ?, player_count = ?, age_limit = ?, dice_usage = ?, card_usage = ?, average_play_time = ?, price = ?, quantity_available = ?, quantity_sold = ? WHERE id = ?";

	            try (PreparedStatement ps = con.prepareStatement(query)) {
	                ps.setString(1, boardGame.name);
	                ps.setString(2, boardGame.maker);
	                ps.setString(3, boardGame.type);
	                ps.setString(4, boardGame.mechanic);
	                ps.setString(5, boardGame.playerCount);
	                ps.setString(6, boardGame.ageLimit);
	                ps.setBoolean(7, boardGame.diceUsage);
	                ps.setBoolean(8, boardGame.cardUsage);
	                ps.setString(9, boardGame.averagePlayTime);
	                ps.setInt(10, boardGame.price);
	                ps.setInt(11, boardGame.quantityAvailable);
	                ps.setInt(12, boardGame.quantitySold);
	                ps.setInt(13, boardGame.id);

	                ps.executeUpdate();
	            } catch (Exception e) {
	                throw new RuntimeException("Error updating board game: " + e.getMessage(), e);
	            }
	        }

	        public void deleteBoardGame(int id) {
	            String query = "DELETE FROM board_games WHERE id = ?";

	            try (PreparedStatement ps = con.prepareStatement(query)) {
	                ps.setInt(1, id);
	                ps.executeUpdate();
	            } catch (Exception e) {
	                throw new RuntimeException("Error deleting board game: " + e.getMessage(), e);
	            }
	        }

	        public List<BoardGame> searchBoardGames(String keyword) {
	            String query = "SELECT * FROM board_games WHERE name LIKE ? OR maker LIKE ? OR type LIKE ?";

	            try (PreparedStatement ps = con.prepareStatement(query)) {
	                ps.setString(1, "%" + keyword + "%");
	                ps.setString(2, "%" + keyword + "%");
	                ps.setString(3, "%" + keyword + "%");
	                ResultSet rs = ps.executeQuery();

	                List<BoardGame> searchResults = new ArrayList<>();
	                while (rs.next()) {
	                    int id = rs.getInt("id");
	                    String name = rs.getString("name");
	                    String maker = rs.getString("maker");
	                    String type = rs.getString("type");
	                    String mechanic = rs.getString("mechanic");
	                    String playerCount = rs.getString("player_count");
	                    String ageLimit = rs.getString("age_limit");
	                    boolean diceUsage = rs.getBoolean("dice_usage");
	                    boolean cardUsage = rs.getBoolean("card_usage");
	                    String averagePlayTime = rs.getString("average_play_time");
	                    int price = rs.getInt("price");
	                    int quantityAvailable = rs.getInt("quantity_available");
	                    int quantitySold = rs.getInt("quantity_sold");

	                    BoardGame boardGame = new BoardGame(id, name, maker, type, mechanic, playerCount, ageLimit, diceUsage,
	                            cardUsage, averagePlayTime, price, quantityAvailable, quantitySold);
	                    searchResults.add(boardGame);
	                }
	                rs.close();
	                return searchResults;
	            } catch (Exception e) {
	                throw new RuntimeException("Error searching board games: " + e.getMessage(), e);
	            }
		
	}
}

