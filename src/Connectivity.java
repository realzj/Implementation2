import java.sql.*;

public class Connectivity {
    Connection conn;

    public static void main(String[] args) {
        new Connectivity();
    }

    public Connectivity() {
        // connect to the database
        try {

            // database connection string
            // MySQL jdbc:mysql://{HOST}[:{PORT}][/{DB}]
            String url = "jdbc:mysql://smcse.city.ac.uk/phpmyadmin/db_sql.php?db=adbt079";
            // username and password
            conn = DriverManager.getConnection(url, "adbt079", "200006212");

            // examples of using the database
            doTests();

            // cleanup, close the connection
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void doTests() {
        //doSelectTest();

        doInsertTest(); //oSelectTest();
        //doUpdateTest(); doSelectTest();
        //doDeleteTest(); doSelectTest();
    }

    private void doSelectTest() {
        System.out.println("[OUTPUT FROM SELECT]");
        String query = "SELECT COF_NAME, PRICE FROM COFFEES";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String s = rs.getString("COF_NAME");
                float n = rs.getFloat("PRICE");
                System.out.println(s + "   " + n);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void doInsertTest() {
        System.out.print("\n[Performing INSERT] ... ");
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO team16Logins (username, password) " +
                    "VALUES ('Paulius', 'Kancleris')");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void doUpdateTest() {
        System.out.print("\n[Performing UPDATE] ... ");
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("UPDATE COFFEES SET PRICE=4.99 WHERE COF_NAME='BREAKFAST BLEND'");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void doDeleteTest() {
        System.out.print("\n[Performing DELETE] ... ");
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("DELETE FROM COFFEES WHERE COF_NAME='BREAKFAST BLEND'");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}