package persistence.hsql;

import data.Data;
import exceptions.KlantException;
import model.Klant;
import model.KlantType;
import persistence.DataSource;
import persistence.KlantDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Implementatie van het data access object voor de interactie met hsql database
// Later kan er bv. ook een implementatie komen voor een PostGres DB etc.
public class HSQLKlantDao implements KlantDao {

    // Opdracht 2.3 b)
    private static HSQLKlantDao INSTANCE;

    private static final Logger logger = Logger.getLogger("be.kdg.model.KlantDbDao");
    private Connection connection;

    // Singleton patroon, klasse kan niet meerdere keren aangemaakt worden
    private HSQLKlantDao(String databasePath) {
        makeConnection(databasePath);
        createTable();
    }

    // getInstance methode voor het singleton Patroon
    public static synchronized HSQLKlantDao getInstance(String databasePath) {
        if (INSTANCE == null) {
            INSTANCE = new HSQLKlantDao(databasePath);
        }
        return INSTANCE;
    }

    // Connection  methode constructor apart maken & logger meldingen in KlantException steken als WARNING
    // Logger melding INFO als alles OK is
    private void makeConnection(String databasePath) {
        createTable();
    }

    // Logger gebruiken en meldingen in KlantException wrappen als WARNING
    // Logger melding INFO als alles OK is
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                logger.log(Level.INFO, "Connectie met database gesloten.");
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Fout bij het sluiten van de database: " + e.getMessage(), e);
                throw new KlantException("Fout bij het sluiten van de database", e);
            }
        }
    }

    // Opdracht 2.3 a) controle of tabel al bestaat of niet, logger meldingen in KlantException wrappen als WARNING
    // Logger melding INFO als alles OK is
    private void createTable() {
        try (Statement stmt = DataSource.getInstance().getConnection().createStatement()) {
            String sql = """                    
                    CREATE TABLE IF NOT EXISTS klantentabel 
                    (id INTEGER IDENTITY,
                    voornaam VARCHAR(50),
                    achternaam VARCHAR(50),
                    email VARCHAR(100),
                    type VARCHAR(20),
                    btw DOUBLE,
                    aanmaakDatum DATE,
                    redflag BOOLEAN) 
                    """;
            stmt.executeUpdate(sql);
            // Controleer of de tabel leeg is en vul de tabel met data uit de Data klasse
            ResultSet rs = stmt.executeQuery("""
                                                SELECT COUNT(*) FROM klantentabel
                                                """);
            rs.next();
            if (rs.getInt(1) == 0) {
                Data.getData().forEach(this::insert);
                logger.log(Level.INFO, "Tabel aangemaakt en gevuld met data.");
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Fout bij het aanmaken van de tabel: " + e.getMessage(), e);
            throw new KlantException("Fout bij het aanmaken van de tabel", e);
        }
    }

    // Opdracht 3.5 CRUD - Create
    @Override
    public boolean insert(Klant klant) {
        if (klant.getId() >= 0) return false; //klant heeft al PK dus bestaat al in database
        try {
            PreparedStatement preparedStatement = DataSource.getInstance().getConnection().prepareStatement(
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
        try (Statement statement = DataSource.getInstance().getConnection().createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
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

    // Opdracht 2.1 Hergebruik SortedOn methode met een SELECT * query
    @Override
    public List<Klant> getAllKlanten() {
        return sortedOn("SELECT * FROM klantentabel");
    }

}
