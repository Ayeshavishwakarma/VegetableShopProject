package pack1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OwnerLoginDAO {
    public OwnerBean checkOwnerLogin(String username, String password) {
        OwnerBean owner = null;
        String sql = "SELECT * FROM OWNER WHERE USERNAME = ? AND PASSWORD = ?";

        try (Connection conn = DBConnect.getcon();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    owner = new OwnerBean();
                    owner.setuName(rs.getString("USERNAME"));
                    owner.setUpwd(rs.getString("PASSWORD")); // Avoid storing raw passwords
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return owner;
    }
}