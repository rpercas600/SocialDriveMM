package iesmm.pmdm.socialdrivemm.utils;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private final String uri = "jdbc:mysql://192.168.1.85";
    private final String port = "3306";
    private final String bd = "socialdrivemm";
    private final String username = "dam";
    private final String password = "dammm";

    private static Connection jdbcConnection;

    private Conexion() throws SQLException {


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        jdbcConnection = DriverManager.getConnection(uri + ":" + port + "/"+ bd, username, password);


    }

    /**
     * Conecta y/o devuelve una conexión a la base de datos previamente establecida.
     *
     * @return la conexión de la base de datos.
     * @throws SQLException devuelve una excepción si no se puede obtener la conexión.
     */
    public static java.sql.Connection getConnection() throws SQLException, IOException {
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
