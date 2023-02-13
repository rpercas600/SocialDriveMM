package iesmm.pmdm.socialdrivemm.daoImpl;

import android.widget.Toast;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import iesmm.pmdm.socialdrivemm.dao.DAOUsuario;
import iesmm.pmdm.socialdrivemm.model.Usuario;
import iesmm.pmdm.socialdrivemm.utils.Conexion;

public class UsuarioImpl implements DAOUsuario {

    @Override
    public boolean checkLogin(Usuario userIn) {

        boolean flag = false;
        Connection con = null;


        String sql = "SELECT * FROM usuario";

        try {

            con = Conexion.getConnection();

            Statement sqlStatement = con.createStatement();
            ResultSet sel = sqlStatement.executeQuery(sql);

            while (sel.next()) {
                Usuario user = new Usuario(sel.getString("user")
                        , sel.getString("pass"));
                if (user.equals(userIn)) {
                    flag = true;
                    break;
                } else {
                    flag = false;
                }
            }
        } catch (SQLException | IOException e) {
            flag = false;
            System.out.println("no conexion");
            System.out.println(new RuntimeException(e));
        }
        return flag;
    }
}
