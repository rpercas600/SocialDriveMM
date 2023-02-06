package iesmm.pmdm.socialdrivemm.daoImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import iesmm.pmdm.socialdrivemm.dao.DAOUsuario;
import iesmm.pmdm.socialdrivemm.model.Usuario;
import iesmm.pmdm.socialdrivemm.utils.Conexion;

public class UsuarioImpl implements DAOUsuario {


    @Override
    public boolean checkLogin(Usuario userIn) {
        boolean flag = false;
        Connection con = null;

        String sql = "SELECT * FROM MARCADOR";

        try {
            con = Conexion.getConnection();

            Statement sqlStatement = con.createStatement();
            ResultSet sel = sqlStatement.executeQuery(sql);

            while (sel.next()) {
                Usuario user = new Usuario(sel.getString("username")
                        , sel.getString("password"));
                if (user.equals(userIn)){
                    flag = true;
                    break;
                } else {
                    flag = false;
                }
            }
        } catch (SQLException | IOException e) {
            flag = false;
            //throw new RuntimeException(e);
        }

        return flag;
    }
}
