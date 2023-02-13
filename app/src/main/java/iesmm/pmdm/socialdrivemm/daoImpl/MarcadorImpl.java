package iesmm.pmdm.socialdrivemm.daoImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        Connection con;

        try {

            con = Conexion.getConnection();

            Statement sqlStatement = con.createStatement();
            ResultSet sel = sqlStatement.executeQuery(sql);

            while (sel.next()) {
                Marcador inci = new Marcador(sel.getString("hora")
                        , sel.getString("ubi")
                        , sel.getString("descripcion")
                        , sel.getString("icono")
                        , sel.getString("user"));

                inci.toString();
                listaMarcadores.add(inci);
            }

            con.close();

        } catch (SQLException | IOException e) {
            System.err.print("Transaction is being rolled back");
            listaMarcadores = null;
            throw new RuntimeException(e);
        }
        return listaMarcadores;
    }

    @Override
    public boolean insert(Marcador marcador) {

        boolean resIns;

        try {
            Connection con = Conexion.getConnection();
            String sql = "INSERT INTO marcador (hora, ubi, descripcion, via, user) values(?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            //Add Parameters
            stmt.setString(1, marcador.getHora());
            stmt.setString(2, marcador.getUbi());
            stmt.setString(3, marcador.getDescripcion());
            stmt.setString(4, marcador.getVia());
            stmt.setString(5, marcador.getUser());

            stmt.executeUpdate();

            con.close();

            resIns = true;
        } catch (SQLException | IOException e) {
            resIns = false;
            e.printStackTrace();
        }
        return resIns;
    }


    @Override
    public boolean delete(String id) {
        boolean resIns;

        try {
            Connection con = Conexion.getConnection();
            String sql = "DELETE FROM marcador WHERE id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, id);

            stmt.executeUpdate();

            con.close();

            resIns = true;
        } catch (SQLException | IOException e) {
            resIns = false;
            e.printStackTrace();
        }
        return resIns;
    }

}
