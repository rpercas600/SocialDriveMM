package iesmm.pmdm.socialdrivemm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import iesmm.pmdm.socialdrivemm.R;
import iesmm.pmdm.socialdrivemm.daoImpl.MarcadorImpl;
import iesmm.pmdm.socialdrivemm.model.Marcador;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayAdapter adaptador;
    private ArrayList<Marcador> marcadores = new ArrayList();

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
     * @return A new instance of fragment BlankFragment.
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

            MarcadorImpl marcImp = new MarcadorImpl();

            addItemsInListView((ArrayList) marcImp.listarMacadores());


        }
    }
    private void addItemsInListView(ArrayList marcadores) {
        //Localizar el listview en el layout
        ListView lista = getActivity().findViewById(R.id.item_number);
        marcadores.add(new Marcador("a","a","a","a","a"));
        System.out.println(marcadores);
        //Creamos adaptador de datos y vinculamos los datos que vamos a presentar en el listview
        adaptador = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, marcadores);
        lista.setAdapter(adaptador);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }
}