import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;
import java.util.Properties;

public class Connect {

    static Connection conn;

    public static void  main(String[] argv) throws SQLException, ClassNotFoundException, IOException {

        testCo();
        insert("Stéphane", "Lavarie");
        //updateData("John","Doe", 17);
        //deleteData(24);
        getData();
        closeBdd();
    }

    public static void deleteData(int monId) throws SQLException, ClassNotFoundException {
        String query = "Delete FROM person where id = ? ";

        try (PreparedStatement preparedStmt = conn.prepareStatement(query)) {
            preparedStmt.setInt(1, monId);
            preparedStmt.execute();

            conn.commit();
            System.out.println("id : " + monId + " supprimé !");

        } catch (SQLException e) {
            // Rollback any database transaction due to exception occurred
            conn.rollback();
            System.out.println("Transaction rollback...");
            e.printStackTrace();
        }
    }
    public static void updateData( String prenom , String nom, int monId) throws SQLException, ClassNotFoundException {
        String query = "Update person set first_name = ?, last_name = ? where id = ?";

        PreparedStatement preparedStmt = conn.prepareStatement(query);

        preparedStmt.setString(1, prenom);
        preparedStmt.setString(2, nom);
        preparedStmt.setInt(3, monId);
        preparedStmt.executeUpdate();

        System.out.println("id : " + monId + " mis à jour !");
    }

    public static void testCo() throws SQLException, ClassNotFoundException, IOException {
        FileInputStream fis = new FileInputStream("C:/Users/stef4/IdeaProjects/test-maven/src/main/resources/config.properties");
        Properties p = new Properties();
        p.load (fis);
        String dname = (String) p.get ("Dname");
        String url = (String) p.get ("URL");
        String user = (String) p.get ("Uname");
        String passwd = (String) p.get ("password");
        Class.forName(dname);

        conn = DriverManager.getConnection(url, user, passwd);
        conn.setAutoCommit(false);

        System.out.println("Connexion effective !");
    }

    public static void getData() throws SQLException, ClassNotFoundException{
        String query = "SELECT * FROM person";
        Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = st.executeQuery(query);

        System.out.println("Enregistrements dans la table...");

        while (rs.next())
        {
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            Date date = rs.getDate("dob");

            // print the result
            System.out.format("%s, %s, %s, %s\n", id, firstName, lastName, date);
        }
        // test Scroll update 
        rs.absolute(3);
        System.out.println("FIRST RECORD...");
        System.out.println(rs.getInt(1) + " -> " + rs.getString(2) + " " + rs.getString(3));

        rs.updateString(2, "John");
        rs.updateRow();
        System.out.println("FIRST RECORD MODIFY...");
        System.out.println(rs.getInt(1) + " -> " + rs.getString(2) + " " + rs.getString(3));
    }

    public static void closeBdd() throws SQLException {
        conn.close();
    }

    public static void insert(String first_name, String last_name) throws SQLException {
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

        // the mysql insert statement
        String query = " insert into person (first_name, last_name, dob)"
                + " values (?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, first_name);
        preparedStmt.setString (2, last_name);
        preparedStmt.setDate   (3, startDate);

        // execute the preparedstatement
        preparedStmt.execute();

        System.out.println(first_name +" "+ last_name + " a été ajouté ");
    }

}
