import java.sql.*;

public class RestaurantManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/FoodHub";
    private static final String USER = "root";
    private static final String PASS = System.getenv("DB_PASS");

    public static void main(String[] args) {
        if (PASS == null) {
            System.err.println("Error: Set DB_PASS in Run Configurations!");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.println("--- Connected to FoodHub Database ---\n");
            Statement stmt = conn.createStatement();

            // Setup and Reset
            createTables(stmt);
            resetData(stmt);

            // 1. CREATE
            insertRecords(stmt);

            // 2. READ
            selectBudgetItems(stmt);
            selectCafeJavaItems(stmt);

            // 3. UPDATE
            updatePrices(stmt);

            // 4. DELETE
            deletePItems(stmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- METHOD: Create Tables ---
    private static void createTables(Statement stmt) throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Restaurant (Id INT PRIMARY KEY, Name VARCHAR(100), Address VARCHAR(255))");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS MenuItem (Id INT PRIMARY KEY, Name VARCHAR(100), Price DECIMAL(10, 2), ResId INT, FOREIGN KEY (ResId) REFERENCES Restaurant(Id))");
    }

    // --- METHOD: Reset Data (Truncate) ---
    private static void resetData(Statement stmt) throws SQLException {
        stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
        stmt.executeUpdate("TRUNCATE TABLE MenuItem");
        stmt.executeUpdate("TRUNCATE TABLE Restaurant");
        stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
    }

    // --- METHOD: Insert (Create) ---
    private static void insertRecords(Statement stmt) throws SQLException {
        System.out.println("[Step 1] Inserting 10 records...");
        for (int i = 1; i <= 10; i++) {
            String rName = (i == 1) ? "Cafe Java" : "Bistro " + i;
            stmt.executeUpdate("INSERT INTO Restaurant VALUES (" + i + ", '" + rName + "', 'Loc " + i + "')");
        }
        String[] foods = {"Pasta", "Pizza", "Burger", "Tea", "Coffee", "Salad", "Pancakes", "Soda", "Steak", "Pie"};
        for (int i = 1; i <= 10; i++) {
            stmt.executeUpdate("INSERT INTO MenuItem VALUES (" + i + ", '" + foods[i-1] + "', " + (i * 25.0) + ", " + ((i % 10) + 1) + ")");
        }
    }

    // --- METHOD: Select Price <= 100 (Read) ---
    private static void selectBudgetItems(Statement stmt) throws SQLException {
        System.out.println("\n[Step 2] Items with Price <= 100:");
        printTable(stmt.executeQuery("SELECT * FROM MenuItem WHERE Price <= 100"));
    }

    // --- METHOD: Select from Cafe Java (Read/Join) ---
    private static void selectCafeJavaItems(Statement stmt) throws SQLException {
        System.out.println("\n[Step 3] Items in 'Cafe Java':");
        String sql = "SELECT m.* FROM MenuItem m JOIN Restaurant r ON m.ResId = r.Id WHERE r.Name = 'Cafe Java'";
        printTable(stmt.executeQuery(sql));
    }

    // --- METHOD: Update Prices (Update) ---
    private static void updatePrices(Statement stmt) throws SQLException {
        System.out.println("\n[Step 4] Updating Price <= 100 to 200...");
        stmt.executeUpdate("UPDATE MenuItem SET Price = 200 WHERE Price <= 100");
        printTable(stmt.executeQuery("SELECT * FROM MenuItem"));
    }

    // --- METHOD: Delete 'P' Items (Delete) ---
    private static void deletePItems(Statement stmt) throws SQLException {
        System.out.println("\n[Step 5] Deleting items starting with 'P'...");
        stmt.executeUpdate("DELETE FROM MenuItem WHERE Name LIKE 'P%'");
        printTable(stmt.executeQuery("SELECT * FROM MenuItem"));
    }

    // --- METHOD: Tabular Print Helper ---
    private static void printTable(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int cols = md.getColumnCount();
        for (int i = 1; i <= cols; i++) System.out.print(String.format("%-15s", md.getColumnName(i)));
        System.out.println("\n------------------------------------------------------------");
        while (rs.next()) {
            for (int i = 1; i <= cols; i++) System.out.print(String.format("%-15s", rs.getString(i)));
            System.out.println();
        }
    }
}