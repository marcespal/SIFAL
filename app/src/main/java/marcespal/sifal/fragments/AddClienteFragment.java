package marcespal.sifal.fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

import marcespal.sifal.MainActivity;
import marcespal.sifal.R;

public class AddClienteFragment extends Fragment {
    private EditText establecimiento;
    private EditText representante;
    private EditText ruc;
    private EditText provincia;
    private EditText canton;
    private EditText direccion;
    private EditText convencional;
    private EditText celular;
    private EditText email;

    private String testablecimiento;
    private String trepresentante;
    private String truc;
    private String tprovincia;
    private String tcanton;
    private String tdireccion;
    private String tconvencional;
    private String tcelular;
    private String temail;
    private String tlatitud;
    private String tlongitud;
    private Button btsave;
    private String usuario;

    public AddClienteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_add_cliente, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        establecimiento = getView().findViewById(R.id.editEstablecimiento);
        representante = getView().findViewById(R.id.editRepresentante);
        ruc = getView().findViewById(R.id.editRuc);
        provincia = getView().findViewById(R.id.editProvincia);
        canton = getView().findViewById(R.id.editCanton);
        direccion = getView().findViewById(R.id.editDireccion);
        convencional = getView().findViewById(R.id.editConvencional);
        celular = getView().findViewById(R.id.editCelular);
        email = getView().findViewById(R.id.editEmail);
        btsave = getView().findViewById(R.id.buttonGuardarClient);


        btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!establecimiento.getText().toString().isEmpty() && !representante.getText().toString().isEmpty() &&
                        !ruc.getText().toString().isEmpty() && !provincia.getText().toString().isEmpty() &&
                        !provincia.getText().toString().isEmpty() && !canton.getText().toString().isEmpty() &&
                        !direccion.getText().toString().isEmpty() && !celular.getText().toString().isEmpty() &&
                        !email.getText().toString().isEmpty()) {

                    MainActivity activity = (MainActivity) getActivity();
                    Log.e("prueba", "Solicita");
                    tlongitud = activity.getLongitud();
                    tlatitud = activity.getLatitud();
                    Log.e("prueba", "paso la recepcion");
                    //Toast.makeText(getContext(), tlongitud +", "+tlatitud, Toast.LENGTH_SHORT).show();
                    obtenerDatos();
                    new SendRequest().execute();
                    cleanerCajas();
                }else {
                    Toast.makeText(getContext(), "Ingrese todos los Items", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void obtenerDatos(){
        testablecimiento = establecimiento.getText().toString();
        trepresentante = representante.getText().toString();
        truc = ruc.getText().toString();
        tprovincia = provincia.getText().toString();
        tcanton = canton.getText().toString();
        tdireccion = direccion.getText().toString();
        tconvencional = convencional.getText().toString();
        tcelular = celular.getText().toString();
        temail = email.getText().toString();

        Log.e("prueba","Paso la el GPS");

        Log.e("prueba","Lat: "+ tlatitud+"Long: "+ tlongitud);
    }
    public void cleanerCajas(){
        establecimiento.setText("");
        representante.setText("");
        ruc.setText("");
        provincia.setText("");
        canton.setText("");
        direccion.setText("");
        convencional.setText("");
        celular.setText("");
        email.setText("");

    }


    public class SendRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try{
                //Change your web app deployed URL or u can use this for attributes (name, country)
                URL url = new URL("https://script.google.com/macros/s/AKfycbxwFcpmctCk47NkfGEA_EYTui5Q42RDYOYB3BYwBqgUOiqbtHE/exec");

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("nombre",testablecimiento);
                postDataParams.put("representante",trepresentante);
                postDataParams.put("ruc",truc);
                postDataParams.put("email",temail);
                postDataParams.put("convencional",tconvencional);
                postDataParams.put("celular",tcelular);
                postDataParams.put("provincia",tprovincia);
                postDataParams.put("canton",tcanton);
                postDataParams.put("direccion",tdireccion);
                postDataParams.put("latitud",tlatitud);
                postDataParams.put("longitud",tlongitud);
                postDataParams.put("seller",usuario);

                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000) /* milliseconds */;
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getContext(), "Cliente registrado con éxito",
                    Toast.LENGTH_LONG).show();

        }

        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while(itr.hasNext()){

                String key= itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }

    }


}
