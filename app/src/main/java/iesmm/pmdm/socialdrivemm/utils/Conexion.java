package iesmm.pmdm.socialdrivemm.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Connection jdbcConnection = null;

    private final String uri = "jdbc:mysql://192.168.10.214";
    private final String port = "3306";
    private final String bd = "socialdrivemm";
    private final String username = "dam";
    private final String password = "dammm";

    private Conexion() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            jdbcConnection = DriverManager.getConnection(uri + ":" + port + "/"+ bd, username, password);

        } catch (ClassNotFoundException e) {
            System.out.println("Error de conexion");
            System.out.println(e);
        }
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
