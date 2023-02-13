package iesmm.pmdm.socialdrivemm.view;

import static android.content.Context.LOCATION_SERVICE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


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
import java.util.Objects;

import iesmm.pmdm.socialdrivemm.R;
import iesmm.pmdm.socialdrivemm.dao.DAOMarcador;
import iesmm.pmdm.socialdrivemm.daoImpl.MarcadorImpl;
import iesmm.pmdm.socialdrivemm.model.Marcador;

public class MapsFragment extends Fragment implements GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private TextView txtDes;
    private LinearLayout ln;
    private String userLogged;
    AlertDialog alert = null;
    LocationManager locationManager;

    private MarcadorImpl marcador;

    FusedLocationProviderClient client;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            mMap = googleMap;

            locationManager = (LocationManager) requireContext().getSystemService(LOCATION_SERVICE);
            if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
                GPSUtils.gpsEnable(getActivity());
            }else{
            GPSUtils.getInstance().findDeviceLocation(getActivity());
            try {
                LatLng ubicacionActual = new LatLng(Double.parseDouble(GPSUtils.getLatitude()),Double.parseDouble(GPSUtils.getLongitude()));
                googleMap.addMarker(new MarkerOptions().position(ubicacionActual).title("Ubicacion actual"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacionActual));
            }catch (NullPointerException e){

            }
            }


        }

    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        client = LocationServices
                .getFusedLocationProviderClient(
                        getActivity());

        return inflater.inflate(R.layout.fragment_maps, container, false);
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


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMapClick(@NonNull LatLng latLng) {

        //Geocoding de la posicion
        Geocoder geocoder = new Geocoder(this.getContext());

        //1. Obtener las posiciones respecto a la direccion marcada
        try {
            List<Address> direcciones = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);


            //2. Sacar los datos
            Address direccion = direcciones.get(0);

            Double lat = direccion.getLatitude();
            Double lon = direccion.getLongitude();
            String ubi = lat + " / " + lon;
            String via = direccion.getAddressLine(0);


            //alert dialog que preguntará descripción
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            LayoutInflater inflater = requireActivity().getLayoutInflater();

            builder.setView(inflater.inflate(R.layout.marcador_alertdialog, null))
                .setTitle(via);


            builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    String des = String.valueOf(txtDes.getText());

                    Marker mark = mMap.addMarker(
                            new MarkerOptions().position(new LatLng(lat, lon))
                                    .title(des));

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


        } catch (IOException e) {
            e.printStackTrace();

            Toast.makeText(this.getContext(), "No se ha indicado una posicion correcta", Toast.LENGTH_SHORT).show();
        }
    }
    class Async extends AsyncTask<Void, Void, Void> {



        String records = "",error="";

        @Override

        protected Void doInBackground(Void... voids) {

            try

            {

                Class.forName("com.mysql.jdbc.Driver");

                Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.5:3306/socialdrivemm", "root", "3081");

                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM test");

                while(resultSet.next()) {

                    records += resultSet.getString(1) + " " + resultSet.getString(2) + "\n";

                }

            }

            catch(Exception e)

            {

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


    }
}
