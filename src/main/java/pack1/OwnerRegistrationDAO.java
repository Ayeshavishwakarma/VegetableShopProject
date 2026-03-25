package pack1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerRegistrationDAO {
    
    public boolean registerOwner(String username, String password, String fname, String lname, String addr, String mid, String phone) {
        boolean status = false;
        try (Connection con = DBConnect.getcon()) {
            
            // Step 1: Check if username already exists
            if (isUsernameExists(username, con)) {
                System.out.println("Registration failed: Username already exists.");
                return false;
            }

            // Step 2: Insert new owner
            String query = "INSERT INTO owner (USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, PHONE) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, fname);
                ps.setString(4, lname);
                ps.setString(5, addr);
                ps.setString(6, mid);
                ps.setString(7, phone);

                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    status = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log properly in production
        }
        return status;
    }

    // Method to check if the username already exists
    private boolean isUsernameExists(String username, Connection con) throws SQLException {
        String query = "SELECT COUNT(*) FROM owner WHERE USERNAME = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true; // Username exists
                }
            }
        }
        return false;
    }
}