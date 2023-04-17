package system;

import java.sql.*;

public class userDAO {

    // pass test
    public boolean Signup(String name, String password, String email, String phone) {

        String userQuery = "INSERT INTO User (UserName, Password, Email, Phone) VALUES (?, ?, ?, ?);";

        try(Connection conn = DB.getConnection();
            PreparedStatement stUser = conn.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
        ){

            //Insert a new user into the User table
            stUser.setString(1, name);
            stUser.setString(2, password);
            stUser.setString(3, email);
            stUser.setString(4, phone);
            stUser.executeUpdate();

            // Retrieve the newly added UserID
            try(ResultSet rs = stUser.getGeneratedKeys()){
                if (rs.next()) {
                    int gamerID = rs.getInt(1); // User.UserID = Gamer.GamerID

                    // Add the new user into the Gamer table
                    String gamerQuery = "INSERT INTO Gamer (GamerID, PaymentMethod) VALUES (?, ?);";
                    try(PreparedStatement stGamer = conn.prepareStatement(gamerQuery)){
                        stGamer.setInt(1, gamerID);
                        stGamer.setString(2, "Credit Card");
                        stGamer.executeUpdate();
                    }
                } else {
                    // Handle the case when no key was generated
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeConnection();
        }
        return false;
    }

    public boolean Login(String username, String password){

        String query = "SELECT UserID FROM User WHERE UserName=? AND Password=?;";

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(query)) {

            // Set query parameters
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            // Execute the query
            if(rs.next()) {
                // If there is a result, the username and password match a user in the database
                int userID = rs.getInt("UserID");
                Session.getInstance().setUserID(userID);
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection();
        }
        return false;
    }

    public boolean isGamer(){

        String query = "SELECT * FROM Gamer WHERE GamerID = ?";

        try (Connection conn = DB.getConnection();
             PreparedStatement st = conn.prepareStatement(query)){

            st.setInt(1, Session.getInstance().getUserID());
            ResultSet rs = st.executeQuery();

            return rs.next();

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection();
        }

        return false;
    }

    public boolean update(String userName, String newPassword){

        String query = "UPDATE User SET Password = ? WHERE UserName = ?";

        try(Connection conn = DB.getConnection();
            PreparedStatement st = conn.prepareStatement(query)){

            //Set query parameters;
            st.setString(1, newPassword);
            st.setString(2, userName);

            //Execute the query
            st.executeUpdate();

            return true;

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection();
        }

        return false;
    }
}
