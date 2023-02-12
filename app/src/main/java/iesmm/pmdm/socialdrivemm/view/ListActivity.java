package iesmm.pmdm.socialdrivemm.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Comparator;

import iesmm.pmdm.socialdrivemm.R;
import iesmm.pmdm.socialdrivemm.model.Marcador;

public class ListActivity extends AppCompatActivity {

    private ArrayAdapter adaptador;
    //Creo los arrays de cadenas para insertarlos en el listview
    //y el de contactos para poder ordenarlo
    private ArrayList cadenas = new ArrayList();
    private ArrayList<Marcador> marcadores = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //leerFichero();
        cadenas.sort(Comparator.naturalOrder());
        loadData();
        //System.out.println(contactos);
        //ordeno el array con el compareTo que he creado en su propia clase para ordenar por nombre
        //contactos.sort(Contacto::compareTo);
        //Deberíamos confirmar el permiso de llamadas al principio en oncreate
        //porque si lo confirmamos antes de darle al botón llamar no llama directamente y habría
        //que repetir la acción
    }

    private void loadData() {
        //Cargar los datos al listview
        //addItemsInListView(cadenas);
    }

    /*private void leerFichero() {
        //Obtengo la ruta del directorio inicial del punto de montaje de memoria externa
       // File file = new File(getExternalFilesDir(null), FILENAME);
        try {
           // FileInputStream fIn = new FileInputStream(file);
            //InputStreamReader archivo = new InputStreamReader(fIn);
            //BufferedReader br = new BufferedReader(archivo);
            //String linea = br.readLine();
            /*while (linea != null) {
                //Voy leyendo el archivo creando un Contacto por cada línea leida,
                // separando el csv según su información
                //en este caso -> nombre,telefono,email
                //y los añado al array
                String[] contacto = linea.split(";");
                contactos.add(new Contacto(contacto[0], Integer.parseInt(contacto[1]), contacto[2]));
                cadenas.add(contacto[0]);
                linea = br.readLine();
            }
            br.close();
            archivo.close();

        } catch (IOException e) {
            Toast.makeText(this, "No se pudo leer", Toast.LENGTH_SHORT).show();
        }
    }*/


    /*private void addItemsInListView(ArrayList cadenas) {
        //Localizar el listview en el layout
        ListView lista = this.findViewById(R.id.container);

        //Creamos adaptador de datos y vinculamos los datos que vamos a presentar en el listview
        adaptador = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, cadenas);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Creo el alertdialog con los botones necesarios del ejercicio para cancelar,
                //enviar whatsapp y llamar.
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Acciones");
                builder.setMessage("¿Que desea hacer con este contacto?");

                builder.setPositiveButton("Llamar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        llamar(contactos.get(i).getTelefono());
                    }
                });
                builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("WhatsApp", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mandarWhatsApp();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }*/

}
