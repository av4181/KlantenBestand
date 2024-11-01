package persist;

import model.Klant;
import model.KlantType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KlantDbDao implements KlantDao {

    private static final Logger logger = Logger.getLogger("be.kdg.model.KlantDbDao");


    private Connection connection = null;

    public KlantDbDao(String path) {
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:" + path, "sa", "");
            System.out.println("SQL connection successful");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Cant make connection with DB" + ex.getMessage());
        }

        createTable();
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
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS klantentabel");
            statement.execute("CREATE TABLE klantentabel" +
                    "(id INTEGER IDENTITY," +
                    "voornaam VARCHAR(30)," +
                    "achternaam VARCHAR(30)," +
                    "email VARCHAR(30)," +
                    "type VARCHAR(30)," +
                    "btw  DOUBLE," +
                    "aanmaakdatum DATE," +
                    "redflag BOOLEAN)"
            );
            System.out.println("DB created");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creating table: " + e.getMessage());
        }
    }

    @Override
    public boolean insert(Klant klant) {
        if (klant.getId() >= 0) return false; //klant heeft al PK dus bestaat al in database
        try {
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

    @Override
    public boolean delete(String achternaam) {
        try {
            Statement statement = connection.createStatement();
            String query;
            if (achternaam.equals("*")) {
                query = "DELETE FROM klantentabel";
            } else {
                query = "DELETE FROM klantentabel where achternaam = '" + achternaam + "'";
            }

            int rowsaffected = statement.executeUpdate(query);
            return rowsaffected != 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
            return false;
        }

    }

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

    @Override
    public List<Klant> sortedOn(String query) {
        List<Klant> sortedList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM klantentabel ORDER BY " + query);
            while (rs.next()) {
                sortedList.add(
                        new Klant(
                                rs.getInt("id"),
                                rs.getString("voornaam"),
                                rs.getString("achternaam"),
                                rs.getString("email"),
                                KlantType.valueOf(rs.getString("type")),
                                rs.getDouble("btw"),
                                rs.getDate("aanmaakdatum").toLocalDate(),
                                rs.getBoolean("redflag")
                        )
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving sql sorted: " + e);
        }
//        sortedList.forEach(System.out::println);
        return sortedList;
    }


}
