package system;

import entity.Game;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class adminDAO {
    public boolean deleteGamer(int gamerID){

        //Delete gamer information including info in User, Gamer, Cart and Library
        String userQuery = "DELETE FROM User WHERE UserID = ?";
        String gamerQuery = "DELETE FROM Gamer WHERE GamerID = ?";
        String cartQuery = "DELETE FROM Cart WHERE CartID = ?";
        String libraryQuery = "DELETE FROM Library WHERE LibraryID = ?";

        try(Connection conn = DB.getConnection();
            PreparedStatement stUser = conn.prepareStatement(userQuery);
            PreparedStatement stGamer = conn.prepareStatement(gamerQuery);
            PreparedStatement stCart = conn.prepareStatement(cartQuery);
            PreparedStatement stLibrary = conn.prepareStatement(libraryQuery)){

            stCart.setInt(1, gamerID);
            stLibrary.setInt(1, gamerID);
            stCart.executeUpdate();
            stLibrary.executeUpdate();

            stUser.setInt(1, gamerID);
            stGamer.setInt(1, gamerID);
            int gamerRows = stGamer.executeUpdate();
            int userRows = stUser.executeUpdate();

            if(userRows > 0 && gamerRows > 0){
                return true;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection();
        }

        return false;
    }

    public boolean addGame(String GameName, String Description, Double Price, String Type, List<String> Image, List<String> Keyword ){

        //Game info is seperated in three tables, Game, Image and Keyword
        //Game.GameID = Image.IdGame_image, Game.GameID = Keyword.IdGame_keyword
        //GameID, ImageID and KeywordID are automatically generated
        String gameQuery = "INSERT INTO Game (GameName, Description, Price, Type) VALUES (?, ?, ?, ?);";
        String imageQuery = "INSERT INTO Image (IdGame_image, Image) VALUES (?, ?);";
        String keywordQuery = "INSERT INTO Keyword (IdGame_keyword, Keyword) VALUES (?, ?);";

        try(Connection conn = DB.getConnection();
            PreparedStatement stGame = conn.prepareStatement(gameQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement stImage = conn.prepareStatement(imageQuery);
            PreparedStatement stKeyword = conn.prepareStatement(keywordQuery)){

            //Make sure that all the information related to a game is inserted into the database
            conn.setAutoCommit(false);

            stGame.setString(1, GameName);
            stGame.setString(2, Description);
            stGame.setDouble(3, Price);
            stGame.setString(4, Type);

            int gameRowsAffected = stGame.executeUpdate();

            if(gameRowsAffected > 0){
                //Get generated GameID
                ResultSet rs = stGame.getGeneratedKeys();
                if(rs.next()){
                    int gameID = rs.getInt(1);

                    //Insert images
                    for(String image : Image){
                        stImage.setInt(1, gameID);
                        stImage.setString(2, image);
                        stImage.addBatch();
                    }
                    stImage.executeBatch();

                    //Insert keywords
                    for(String keyword : Keyword){
                        stKeyword.setInt(1, gameID);
                        stKeyword.setString(2, keyword);
                        stKeyword.addBatch();
                    }
                    stKeyword.executeBatch();

                    conn.commit();
                    return true;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection();
        }

        return false;

    }

    public boolean deleteGame(int gameID){
        String libraryQuery = "DELETE FROM Library WHERE libGameID = ?;";
        String cartQuery = "DELETE FROM Cart WHERE cartGameID = ?;";
        String gameQuery = "DELETE FROM Game WHERE GameID = ?;";
        String keywordQuery = "DELETE FROM Keyword WHERE IdGame_keyword = ?;";
        String imageQuery = "DELETE FROM Image WHERE IdGame_image = ?;";

        Connection con = null;
        PreparedStatement stLibrary = null;
        PreparedStatement stCart = null;
        PreparedStatement stGame = null;
        PreparedStatement stKeyword = null;
        PreparedStatement stImage = null;

        try {
            con = DB.getConnection();
            stLibrary = con.prepareStatement(libraryQuery);
            stCart = con.prepareStatement(cartQuery);
            stGame = con.prepareStatement(gameQuery);
            stKeyword = con.prepareStatement(keywordQuery);
            stImage = con.prepareStatement(imageQuery);

            con.setAutoCommit(false);

            // Delete library entries
            stLibrary.setInt(1, gameID);
            stLibrary.executeUpdate();

            // Delete cart entries
            stCart.setInt(1, gameID);
            stCart.executeUpdate();

            // Delete keywords
            stKeyword.setInt(1, gameID);
            stKeyword.executeUpdate();

            // Delete images
            stImage.setInt(1, gameID);
            stImage.executeUpdate();

            // Delete game
            stGame.setInt(1, gameID);
            int gameRows = stGame.executeUpdate();
            con.commit();

            if (gameRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (DB.getConnection() != null) {
                    DB.getConnection().rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            DB.closeConnection();
        }

        return false;
    }

    
}
