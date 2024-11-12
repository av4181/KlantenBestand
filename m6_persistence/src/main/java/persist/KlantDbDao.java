package persist;

import model.Klant;
import model.KlantType;
import model.Klanten;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KlantDbDao implements KlantDao {

    private static final Logger logger = Logger.getLogger("be.kdg.model.KlantDbDao");
    private Connection connection = null;
    private Klanten klanten;

    // Opdracht 3.3 & 3.4

    public KlantDbDao(String path) {
        klanten = new Klanten();
        try {
            this.connection = DriverManager.getConnection(path);
            createTable();
            System.out.println("SQL connection successful");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Cant make connection with DB" + ex.getMessage());
        }
    }

    public void close() {

        if (connection == null) return;
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN COMPACT");
            statement.close();
            connection.close();
            System.out.println("\nDatabase gesloten");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Issues arose closing the connection: " + e.getMessage());
        }
    }

    private void createTable() {
        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("DROP TABLE IF EXISTS klantentabel");
            String create = "CREATE TABLE klantentabel" +
                    "(id INTEGER IDENTITY," +
                    "voornaam VARCHAR(30)," +
                    "achternaam VARCHAR(30)," +
                    "email VARCHAR(30)," +
                    "type VARCHAR(30)," +
                    "btw  DOUBLE," +
                    "aanmaakdatum DATE," +
                    "redflag BOOLEAN)";
            statement.executeUpdate(create);
            System.out.println("DB created");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creating table: " + e.getMessage());
        }
    }
    // Opdracht 3.5 CRUD - Create
    @Override
    public boolean insert(Klant klant) {
        if (klant.getId() >= 0) return false; //klant heeft al PK dus bestaat al in database
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO klantentabel VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, klant.getVoornaam());
            preparedStatement.setString(2, klant.getAchternaam());
            preparedStatement.setDate(3, Date.valueOf(klant.getEmail()));
            preparedStatement.setString(4, klant.getType().name());
            preparedStatement.setDouble(5, klant.getBtw());
            preparedStatement.setInt(6, klant.getAanmaakDatum().getYear());
            preparedStatement.setBoolean(7, klant.getRedflag());
            int rowsAffected = preparedStatement.executeUpdate();
            boolean result = rowsAffected == 1;
            preparedStatement.close();
            return result;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error when inserting: " + e.getMessage());
            return false;
        }
    }

    // Opdracht 3.5 CRUD - Delete
    @Override
    public boolean verwijder(String achternaam, String voornaam) {
        String sql = "DELETE FROM klantentabel WHERE achternaam = ? AND voornaam = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, achternaam);
            stmt.setString(2, voornaam);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Fout bij het verwijderen van klant: " + e.getMessage(), e);
            return false;
        }
    }

    // Opdracht 3.5 CRUD - Update
    @Override
    public boolean update(Klant klant) {
        try {
            Statement statement = connection.createStatement();
            int rowsaffected = statement.executeUpdate("UPDATE klantentabel set " +
                    "voornaam = '" + klant.getVoornaam() + "'," +
                    "achternaam = '" + klant.getAchternaam() + "'," +
                    "email = '" + klant.getEmail() + "'," +
                    "type = '" + klant.getType().name() + "'," +
                    "btw = " + klant.getBtw() + "," +
                    "aanmaakdatum = '" + Date.valueOf(klant.getAanmaakDatum()) + "'," +
                    "redflag = " + klant.getRedflag() +
                    " where id = " + klant.getId()
            );

            Boolean res = rowsaffected == 1;
            statement.close();
            return res;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating klant: " + e.getMessage());
        }

        return false;
    }

    // Opdracht 3.5 CRUD - Read
    @Override
    public Klant retrieve(String achternaam) {
        Klant toRetrieveKlant = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT  * FROM klantentabel where achternaam = '" + achternaam + "'");
            if (rs.next()) {
                toRetrieveKlant = new Klant(
                        rs.getInt("id"),
                        rs.getString("voornaam"),
                        rs.getString("achternaam"),
                        rs.getString("email"),
                        KlantType.valueOf(rs.getString("type")),
                        rs.getDouble("btw"),
                        rs.getDate("aanmaakdatum").toLocalDate(),
                        rs.getBoolean("redflag")
                );
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error zoeken van Klant: " + e.getMessage());
        }
        return toRetrieveKlant;

    }

    //Opdarcht 3.5 e)
    public List<Klant> sortedOn(String query) {
        return execQuery("SELECT * FROM klantentabel ORDER BY " + query + " ASC");
    }

    private List<Klant> execQuery(String query) {
        List<Klant> klanten = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next())
            {
                klanten.add(new Klant(
                        rs.getInt("id"),
                        rs.getString("voornaam"),
                        rs.getString("achternaam"),
                        rs.getString("email"),
                        KlantType.valueOf(rs.getString("type")),
                        rs.getDouble("btw"),
                        rs.getDate("aanmaakdatum").toLocalDate(),
                        rs.getBoolean("redflag")
                ));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Fout bij het ophalen van klanten: " + e.getMessage(), e);
        }
        return klanten;
    }
    @Override
    public List<Klant> sortedOnAchternaam() {
        return sortedOn("SELECT * FROM klantentabel ORDER BY achternaam ASC");
    }

    @Override
    public List<Klant> sortedOnAanmaakDatum() {
        return sortedOn("SELECT * FROM klantentabel ORDER BY aanmaakdatum ASC");
    }

    @Override
    public List<Klant> sortedOnBtw() {
        return sortedOn("SELECT * FROM klantentabel ORDER BY btw ASC");
    }

}
