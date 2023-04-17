package system;

import entity.Gamer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//change the former 4 function for test
public class gamerDAO {
    public boolean addToCart(int GameID){

        // Check if the game already exists in the cart or the library
        String checkGameQuery = "SELECT * FROM Cart WHERE CartID = 4 AND cartGameID = ? " +
                                "UNION " +
                                "SELECT * FROM Library WHERE LibraryID = 4 AND libGameID = ?;";


        try (Connection conn = DB.getConnection();
             PreparedStatement checkGameSt = conn.prepareStatement(checkGameQuery)) {

            // Set query parameters
            //checkGameSt.setInt(1, Session.getInstance().getUserID());
            checkGameSt.setInt(1, GameID);
            checkGameSt.setInt(2, GameID);

            // Execute the query
            ResultSet rs = checkGameSt.executeQuery();

            // If a result exists, the game is already in the cart, so return false
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "INSERT INTO Cart (CartID, cartGameID) VALUES (4, ?);";


        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(query)){

            //st.setInt(1, Session.getInstance().getUserID());
            st.setInt(1, GameID); // 2

            st.executeUpdate();

            return true;

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeConnection();
        }

        return false;
    }

    //Purchase means add games to library directly
    public boolean purchase(int GameID){

        // Check if the game already exists in the cart
        String checkGameQuery = "SELECT * FROM Library WHERE LibraryID = 4 AND libGameID = ?;";

        try (Connection conn = DB.getConnection();
             PreparedStatement checkGameSt = conn.prepareStatement(checkGameQuery)) {

            // Set query parameters
            //checkGameSt.setInt(1, Session.getInstance().getUserID());
            checkGameSt.setInt(1, GameID);

            // Execute the query
            ResultSet rs = checkGameSt.executeQuery();

            // If a result exists, the game is already in the cart, so return false
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "INSERT INTO Library (LibraryID, libGameID) VALUES (4, ?);";

        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(query)){

            //st.setInt(1, Session.getInstance().getUserID());
            st.setInt(1, GameID);// 2

            st.executeUpdate();

            return true;

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeConnection();
        }

        return false;
    }

    //Delete the game from cart when this game has been purchased or just deleted
    public boolean delete(int GameID){
        String query = "DELETE FROM Cart WHERE CartID = 4 AND cartGameID = ?;";

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {

            // Set query parameters
            //st.setInt(1, Session.getInstance().getUserID());
            st.setInt(1, GameID);// 2

            // Execute the query
            int affectedRows = st.executeUpdate();

            // If at least one row is affected, the game was deleted from the cart
            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection();
        }

        return false;

    }

    //Remove the game from library
    public boolean remove(int GameID){
        String query = "DELETE FROM Library WHERE LibraryID = 4 AND libGameID = ?;";

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {

            // Set query parameters
            //st.setInt(1, Session.getInstance().getUserID());
            st.setInt(1, GameID);// 2

            // Execute the query
            int affectedRows = st.executeUpdate();

            // If at least one row is affected, return true
            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection();
        }

        return false;
    }

    //Retrieve and display gamer information
    public Gamer getGamerInfo(){

        String query = "SELECT User.UserName, User.UserID, User.Email, User.Phone, Gamer.PaymentMethod" +
                        " FROM User INNER JOIN Gamer ON User.UserID = Gamer.GamerID" +
                        " WHERE User.UserID = ?";

        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(query)){

            st.setInt(1, Session.getInstance().getUserID());

            try(ResultSet rs = st.executeQuery()){
                if(rs.next()){
                    String username = rs.getString("UserName");
                    int userID = rs.getInt("UserID");
                    String email = rs.getString("Email");
                    String phone = rs.getString("Phone");
                    String payment = rs.getString("PaymentMethod");

                    // Create a User object to hold the account information
                    Gamer gamer = new Gamer();
                    gamer.setUserName(username);
                    gamer.setUserID(userID);
                    gamer.setEmail(email);
                    gamer.setPhone(phone);
                    gamer.setPaymentMethod(payment);

                    return gamer;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection();
        }

        return null;
    }
}
