package system;

//Display games in library

import entity.Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class libraryDAO extends gameDAO{

    public int[] getGamesInLibrary(){
        String libraryQuery = "SELECT libGameID FROM Library WHERE LibraryID = ?;"; // Test
        List<Integer> gamesInLibrary = new ArrayList<>();

        try (Connection conn = DB.getConnection();
             PreparedStatement stLibrary = conn.prepareStatement(libraryQuery)) {

            stLibrary.setInt(1, Session.getInstance().getUserID());
            ResultSet rsLibrary = stLibrary.executeQuery();

            while (rsLibrary.next()) {
                int gameID = rsLibrary.getInt("libGameID");
                gamesInLibrary.add(gameID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection();
        }

        return gamesInLibrary.stream().mapToInt(Integer::intValue).toArray();
    }
}
