package system;


//1.Search by keyword
//2.Get game info by type


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class storeDAO extends gameDAO{

    //limit is the number of games to fetch and offset is the starting point for fetching games
    public int[] getGamesByType(String type, int limit, int offset) {

        //Click button "ALL" will show all games
        String query;
        if ("ALL".equalsIgnoreCase(type)) {
            query = "SELECT GameID FROM Game LIMIT ? OFFSET ?;";
        } else {
            query = "SELECT GameID FROM Game WHERE Type = ? LIMIT ? OFFSET ?;";
        }
        List<Integer> gamesByType = new ArrayList<>();

        try (Connection conn = DB.getConnection();
             PreparedStatement stGames = conn.prepareStatement(query)) {

            if ("ALL".equalsIgnoreCase(type)) {
                stGames.setInt(1, limit);
                stGames.setInt(2, offset);
            } else {
                stGames.setString(1, type);
                stGames.setInt(2, limit);
                stGames.setInt(3, offset);
            }
            ResultSet rsGames = stGames.executeQuery();

            while (rsGames.next()) {
                int gameID = rsGames.getInt("GameID");
                gamesByType.add(gameID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection();
        }

        return gamesByType.stream().mapToInt(Integer::intValue).toArray();
    }

    //Search games
    public int[] searchGames(String input){
        String query = "SELECT DISTINCT g.GameID FROM Game AS g " +
                "JOIN Keyword AS k ON g.GameID = k.IdGame_keyword " +
                "WHERE g.Description LIKE ? OR k.Keyword LIKE ?;";


        List<Integer> gamesByKeyword = new ArrayList<>();

        try (Connection conn = DB.getConnection();
             PreparedStatement stGames = conn.prepareStatement(query)) {

            String keywordPattern = "%" + input + "%";
            stGames.setString(1, keywordPattern);
            stGames.setString(2, keywordPattern);
            ResultSet rsGames = stGames.executeQuery();

            while (rsGames.next()) {
                int gameID = rsGames.getInt("GameID");
                gamesByKeyword.add(gameID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection();
        }

        return gamesByKeyword.stream().mapToInt(Integer::intValue).toArray();
    }
    
    //search games in specific type
    public int[] searchGamesByType(String input, String type){
        String query = "SELECT DISTINCT g.GameID FROM Game AS g " +
                "JOIN Keyword AS k ON g.GameID = k.IdGame_keyword " +
                "WHERE g.Type = ? AND" +
                "(g.Description LIKE ? OR k.Keyword LIKE ?);";


        List<Integer> games = new ArrayList<>();

        try (Connection conn = DB.getConnection();
             PreparedStatement stGames = conn.prepareStatement(query)) {

            String keywordPattern = "%" + input + "%";
            stGames.setString(1, type);
            stGames.setString(2, keywordPattern);
            stGames.setString(3, keywordPattern);

            ResultSet rsGames = stGames.executeQuery();

            while (rsGames.next()) {
                int gameID = rsGames.getInt("GameID");
                games.add(gameID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection();
        }

        return games.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public int[] getGameIDs() {
        List<Integer> gameIDs = new ArrayList<>();
        String query = "SELECT GameID FROM Game;";

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                gameIDs.add(rs.getInt("GameID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection();
        }

        return gameIDs.stream().mapToInt(Integer::intValue).toArray();
    }
}
