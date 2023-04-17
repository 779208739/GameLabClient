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


    public int[] getGamesByType(String type) {

        //Click button "ALL" will show all games
        String query;
        if ("ALL".equalsIgnoreCase(type)) {
            query = "SELECT GameID FROM Game";
        } else {
            query = "SELECT GameID FROM Game WHERE Type = ?";
        }
        List<Integer> gamesByType = new ArrayList<>();

        try (Connection conn = DB.getConnection();
             PreparedStatement stGames = conn.prepareStatement(query)) {

            if (!"ALL".equalsIgnoreCase(type)) {
                stGames.setString(1, type);
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
    public int[] searchGames(String input, String type) {
        StringBuilder queryBuilder = new StringBuilder(
                "SELECT DISTINCT g.GameID FROM Game AS g " + "JOIN Keyword AS k ON g.GameID = k.IdGame_keyword " + "WHERE "
        );

        if (!type.equals("All")) {
            queryBuilder.append("g.Type = ? AND ");
        }

        queryBuilder.append("(g.Description LIKE ? OR g.GameName LIKE ? OR k.Keyword LIKE ?);");

        String query = queryBuilder.toString();

        List<Integer> games = new ArrayList<>();

        try (Connection conn = DB.getConnection();
             PreparedStatement stGames = conn.prepareStatement(query)) {

            String keywordPattern = "%" + input + "%";

            int paramIndex = 1;
            if (!type.equals("All")) {
                stGames.setString(paramIndex++, type);
            }
            stGames.setString(paramIndex++, keywordPattern);
            stGames.setString(paramIndex++, keywordPattern);
            stGames.setString(paramIndex, keywordPattern);

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
