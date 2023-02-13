package iesmm.pmdm.socialdrivemm.utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
    private static Connection jdbcConnection = null; // Representa una conexión a la BD
    //private final String fileBD = "app/src/main/res/connection.properties";
    private final String uri = "jdbc:mysql://192.168.1.85";
    private final String port = "3306";
    private final String bd = "socialdrive";
    private final String username = "root";
    private final String password = "dammm";



    private Conexion() throws IOException, SQLException {
        /*
        // 1. Cargar configuración de fichero de propiedades en ruta por defecto
        Properties props = new Properties();
        props.load(new FileReader(fileBD));

        // 2. Cargar valores de propiedades del fichero

        this.uri = props.getProperty("uri");
        this.port = props.getProperty("port");
        this.bd = props.getProperty("bd");
        this.username = props.getProperty("username");
        this.password = props.getProperty("password");
        */
        // 3. Realizar conexión a la BD
        jdbcConnection = DriverManager.getConnection(uri + ":" + port + "/"+ bd, username, password);
    }

    /**
     * Conecta y/o devuelve una conexión a la base de datos previamente establecida.
     *
     * @return la conexión de la base de datos.
     * @throws SQLException devuelve una excepción si no se puede obtener la conexión.
     */
    public static Connection getConnection() throws SQLException, IOException {
        if (jdbcConnection == null || jdbcConnection.isClosed())
            new Conexion();

        return jdbcConnection;
    }

    /**
     * Cierra la conexión a la base de datos.
     *
     * @return devuelve cierto si la conexión ha sido cerrada, falso en caso
     * contrario
     * @throws SQLException devuelve una excepción si no puede cerrar la conexión.
     */
    public static boolean close() throws SQLException {
        boolean closed = false;

        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
            closed = true;
        }

        return closed;
    }
}
