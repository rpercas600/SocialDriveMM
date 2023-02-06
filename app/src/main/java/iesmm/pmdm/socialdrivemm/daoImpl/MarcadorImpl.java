package iesmm.pmdm.socialdrivemm.daoImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import iesmm.pmdm.socialdrivemm.dao.DAOMarcador;
import iesmm.pmdm.socialdrivemm.model.Marcador;
import iesmm.pmdm.socialdrivemm.utils.Conexion;

public class MarcadorImpl implements DAOMarcador {

    @Override
    public List<Marcador> listarMacadores() {
        List<Marcador> listaMarcadores = new ArrayList<>();

        String sql = "SELECT * FROM MARCADOR";
        Connection con = null;

        try {

            con = Conexion.getConnection();

            Statement sqlStatement = con.createStatement();
            ResultSet sel = sqlStatement.executeQuery(sql);

            while (sel.next()) {
                Marcador inci = new Marcador(sel.getString("NumIncidencia")
                        , sel.getString("CodPartido")
                        , sel.getString("CodJugador")
                        , sel.getString("Tipo"));

                inci.toString();
                listaMarcadores.add(inci);
            }



        } catch (SQLException | IOException e) {
            System.err.print("Transaction is being rolled back");
            listaMarcadores = null;
            throw new RuntimeException(e);
        }
        return listaMarcadores;
    }

    @Override
    public boolean insert(Marcador marcador) {
        return false;
    }

    @Override
    public boolean update(Marcador marcador) {
        return false;
    }

    @Override
    public boolean delete(String idMarc) {
        return false;
    }

}
