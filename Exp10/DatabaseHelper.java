import java.sql.*;

public class DatabaseHelper {
    // Ensure your DB name 'foodhub' is correct here
    private static final String URL = "jdbc:mysql://localhost:3306/foodhub";
    private static final String USER = "root";
    private static final String PASS = System.getenv("DB_PASS");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // CREATE (Insert)
    public static void insertItem(int id, String name, double price, int resId) throws SQLException {
        String sql = "INSERT INTO menuitem (Id, Name, Price, ResId) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, resId);
            pstmt.executeUpdate();
        }
    }

    // READ (Select)
    public static String getAllItems() throws SQLException {
        StringBuilder data = new StringBuilder();
        String sql = "SELECT * FROM menuitem";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                data.append(String.format("ID: %d | Name: %s | Price: %.2f | ResID: %d\n",
                        rs.getInt("Id"), rs.getString("Name"), rs.getDouble("Price"), rs.getInt("ResId")));
            }
        }
        return data.toString();
    }

    // UPDATE
    public static void updatePrice(int id, double newPrice) throws SQLException {
        String sql = "UPDATE menuitem SET Price = ? WHERE Id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
    }

    // DELETE
    public static void deleteItem(int id) throws SQLException {
        String sql = "DELETE FROM menuitem WHERE Id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}