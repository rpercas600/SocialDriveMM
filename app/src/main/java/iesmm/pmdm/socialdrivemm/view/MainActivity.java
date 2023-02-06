package iesmm.pmdm.socialdrivemm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import iesmm.pmdm.socialdrivemm.R;
import iesmm.pmdm.socialdrivemm.daoImpl.UsuarioImpl;
import iesmm.pmdm.socialdrivemm.model.Usuario;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsuarioImpl usuarioImpl = new UsuarioImpl();

        Button b = this.findViewById(R.id.boton_iniciar_sesion);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.boton_iniciar_sesion) {

                    //Comprobar lo introducido en los campos
                    String txtUser = ((TextView) findViewById(R.id.input_usuario)).getText().toString();
                    String txtPass = ((TextView) findViewById(R.id.input_contrasena)).getText().toString();

                    Usuario usr = new Usuario(txtUser, txtPass);

                    if (usuarioImpl.checkLogin(usr)) {
                        Snackbar.make(view, "Login Correcto, bienvenido " + txtUser, Snackbar.LENGTH_LONG).show();
                        //Lanzo el intent para cambiar de pantalla
                        Intent i = new Intent(getApplicationContext(), NavigationDrawer.class);
                        startActivity(i);

                    } else {
                        Snackbar.make(view, "Error en acceso, datos incorrectos", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}