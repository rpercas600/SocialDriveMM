package iesmm.pmdm.socialdrivemm.view;

import static android.content.Context.LOCATION_SERVICE;

import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import iesmm.pmdm.socialdrivemm.R;
import iesmm.pmdm.socialdrivemm.daoImpl.MarcadorImpl;
import iesmm.pmdm.socialdrivemm.model.Marcador;

public class MapsFragment extends Fragment {

    AlertDialog alert = null;
    LocationManager locationManager;
    FusedLocationProviderClient client;

    private GoogleMap mMap;
    private final OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {

            mMap = googleMap;

            locationManager = (LocationManager) requireContext().getSystemService(LOCATION_SERVICE);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                GPSUtils.gpsEnable(getActivity());
            } else {
                GPSUtils.getInstance().findDeviceLocation(getActivity());
                try {
                    LatLng ubicacionActual = new LatLng(Double.parseDouble(GPSUtils.getLatitude()), Double.parseDouble(GPSUtils.getLongitude()));
                    googleMap.addMarker(new MarkerOptions().position(ubicacionActual).title("Ubicacion actual"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacionActual));

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacionActual, 10));

                } catch (NullPointerException e) {
                    Toast.makeText(getActivity(), "Error de gps", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
    private TextView txtDes;
    private LinearLayout ln;
    private String userLogged;
    private MarcadorImpl marcador;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);

        client = LocationServices
                .getFusedLocationProviderClient(getActivity());

/*      Bundle mBundle = new Bundle();
        Bundle extras = getActivity().getIntent().getExtras();
        String foo = extras.getString("FOO");
        //mBundle = getArguments();
        //mBundle.getString("user");
        System.out.println(foo);*/

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {

                        //Geocoding de la posicion
                        Geocoder geocoder = new Geocoder(getContext());

                        //1. Obtener las posiciones respecto a la direccion marcada
                        try {
                            List<Address> direcciones = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);


                            //2. Sacar los datos
                            Address direccion = direcciones.get(0);

                            Double lat = direccion.getLatitude();
                            Double lon = direccion.getLongitude();
                            String ubi = lat + " / " + lon;
                            String via = direccion.getAddressLine(0);


                            //alert dialog que preguntar?? descripci??n
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                            LayoutInflater inflater = requireActivity().getLayoutInflater();

                            builder.setView(inflater.inflate(R.layout.marcador_alertdialog, null))
                                    .setTitle(via);

                            builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                public void onClick(DialogInterface dialog, int id) {

                                    String des = String.valueOf(txtDes.getText());

                                    Marker mark = mMap.addMarker(
                                            new MarkerOptions().position(new LatLng(lat, lon))
                                                    .title(des));

                                    mark.showInfoWindow();

                                    Marcador markOb = new Marcador(
                                            String.valueOf(LocalDateTime.now()),
                                            ubi, des, via, userLogged);

                                    marcador.insert(markOb);
                                }
                            });

                            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });

                            AlertDialog dialog = builder.create();
                            dialog.show();

                        } catch (IOException e) {
                            e.printStackTrace();

                            Toast.makeText(getActivity(), "No se ha indicado una posicion correcta", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtDes = view.findViewById(R.id.descripcion);


        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    class Async extends AsyncTask<Void, Void, Void> {

        String records = "", error = "";

        @Override

        protected Void doInBackground(Void... voids) {

            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.5:3306/socialdrivemm", "root", "3081");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM test");

                while (resultSet.next()) {
                    records += resultSet.getString(1) + " " + resultSet.getString(2) + "\n";
                }
            } catch (Exception e) {
                error = e.toString();
            }
            return null;
        }

        @Override

        protected void onPostExecute(Void aVoid) {

           /* text.setText(records);

            if(error != "")

                errorText.setText(error);

            super.onPostExecute(aVoid);
*/
        }


        private void actualizarMarcadores(){

            for (Marcador obj: marcador.listarMacadores()) {

                String[] ubi = obj.getUbi().split("/");

                Marker mark = mMap.addMarker(
                        new MarkerOptions().position(new LatLng(Double.valueOf(ubi[0]), Double.valueOf(ubi[1])))
                                .title(obj.getDescripcion()));

            }
        }

    }
}
