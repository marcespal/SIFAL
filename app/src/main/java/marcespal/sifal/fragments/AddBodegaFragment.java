package marcespal.sifal.fragments;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import marcespal.sifal.MainActivity;
import marcespal.sifal.R;

import static android.content.Context.MODE_PRIVATE;


public class AddBodegaFragment extends Fragment {
    private Spinner listProduct, listInOut;
    String[] products = {"Seleccione","BARDANA día","BARDANA noche","CANCHINACUI tabletas","CANCHINACUI vino","CARANDAY ungüento",
            "CATAPLAC elíxir","CHUCUTA emulsión","CHUCUTA tabletas","DENDRITAS jarabe","HUASCARAY extracto",
            "JAPIKAY 250gr","JAPIKAY 500gr","JINAJAY tónico","KUMARA jaléa","LAMOCHITA callicida",
            "LOMVERSAN jarabe","LOMVERSAN tabletas","MASAJE CAYUGA líquido","PASION&MACA","PIEL ROJA emulsión",
            "PROSEROT tabletas","PROSEROT vino","SANAJOL 350ml","SANAJOL 500ml","SIFADERMA crema",
            "SILVESTRE jarabe","SILVESTRIN tabletas","VERBENOL fungicida","YAKASARA elíxir"};
    String[] inout = {"Seleccione","Ingreso", "Egreso"};

    private EditText fecha;
    private EditText cantidad;
    private EditText lote;

    private String tfecha;
    private String tproducto;
    private String tcantidad;
    private String tdetalle;
    private String tlote;
    private String responsable;

    private Button btSaveBod;

    private int positionPROD;
    private int positionINOUT;

    public AddBodegaFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_add_bodega, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("Credenciales",MODE_PRIVATE);
        responsable = preferences.getString("User","nulo");


        fecha = getView().findViewById(R.id.editFecha);
        cantidad = getView().findViewById(R.id.editCantidad);
        lote = getView().findViewById(R.id.editLote);
        btSaveBod = getView().findViewById(R.id.buttonGuardarBodega);

        listProduct = getView().findViewById(R.id.listProduct);
        listInOut = getView().findViewById(R.id.listInOut);
        ArrayAdapter<String> adapterProducts = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,products);
        listProduct.setAdapter(adapterProducts);
        ArrayAdapter<String> adapterInout = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,inout);
        listInOut.setAdapter(adapterInout);

        listProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionPROD = position;
                tproducto = products[position];
                if(positionPROD!=0){
                    //Toast.makeText(getContext(), tproducto, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listInOut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tdetalle = inout[position];
                positionINOUT = position;
                switch (position){
                    case 1:
                        tdetalle = inout[position];
                        break;
                    case 2:
                        tdetalle = inout[position];
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btSaveBod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (positionINOUT==0 || positionPROD==0){
                    Toast.makeText(getContext(), "Un item no ha sido seleccionado", Toast.LENGTH_SHORT).show();
                }else {
                    obtenerDatos();
                    new SendRequest().execute();
                    limpiaCajas();
                }
            }
        });
    }

    public void obtenerDatos(){
        tfecha = fecha.getText().toString();
        tcantidad = cantidad.getText().toString();
        if (positionINOUT==1){}else {
            tcantidad = "-" + tcantidad;
        }
        //Toast.makeText(getContext(), tcantidad, Toast.LENGTH_SHORT).show();
        tlote = lote.getText().toString();
    }
    public void limpiaCajas(){
        listProduct.setSelection(0);
        listInOut.setSelection(0);
        fecha.setText("");
        cantidad.setText("");
        lote.setText("");

    }

    public class SendRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try{
                //Change your web app deployed URL or u can use this for attributes (name, country)
                URL url = new URL("https://script.google.com/macros/s/AKfycbyU-m1WwTsRYR5TvUQT2_fNNB6IsK72bSmlngDDxGVt0HzgTeM/exec");

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("fecha",tfecha);
                postDataParams.put("producto",tproducto);
                postDataParams.put("cantidad",tcantidad);
                postDataParams.put("detalle",tdetalle);
                postDataParams.put("responsable",responsable);
                postDataParams.put("lote",tlote);

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
            Toast.makeText(getContext(), "Registro de bodega realizado con éxito",
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
