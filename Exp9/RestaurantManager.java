import java.sql.*;

public class RestaurantManager{
    static final String URL = "jdbc:mysql://localhost:3306/FoodHub";
    static final String USER = "root";
    static final String PASS = "ANMOl@2006";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("Connection established.");

            // Call individual methods
            insertRecords(conn);

            System.out.println("\n--- Items <= 100 ---");
            selectLowPriceItems(conn);

            System.out.println("\n--- Items in 'Cafe Java' ---");
            selectByRestaurant(conn, "Cafe Java");

            updatePrices(conn);

            deleteItemsByName(conn, "P%");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- INSERT METHOD ---
    public static void insertRecords(Connection conn) throws SQLException {
        String resSQL = "INSERT IGNORE INTO Restaurant (Id, Name, Address) VALUES (?, ?, ?)";
        String itemSQL = "INSERT IGNORE INTO MenuItem (Id, Name, Price, ResId) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pRes = conn.prepareStatement(resSQL);
             PreparedStatement pItem = conn.prepareStatement(itemSQL)) {

            // Insert 10 Restaurants
            for (int i = 1; i <= 10; i++) {
                pRes.setInt(1, i);
                pRes.setString(2, i == 1 ? "Cafe Java" : "Restaurant " + i);
                pRes.setString(3, "Location " + i);
                pRes.executeUpdate();
            }

            // Insert 10 Menu Items
            String[] names = {"Pizza", "Pasta", "Burger", "Pancakes", "Tea", "Coffee", "Salad", "Fries", "Soda", "Cake"};
            double[] prices = {120, 95, 80, 150, 20, 40, 110, 60, 30, 100};
            for (int i = 1; i <= 10; i++) {
                pItem.setInt(1, i);
                pItem.setString(2, names[i-1]);
                pItem.setDouble(3, prices[i-1]);
                pItem.setInt(4, (i % 10) + 1);
                pItem.executeUpdate();
            }
            System.out.println("Records inserted successfully.");
        }
    }

    // --- SELECT METHOD (Price <= 100) ---
    public static void selectLowPriceItems(Connection conn) throws SQLException {
        String sql = "SELECT * FROM MenuItem WHERE Price <= 100";
        printResultSet(conn, sql);
    }

    // --- SELECT METHOD (By Restaurant Name) ---
    public static void selectByRestaurant(Connection conn, String resName) throws SQLException {
        String sql = "SELECT m.* FROM MenuItem m JOIN Restaurant r ON m.ResId = r.Id WHERE r.Name = '" + resName + "'";
        printResultSet(conn, sql);
    }

    // --- UPDATE METHOD ---
    public static void updatePrices(Connection conn) throws SQLException {
        String sql = "UPDATE MenuItem SET Price = 200 WHERE Price <= 100";
        try (Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            System.out.println("\nUpdate complete. Rows affected: " + rows);
        }
    }

    // --- DELETE METHOD ---
    public static void deleteItemsByName(Connection conn, String pattern) throws SQLException {
        String sql = "DELETE FROM MenuItem WHERE Name LIKE ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pattern);
            int rows = pstmt.executeUpdate();
            System.out.println("\nDeletion complete. Rows removed: " + rows);
        }
    }

    // --- UTILITY METHOD TO PRINT TABLES ---
    private static void printResultSet(Connection conn, String query) throws SQLException {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    System.out.print(rsmd.getColumnName(i) + ": " + rs.getString(i) + " | ");
                }
                System.out.println();
            }
        }
    }
}