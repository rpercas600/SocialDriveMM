package iesmm.pmdm.socialdrivemm.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import iesmm.pmdm.socialdrivemm.R;
import iesmm.pmdm.socialdrivemm.model.Marcador;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    private ArrayAdapter adaptador;
    //Creo los arrays de cadenas para insertarlos en el listview
    //y el de contactos para poder ordenarlo
    private ArrayList cadenas = new ArrayList();
    private ArrayList<Marcador> marcadores = new ArrayList();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListView lista;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    private void loadData() {
        //Cargar los datos al listview
        addItemsInListView(cadenas);
    }

    private void leerFichero() {
        //Obtengo la ruta del directorio inicial del punto de montaje de memoria externa
        /* metemos conexion y llamamos base de datos para tener lo que pidamos
        try {

            String linea = br.readLine();
            while (linea != null) {
                //Voy leyendo el archivo creando un Contacto por cada línea leida,
                // separando el csv según su información
                //en este caso -> nombre,telefono,email
                //y los añado al array
                String[] contacto = linea.split(";");
                marcadores.add(new Contacto(contacto[0], Integer.parseInt(contacto[1]), contacto[2]));
                cadenas.add(contacto[0]);
                linea = br.readLine();
            }

        } catch (IOException e) {
            Toast.makeText(this, "No se pudo leer", Toast.LENGTH_SHORT).show();
        }*/
    }


    private void addItemsInListView(ArrayList cadenas) {
     /*   //Localizar el listview en el layout
        ListView lista = this.findViewById(R.id.lista);

        //Creamos adaptador de datos y vinculamos los datos que vamos a presentar en el listview
        adaptador = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, cadenas);
        lista.setAdapter(adaptador);
*/

    }
}