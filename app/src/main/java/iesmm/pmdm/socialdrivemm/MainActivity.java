package iesmm.pmdm.socialdrivemm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = this.findViewById(R.id.boton_iniciar_sesion);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Comprobar lo introducido en los campos
                String txtCorreo = ((TextView) findViewById(R.id.input_usuario)).getText().toString();
                String txtPass = ((TextView) findViewById(R.id.input_contrasena)).getText().toString();

                if(getAccess(txtCorreo,txtPass)){
                    Snackbar.make(view,"Bien",Snackbar.LENGTH_LONG).show();
                    //Lanzo el intent para cambiar de pantalla
                   Intent i = new Intent(getApplicationContext(),NavigationDrawer.class);
                    startActivity(i);


                }else{
                    Snackbar.make(view,"Error en acceso, datos incorrectos",Snackbar.LENGTH_LONG).show();
                }

            }
        });
    }

    /**
     * Devuelve cierto si se confirma que el email y password son correctos y se localizan en el fichero
     * @param txtCorreo correo de acceso
     * @param txtPass password insertado
     * @return Devuelve cierto si son correctos y falso en caso contrario
     */
    private boolean getAccess(String txtCorreo, String txtPass) {
        String user = "rafa";
        String pass = "dammm";
        if(txtCorreo.equals(user) && txtPass.equals(pass)){
            return true;
        }else{
        return false;
        }
    }


}