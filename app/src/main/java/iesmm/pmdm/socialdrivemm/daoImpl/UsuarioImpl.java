package iesmm.pmdm.socialdrivemm.daoImpl;

import android.widget.Toast;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import iesmm.pmdm.socialdrivemm.dao.DAOUsuario;
import iesmm.pmdm.socialdrivemm.model.Usuario;
import iesmm.pmdm.socialdrivemm.utils.Conexion;

public class UsuarioImpl implements DAOUsuario {


    @Override
    public boolean checkLogin(Usuario userIn) {
        /*boolean flag = false;
        Connection con = null;


        String sql = "SELECT * FROM usuario";

        try {

            con = Conexion.getConnection();

            Statement sqlStatement = con.createStatement();
            ResultSet sel = sqlStatement.executeQuery(sql);

            while (sel.next()) {
                Usuario user = new Usuario(sel.getString("user")
                        , sel.getString("pass"));
                if (user.equals(userIn)){
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

        return flag;*/
        boolean flag = false;
        try

        {

            //Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.1.5:3306/socialdrivemm", "root", "3081");

            Statement statement = connection.createStatement();

            ResultSet sel = statement.executeQuery("SELECT * FROM usuario");

            while(sel.next()) {

                Usuario user = new Usuario(sel.getString("user")
                        , sel.getString("pass"));
                if (user.equals(userIn)){
                    flag = true;
                    break;
                } else {
                    flag = false;
                }

            }

        }

        catch(Exception e)

        {

            String error = e.toString();

        }
        return flag;
    }
}
