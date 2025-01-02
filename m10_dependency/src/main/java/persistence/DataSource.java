package persistence;

import exceptions.KlantException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Aparte klasse om alle DB gerelateerde zaken te groeperen, connecteren, close, bijhouden user en paswoord HSQL
public class DataSource {

    private static final Logger logger = Logger.getLogger(DataSource.class.getName());
    private static final String URL = "jdbc:hsqldb:file:db/klantentabel";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static DataSource instance;
    private Connection connection;

    private DataSource() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "HSQLDB driver niet gevonden: " + e.getMessage(), e);
            throw new KlantException("HSQLDB driver niet gevonden", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Fout bij het maken van de database connectie: " + e.getMessage(), e);
            throw new KlantException("Fout bij database connectie", e);
        }
    }

    public static synchronized DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            throw new KlantException("Database connectie is niet beschikbaar");
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Fout bij het sluiten van de database connectie: " + e.getMessage(), e);
        }
    }
}
