package marcespal.sifal.fragments;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import marcespal.sifal.MainActivity;
import marcespal.sifal.R;
import marcespal.sifal.models.Clientes;

import static android.content.Context.MODE_PRIVATE;


public class AddVentaFragment extends Fragment{

    String[] Mproductos = {
            "BARDANA día","BARDANA noche","CANCHINACUI tabletas","CANCHINACUI vino","CARANDAY ungüento",
            "CATAPLAC elíxir","CHUCUTA emulsión","CHUCUTA tabletas","DENDRITAS jarabe","HUASCARAY extracto",
            "JAPIKAY 250gr","JAPIKAY 500gr","JINAJAY tónico","KUMARA jaléa","LAMOCHITA callicida",
            "LOMVERSAN jarabe","LOMVERSAN tabletas","MASAJE CAYUGA líquido","PASION&MACA","PIEL ROJA emulsión",
            "PROSEROT tabletas","PROSEROT vino","SANAJOL 350ml","SANAJOL 500ml","SIFADERMA crema",
            "SILVESTRE jarabe","SILVESTRIN tabletas","VERBENOL fungicida","YAKASARA elíxir"};


    Double[] Mpreferencial = {
            3.50,3.50,6.50,7.50,1.40,
            6.00,6.00,4.00,6.00,6.00,
            4.00,6.00,6.00,2.00,0.70,
            2.00,17.50,2.50,6.00,6.00,
            6.50,7.50,1.25,1.80,0.70,
            2.00,12.00,2.50,6.00};

    Double[] Mdistribuidor = {
            4.00,4.00,8.50,9.00,1.80,
            7.00,7.00,4.65,7.00,7.00,
            4.65,7.00,7.00,2.40,1.00,
            2.50,23.00,3.00,7.00,7.00,
            8.50,9.00,1.40,2.00,0.90,
            2.20,16.00,3.00,7.00};

    Double[] Mfarmacia= {
            4.50,4.50,8.70,11.00,2.10,
            8.00,8.00,5.35,8.00,8.00,
            5.35,8.00,8.00,2.68,1.50,
            3.00,28.00,3.50,8.00,8.00,
            8.70,11.00,1.80,2.50,1.50,
            2.68,16.80,3.40,8.00};

    Double[] matriz, matCant, matTotal;
    int SelectListClientes, AvanceEnvio=0;

    ArrayList<Clientes> dataset;
    private static final String TAG = "SIFAL";
    private ProgressBar progressBar;
    private String url1 = "https://script.google.com/macros/s/AKfycbxwFcpmctCk47NkfGEA_EYTui5Q42RDYOYB3BYwBqgUOiqbtHE/exec";



    private TextView tvNombrep0;
    private TextView tvCostU0;
    private TextView tvCostT0;
    private TextView tvNombrep1;
    private TextView tvCostU1;
    private TextView tvCostT1;
    private TextView tvNombrep2;
    private TextView tvCostU2;
    private TextView tvCostT2;
    private TextView tvNombrep3;
    private TextView tvCostU3;
    private TextView tvCostT3;
    private TextView tvNombrep4;
    private TextView tvCostU4;
    private TextView tvCostT4;
    private TextView tvNombrep5;
    private TextView tvCostU5;
    private TextView tvCostT5;
    private TextView tvNombrep6;
    private TextView tvCostU6;
    private TextView tvCostT6;
    private TextView tvNombrep7;
    private TextView tvCostU7;
    private TextView tvCostT7;
    private TextView tvNombrep8;
    private TextView tvCostU8;
    private TextView tvCostT8;
    private TextView tvNombrep9;
    private TextView tvCostU9;
    private TextView tvCostT9;
    private TextView tvNombrep10;
    private TextView tvCostU10;
    private TextView tvCostT10;
    private TextView tvNombrep11;
    private TextView tvCostU11;
    private TextView tvCostT11;
    private TextView tvNombrep12;
    private TextView tvCostU12;
    private TextView tvCostT12;
    private TextView tvNombrep13;
    private TextView tvCostU13;
    private TextView tvCostT13;
    private TextView tvNombrep14;
    private TextView tvCostU14;
    private TextView tvCostT14;
    private TextView tvNombrep15;
    private TextView tvCostU15;
    private TextView tvCostT15;
    private TextView tvNombrep16;
    private TextView tvCostU16;
    private TextView tvCostT16;
    private TextView tvNombrep17;
    private TextView tvCostU17;
    private TextView tvCostT17;
    private TextView tvNombrep18;
    private TextView tvCostU18;
    private TextView tvCostT18;
    private TextView tvNombrep19;
    private TextView tvCostU19;
    private TextView tvCostT19;
    private TextView tvNombrep20;
    private TextView tvCostU20;
    private TextView tvCostT20;
    private TextView tvNombrep21;
    private TextView tvCostU21;
    private TextView tvCostT21;
    private TextView tvNombrep22;
    private TextView tvCostU22;
    private TextView tvCostT22;
    private TextView tvNombrep23;
    private TextView tvCostU23;
    private TextView tvCostT23;
    private TextView tvNombrep24;
    private TextView tvCostU24;
    private TextView tvCostT24;
    private TextView tvNombrep25;
    private TextView tvCostU25;
    private TextView tvCostT25;
    private TextView tvNombrep26;
    private TextView tvCostU26;
    private TextView tvCostT26;
    private TextView tvNombrep27;
    private TextView tvCostU27;
    private TextView tvCostT27;
    private TextView tvNombrep28;
    private TextView tvCostU28;
    private TextView tvCostT28;

    private TextView tvTotal;


    private EditText etCant0;
    private EditText etCant1;
    private EditText etCant2;
    private EditText etCant3;
    private EditText etCant4;
    private EditText etCant5;
    private EditText etCant6;
    private EditText etCant7;
    private EditText etCant8;
    private EditText etCant9;
    private EditText etCant10;
    private EditText etCant11;
    private EditText etCant12;
    private EditText etCant13;
    private EditText etCant14;
    private EditText etCant15;
    private EditText etCant16;
    private EditText etCant17;
    private EditText etCant18;
    private EditText etCant19;
    private EditText etCant20;
    private EditText etCant21;
    private EditText etCant22;
    private EditText etCant23;
    private EditText etCant24;
    private EditText etCant25;
    private EditText etCant26;
    private EditText etCant27;
    private EditText etCant28;

    private EditText etObserVenta;
    private EditText etFactura;

    private Spinner listCliente;

    private Button btCotizar, btVender;

    private String FacturaVal, ObserVal;


    public AddVentaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_venta, container, false);
    }
public String[] vClientes;

    public String responsable;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        matTotal = new Double[29];
        matCant = new Double[29];

        listCliente = getView().findViewById(R.id.listCliente);

        etObserVenta = getView().findViewById(R.id.etObserVenta);
        etFactura = getView().findViewById(R.id.etFactura);

        tvNombrep0=getView().findViewById(R.id.tvNameP0);
        etCant0=getView().findViewById(R.id.etCant0);
        tvCostU0=getView().findViewById(R.id.tvCostU0);
        tvCostT0=getView().findViewById(R.id.tvCostT0);
        tvNombrep1=getView().findViewById(R.id.tvNameP1);
        etCant1=getView().findViewById(R.id.etCant1);
        tvCostU1=getView().findViewById(R.id.tvCostU1);
        tvCostT1=getView().findViewById(R.id.tvCostT1);
        tvNombrep2=getView().findViewById(R.id.tvNameP2);
        etCant2=getView().findViewById(R.id.etCant2);
        tvCostU2=getView().findViewById(R.id.tvCostU2);
        tvCostT2=getView().findViewById(R.id.tvCostT2);
        tvNombrep3=getView().findViewById(R.id.tvNameP3);
        etCant3=getView().findViewById(R.id.etCant3);
        tvCostU3=getView().findViewById(R.id.tvCostU3);
        tvCostT3=getView().findViewById(R.id.tvCostT3);
        tvNombrep4=getView().findViewById(R.id.tvNameP4);
        etCant4=getView().findViewById(R.id.etCant4);
        tvCostU4=getView().findViewById(R.id.tvCostU4);
        tvCostT4=getView().findViewById(R.id.tvCostT4);
        tvNombrep5=getView().findViewById(R.id.tvNameP5);
        etCant5=getView().findViewById(R.id.etCant5);
        tvCostU5=getView().findViewById(R.id.tvCostU5);
        tvCostT5=getView().findViewById(R.id.tvCostT5);
        tvNombrep6=getView().findViewById(R.id.tvNameP6);
        etCant6=getView().findViewById(R.id.etCant6);
        tvCostU6=getView().findViewById(R.id.tvCostU6);
        tvCostT6=getView().findViewById(R.id.tvCostT6);
        tvNombrep7=getView().findViewById(R.id.tvNameP7);
        etCant7=getView().findViewById(R.id.etCant7);
        tvCostU7=getView().findViewById(R.id.tvCostU7);
        tvCostT7=getView().findViewById(R.id.tvCostT7);
        tvNombrep8=getView().findViewById(R.id.tvNameP8);
        etCant8=getView().findViewById(R.id.etCant8);
        tvCostU8=getView().findViewById(R.id.tvCostU8);
        tvCostT8=getView().findViewById(R.id.tvCostT8);
        tvNombrep9=getView().findViewById(R.id.tvNameP9);
        etCant9=getView().findViewById(R.id.etCant9);
        tvCostU9=getView().findViewById(R.id.tvCostU9);
        tvCostT9=getView().findViewById(R.id.tvCostT9);
        tvNombrep10=getView().findViewById(R.id.tvNameP10);
        etCant10=getView().findViewById(R.id.etCant10);
        tvCostU10=getView().findViewById(R.id.tvCostU10);
        tvCostT10=getView().findViewById(R.id.tvCostT10);
        tvNombrep11=getView().findViewById(R.id.tvNameP11);
        etCant11=getView().findViewById(R.id.etCant11);
        tvCostU11=getView().findViewById(R.id.tvCostU11);
        tvCostT11=getView().findViewById(R.id.tvCostT11);
        tvNombrep12=getView().findViewById(R.id.tvNameP12);
        etCant12=getView().findViewById(R.id.etCant12);
        tvCostU12=getView().findViewById(R.id.tvCostU12);
        tvCostT12=getView().findViewById(R.id.tvCostT12);
        tvNombrep13=getView().findViewById(R.id.tvNameP13);
        etCant13=getView().findViewById(R.id.etCant13);
        tvCostU13=getView().findViewById(R.id.tvCostU13);
        tvCostT13=getView().findViewById(R.id.tvCostT13);
        tvNombrep14=getView().findViewById(R.id.tvNameP14);
        etCant14=getView().findViewById(R.id.etCant14);
        tvCostU14=getView().findViewById(R.id.tvCostU14);
        tvCostT14=getView().findViewById(R.id.tvCostT14);
        tvNombrep15=getView().findViewById(R.id.tvNameP15);
        etCant15=getView().findViewById(R.id.etCant15);
        tvCostU15=getView().findViewById(R.id.tvCostU15);
        tvCostT15=getView().findViewById(R.id.tvCostT15);
        tvNombrep16=getView().findViewById(R.id.tvNameP16);
        etCant16=getView().findViewById(R.id.etCant16);
        tvCostU16=getView().findViewById(R.id.tvCostU16);
        tvCostT16=getView().findViewById(R.id.tvCostT16);
        tvNombrep17=getView().findViewById(R.id.tvNameP17);
        etCant17=getView().findViewById(R.id.etCant17);
        tvCostU17=getView().findViewById(R.id.tvCostU17);
        tvCostT17=getView().findViewById(R.id.tvCostT17);
        tvNombrep18=getView().findViewById(R.id.tvNameP18);
        etCant18=getView().findViewById(R.id.etCant18);
        tvCostU18=getView().findViewById(R.id.tvCostU18);
        tvCostT18=getView().findViewById(R.id.tvCostT18);
        tvNombrep19=getView().findViewById(R.id.tvNameP19);
        etCant19=getView().findViewById(R.id.etCant19);
        tvCostU19=getView().findViewById(R.id.tvCostU19);
        tvCostT19=getView().findViewById(R.id.tvCostT19);
        tvNombrep20=getView().findViewById(R.id.tvNameP20);
        etCant20=getView().findViewById(R.id.etCant20);
        tvCostU20=getView().findViewById(R.id.tvCostU20);
        tvCostT20=getView().findViewById(R.id.tvCostT20);
        tvNombrep21=getView().findViewById(R.id.tvNameP21);
        etCant21=getView().findViewById(R.id.etCant21);
        tvCostU21=getView().findViewById(R.id.tvCostU21);
        tvCostT21=getView().findViewById(R.id.tvCostT21);
        tvNombrep22=getView().findViewById(R.id.tvNameP22);
        etCant22=getView().findViewById(R.id.etCant22);
        tvCostU22=getView().findViewById(R.id.tvCostU22);
        tvCostT22=getView().findViewById(R.id.tvCostT22);
        tvNombrep23=getView().findViewById(R.id.tvNameP23);
        etCant23=getView().findViewById(R.id.etCant23);
        tvCostU23=getView().findViewById(R.id.tvCostU23);
        tvCostT23=getView().findViewById(R.id.tvCostT23);
        tvNombrep24=getView().findViewById(R.id.tvNameP24);
        etCant24=getView().findViewById(R.id.etCant24);
        tvCostU24=getView().findViewById(R.id.tvCostU24);
        tvCostT24=getView().findViewById(R.id.tvCostT24);
        tvNombrep25=getView().findViewById(R.id.tvNameP25);
        etCant25=getView().findViewById(R.id.etCant25);
        tvCostU25=getView().findViewById(R.id.tvCostU25);
        tvCostT25=getView().findViewById(R.id.tvCostT25);
        tvNombrep26=getView().findViewById(R.id.tvNameP26);
        etCant26=getView().findViewById(R.id.etCant26);
        tvCostU26=getView().findViewById(R.id.tvCostU26);
        tvCostT26=getView().findViewById(R.id.tvCostT26);
        tvNombrep27=getView().findViewById(R.id.tvNameP27);
        etCant27=getView().findViewById(R.id.etCant27);
        tvCostU27=getView().findViewById(R.id.tvCostU27);
        tvCostT27=getView().findViewById(R.id.tvCostT27);
        tvNombrep28=getView().findViewById(R.id.tvNameP28);
        etCant28=getView().findViewById(R.id.etCant28);
        tvCostU28=getView().findViewById(R.id.tvCostU28);
        tvCostT28=getView().findViewById(R.id.tvCostT28);

        tvTotal = getView().findViewById(R.id.tvTotal);

        btCotizar = getView().findViewById(R.id.btCotizar);
        btVender = getView().findViewById(R.id.btVender);

        progressBar =getView().findViewById(R.id.Progreso);
        progressBar.setVisibility(View.VISIBLE);

        RequestQueue queue = Volley.newRequestQueue(getContext());

        progressBar.setVisibility(View.VISIBLE);

        JsonArrayRequest req = new JsonArrayRequest(url1, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.e(TAG, response.toString());
                dataset = new ArrayList<Clientes>();
                vClientes = new String[response.length()];
                vClientes = parser(response);
                Toast.makeText(getContext(), "Lista de clientes habilitada", Toast.LENGTH_SHORT).show();
                ArrayAdapter<String> adapterCliente = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, vClientes);
                listCliente.setAdapter(adapterCliente);
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error al descargar la lista de clientes", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(req);


        int j = matTotal.length;
        int i=0;
        while (i <= j-1){
            matTotal[i] = 0.00;
            i++;
        }

        setNombreProducts();
        progressBar.setVisibility(View.GONE);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("Credenciales",MODE_PRIVATE);
        responsable = preferences.getString("User","nulo");

        listCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    SelectListClientes=0;
                }else {
                    SelectListClientes=position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        etCant0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {

                if (!etCant0.getText().toString().isEmpty()){
                    matCant[0] = Double.valueOf(etCant0.getText().toString());
                    if(matCant[0]<300) {
                        tvCostU0.setText(String.valueOf(Mfarmacia[0]));
                        Double aux = matCant[0] * Mfarmacia[0];
                        matTotal[0] = aux;
                        tvCostT0.setText(aux.toString());
                    }else {
                        Double aux = matCant[0] * Mdistribuidor[0];
                        tvCostU0.setText(String.valueOf(Mdistribuidor[0]));
                        matTotal[0] = aux;
                        tvCostT0.setText(aux.toString());
                    }
                }else{
                    matCant[0] = 0.00;
                    matTotal[0] = 0.00;
                    tvCostT0.setText(matTotal[0].toString());
                }
            }
        });
        etCant1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant1.getText().toString().isEmpty()){
                    matCant[1] = Double.valueOf(etCant1.getText().toString());
                    if(matCant[1]<300) {
                        tvCostU1.setText(String.valueOf(Mfarmacia[1]));
                        Double aux = matCant[1] * Mfarmacia[1];
                        matTotal[1] = aux;
                        tvCostT1.setText(aux.toString());
                    }else {
                        Double aux = matCant[1] * Mdistribuidor[1];
                        tvCostU1.setText(String.valueOf(Mdistribuidor[1]));
                        matTotal[1] = aux;
                        tvCostT1.setText(aux.toString());
                    }
                }else{
                    matCant[1] = 0.00;
                    matTotal[1] = 0.00;
                    tvCostT1.setText(matTotal[1].toString());
                }
            }
        });
        etCant2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant2.getText().toString().isEmpty()){
                    matCant[2] = Double.valueOf(etCant2.getText().toString());
                    if(matCant[2]<300) {
                        tvCostU2.setText(String.valueOf(Mfarmacia[2]));
                        Double aux = matCant[2] * Mfarmacia[2];
                        matTotal[2] = aux;
                        tvCostT2.setText(aux.toString());
                    }else {
                        Double aux = matCant[2] * Mdistribuidor[2];
                        tvCostU2.setText(String.valueOf(Mdistribuidor[2]));
                        matTotal[2] = aux;
                        tvCostT2.setText(aux.toString());
                    }
                }else{
                    matCant[2] = 0.00;
                    matTotal[2] = 0.00;
                    tvCostT2.setText(matTotal[2].toString());
                }
            }
        });

        etCant3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant3.getText().toString().isEmpty()){
                    matCant[3] = Double.valueOf(etCant3.getText().toString());
                    if(matCant[3]<300) {
                        tvCostU3.setText(String.valueOf(Mfarmacia[3]));
                        Double aux = matCant[3] * Mfarmacia[3];
                        matTotal[3] = aux;
                        tvCostT3.setText(aux.toString());
                    }else {
                        Double aux = matCant[3] * Mdistribuidor[3];
                        tvCostU3.setText(String.valueOf(Mdistribuidor[3]));
                        matTotal[3] = aux;
                        tvCostT3.setText(aux.toString());
                    }
                }else{
                    matCant[3] = 0.00;
                    matTotal[3] = 0.00;
                    tvCostT3.setText(matTotal[3].toString());
                }
            }
        });
        etCant4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant4.getText().toString().isEmpty()){
                    matCant[4] = Double.valueOf(etCant4.getText().toString());
                    if(matCant[4]<300) {
                        tvCostU4.setText(String.valueOf(Mfarmacia[4]));
                        Double aux = matCant[4] * Mfarmacia[4];
                        matTotal[4] = aux;
                        tvCostT4.setText(aux.toString());
                    }else {
                        Double aux = matCant[4] * Mdistribuidor[4];
                        tvCostU4.setText(String.valueOf(Mdistribuidor[4]));
                        matTotal[4] = aux;
                        tvCostT4.setText(aux.toString());
                    }
                }else{
                    matCant[4] = 0.00;
                    matTotal[4] = 0.00;
                    tvCostT4.setText(matTotal[4].toString());
                }
            }
        });
        etCant5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant5.getText().toString().isEmpty()){
                    matCant[5] = Double.valueOf(etCant5.getText().toString());
                    if(matCant[5]<300) {
                        tvCostU5.setText(String.valueOf(Mfarmacia[5]));
                        Double aux = matCant[5] * Mfarmacia[5];
                        matTotal[5] = aux;
                        tvCostT5.setText(aux.toString());
                    }else {
                        Double aux = matCant[5] * Mdistribuidor[5];
                        tvCostU5.setText(String.valueOf(Mdistribuidor[5]));
                        matTotal[5] = aux;
                        tvCostT5.setText(aux.toString());
                    }
                }else{
                    matCant[5] = 0.00;
                    matTotal[5] = 0.00;
                    tvCostT5.setText(matTotal[5].toString());
                }
            }
        });
        etCant6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant6.getText().toString().isEmpty()){
                    matCant[6] = Double.valueOf(etCant6.getText().toString());
                    if(matCant[6]<300) {
                        tvCostU6.setText(String.valueOf(Mfarmacia[6]));
                        Double aux = matCant[6] * Mfarmacia[6];
                        matTotal[6] = aux;
                        tvCostT6.setText(aux.toString());
                    }else {
                        Double aux = matCant[6] * Mdistribuidor[6];
                        tvCostU6.setText(String.valueOf(Mdistribuidor[6]));
                        matTotal[6] = aux;
                        tvCostT6.setText(aux.toString());
                    }
                }else{
                    matCant[6] = 0.00;
                    matTotal[6] = 0.00;
                    tvCostT6.setText(matTotal[6].toString());
                }
            }
        });
        etCant7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant7.getText().toString().isEmpty()){
                    matCant[7] = Double.valueOf(etCant7.getText().toString());
                    if(matCant[7]<300) {
                        tvCostU7.setText(String.valueOf(Mfarmacia[7]));
                        Double aux = matCant[7] * Mfarmacia[7];
                        matTotal[7] = aux;
                        tvCostT7.setText(aux.toString());
                    }else {
                        Double aux = matCant[7] * Mdistribuidor[7];
                        tvCostU7.setText(String.valueOf(Mdistribuidor[7]));
                        matTotal[7] = aux;
                        tvCostT7.setText(aux.toString());
                    }
                }else{
                    matCant[7] = 0.00;
                    matTotal[7] = 0.00;
                    tvCostT7.setText(matTotal[7].toString());
                }
            }
        });
        etCant8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant8.getText().toString().isEmpty()){
                    matCant[8] = Double.valueOf(etCant8.getText().toString());
                    if(matCant[8]<300) {
                        tvCostU8.setText(String.valueOf(Mfarmacia[8]));
                        Double aux = matCant[8] * Mfarmacia[8];
                        matTotal[8] = aux;
                        tvCostT8.setText(aux.toString());
                    }else {
                        Double aux = matCant[8] * Mdistribuidor[8];
                        tvCostU8.setText(String.valueOf(Mdistribuidor[8]));
                        matTotal[8] = aux;
                        tvCostT8.setText(aux.toString());
                    }
                }else{
                    matCant[8] = 0.00;
                    matTotal[8] = 0.00;
                    tvCostT8.setText(matTotal[8].toString());
                }
            }
        });
        etCant9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant9.getText().toString().isEmpty()){
                    matCant[9] = Double.valueOf(etCant9.getText().toString());
                    if(matCant[9]<300) {
                        tvCostU9.setText(String.valueOf(Mfarmacia[9]));
                        Double aux = matCant[9] * Mfarmacia[9];
                        matTotal[9] = aux;
                        tvCostT9.setText(aux.toString());
                    }else {
                        Double aux = matCant[9] * Mdistribuidor[9];
                        tvCostU9.setText(String.valueOf(Mdistribuidor[9]));
                        matTotal[9] = aux;
                        tvCostT9.setText(aux.toString());
                    }
                }else{
                    matCant[9] = 0.00;
                    matTotal[9] = 0.00;
                    tvCostT9.setText(matTotal[9].toString());
                }
            }
        });
        etCant10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant10.getText().toString().isEmpty()){
                    matCant[10] = Double.valueOf(etCant10.getText().toString());
                    if(matCant[10]<300) {
                        tvCostU10.setText(String.valueOf(Mfarmacia[10]));
                        Double aux = matCant[10] * Mfarmacia[10];
                        matTotal[10] = aux;
                        tvCostT10.setText(aux.toString());
                    }else {
                        Double aux = matCant[10] * Mdistribuidor[10];
                        tvCostU10.setText(String.valueOf(Mdistribuidor[10]));
                        matTotal[10] = aux;
                        tvCostT10.setText(aux.toString());
                    }
                }else{
                    matCant[10] = 0.00;
                    matTotal[10] = 0.00;
                    tvCostT10.setText(matTotal[10].toString());
                }
            }
        });
        etCant11.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant11.getText().toString().isEmpty()){
                    matCant[11] = Double.valueOf(etCant11.getText().toString());
                    if(matCant[11]<300) {
                        tvCostU11.setText(String.valueOf(Mfarmacia[11]));
                        Double aux = matCant[11] * Mfarmacia[11];
                        matTotal[11] = aux;
                        tvCostT11.setText(aux.toString());
                    }else {
                        Double aux = matCant[11] * Mdistribuidor[11];
                        tvCostU11.setText(String.valueOf(Mdistribuidor[11]));
                        matTotal[11] = aux;
                        tvCostT11.setText(aux.toString());
                    }
                }else{
                    matCant[11] = 0.00;
                    matTotal[11] = 0.00;
                    tvCostT11.setText(matTotal[11].toString());
                }
            }
        });
        etCant12.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant12.getText().toString().isEmpty()){
                    matCant[12] = Double.valueOf(etCant12.getText().toString());
                    if(matCant[12]<300) {
                        tvCostU12.setText(String.valueOf(Mfarmacia[12]));
                        Double aux = matCant[12] * Mfarmacia[12];
                        matTotal[12] = aux;
                        tvCostT12.setText(aux.toString());
                    }else {
                        Double aux = matCant[12] * Mdistribuidor[12];
                        tvCostU12.setText(String.valueOf(Mdistribuidor[12]));
                        matTotal[12] = aux;
                        tvCostT12.setText(aux.toString());
                    }
                }else{
                    matCant[12] = 0.00;
                    matTotal[12] = 0.00;
                    tvCostT12.setText(matTotal[12].toString());
                }
            }
        });
        etCant13.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant13.getText().toString().isEmpty()){
                    matCant[13] = Double.valueOf(etCant13.getText().toString());
                    if(matCant[13]<300) {
                        tvCostU13.setText(String.valueOf(Mfarmacia[13]));
                        Double aux = matCant[13] * Mfarmacia[13];
                        matTotal[13] = aux;
                        tvCostT13.setText(aux.toString());
                    }else {
                        Double aux = matCant[13] * Mdistribuidor[13];
                        tvCostU13.setText(String.valueOf(Mdistribuidor[13]));
                        matTotal[13] = aux;
                        tvCostT13.setText(aux.toString());
                    }
                }else{
                    matCant[13] = 0.00;
                    matTotal[13] = 0.00;
                    tvCostT0.setText(matTotal[13].toString());
                }
            }
        });
        etCant14.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant14.getText().toString().isEmpty()){
                    matCant[14] = Double.valueOf(etCant14.getText().toString());
                    if(matCant[14]<300) {
                        tvCostU14.setText(String.valueOf(Mfarmacia[14]));
                        Double aux = matCant[14] * Mfarmacia[14];
                        matTotal[14] = aux;
                        tvCostT14.setText(aux.toString());
                    }else {
                        Double aux = matCant[14] * Mdistribuidor[14];
                        tvCostU14.setText(String.valueOf(Mdistribuidor[14]));
                        matTotal[14] = aux;
                        tvCostT14.setText(aux.toString());
                    }
                }else{
                    matCant[14] = 0.00;
                    matTotal[14] = 0.00;
                    tvCostT14.setText(matTotal[14].toString());
                }
            }
        });
        etCant15.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant15.getText().toString().isEmpty()){
                    matCant[15] = Double.valueOf(etCant15.getText().toString());
                    if(matCant[15]<300) {
                        tvCostU15.setText(String.valueOf(Mfarmacia[15]));
                        Double aux = matCant[15] * Mfarmacia[15];
                        matTotal[15] = aux;
                        tvCostT15.setText(aux.toString());
                    }else {
                        Double aux = matCant[15] * Mdistribuidor[15];
                        tvCostU15.setText(String.valueOf(Mdistribuidor[15]));
                        matTotal[15] = aux;
                        tvCostT15.setText(aux.toString());
                    }
                }else{
                    matCant[15] = 0.00;
                    matTotal[15] = 0.00;
                    tvCostT15.setText(matTotal[15].toString());
                }
            }
        });
        etCant16.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant16.getText().toString().isEmpty()){
                    matCant[16] = Double.valueOf(etCant16.getText().toString());
                    if(matCant[16]<300) {
                        tvCostU16.setText(String.valueOf(Mfarmacia[16]));
                        Double aux = matCant[16] * Mfarmacia[16];
                        matTotal[16] = aux;
                        tvCostT16.setText(aux.toString());
                    }else {
                        Double aux = matCant[16] * Mdistribuidor[16];
                        tvCostU16.setText(String.valueOf(Mdistribuidor[16]));
                        matTotal[16] = aux;
                        tvCostT16.setText(aux.toString());
                    }
                }else{
                    matCant[16] = 0.00;
                    matTotal[16] = 0.00;
                    tvCostT16.setText(matTotal[16].toString());
                }
            }
        });
        etCant17.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant17.getText().toString().isEmpty()){
                    matCant[17] = Double.valueOf(etCant17.getText().toString());
                    if(matCant[17]<300) {
                        tvCostU17.setText(String.valueOf(Mfarmacia[17]));
                        Double aux = matCant[17] * Mfarmacia[17];
                        matTotal[17] = aux;
                        tvCostT17.setText(aux.toString());
                    }else {
                        Double aux = matCant[17] * Mdistribuidor[17];
                        tvCostU17.setText(String.valueOf(Mdistribuidor[17]));
                        matTotal[17] = aux;
                        tvCostT17.setText(aux.toString());
                    }
                }else{
                    matCant[17] = 0.00;
                    matTotal[17] = 0.00;
                    tvCostT17.setText(matTotal[17].toString());
                }
            }
        });
        etCant18.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant18.getText().toString().isEmpty()){
                    matCant[18] = Double.valueOf(etCant18.getText().toString());
                    if(matCant[18]<300) {
                        tvCostU18.setText(String.valueOf(Mfarmacia[0]));
                        Double aux = matCant[18] * Mfarmacia[18];
                        matTotal[18] = aux;
                        tvCostT18.setText(aux.toString());
                    }else {
                        Double aux = matCant[18] * Mdistribuidor[18];
                        tvCostU18.setText(String.valueOf(Mdistribuidor[18]));
                        matTotal[18] = aux;
                        tvCostT18.setText(aux.toString());
                    }
                }else{
                    matCant[18] = 0.00;
                    matTotal[18] = 0.00;
                    tvCostT18.setText(matTotal[18].toString());
                }
            }
        });
        etCant19.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant19.getText().toString().isEmpty()){
                    matCant[19] = Double.valueOf(etCant19.getText().toString());
                    if(matCant[19]<300) {
                        tvCostU19.setText(String.valueOf(Mfarmacia[19]));
                        Double aux = matCant[19] * Mfarmacia[19];
                        matTotal[19] = aux;
                        tvCostT19.setText(aux.toString());
                    }else {
                        Double aux = matCant[19] * Mdistribuidor[19];
                        tvCostU19.setText(String.valueOf(Mdistribuidor[19]));
                        matTotal[19] = aux;
                        tvCostT19.setText(aux.toString());
                    }
                }else{
                    matCant[19] = 0.00;
                    matTotal[19] = 0.00;
                    tvCostT19.setText(matTotal[19].toString());
                }
            }
        });
        etCant20.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant20.getText().toString().isEmpty()){
                    matCant[20] = Double.valueOf(etCant20.getText().toString());
                    if(matCant[20]<300) {
                        tvCostU20.setText(String.valueOf(Mfarmacia[20]));
                        Double aux = matCant[20] * Mfarmacia[20];
                        matTotal[20] = aux;
                        tvCostT20.setText(aux.toString());
                    }else {
                        Double aux = matCant[20] * Mdistribuidor[20];
                        tvCostU20.setText(String.valueOf(Mdistribuidor[20]));
                        matTotal[20] = aux;
                        tvCostT20.setText(aux.toString());
                    }
                }else{
                    matCant[20] = 0.00;
                    matTotal[20] = 0.00;
                    tvCostT20.setText(matTotal[20].toString());
                }
            }
        });
        etCant21.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant21.getText().toString().isEmpty()){
                    matCant[21] = Double.valueOf(etCant21.getText().toString());
                    if(matCant[21]<300) {
                        tvCostU21.setText(String.valueOf(Mfarmacia[21]));
                        Double aux = matCant[21] * Mfarmacia[21];
                        matTotal[21] = aux;
                        tvCostT21.setText(aux.toString());
                    }else {
                        Double aux = matCant[21] * Mdistribuidor[21];
                        tvCostU21.setText(String.valueOf(Mdistribuidor[21]));
                        matTotal[21] = aux;
                        tvCostT21.setText(aux.toString());
                    }
                }else{
                    matCant[21] = 0.00;
                    matTotal[21] = 0.00;
                    tvCostT21.setText(matTotal[21].toString());
                }
            }
        });
        etCant22.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant22.getText().toString().isEmpty()){
                    matCant[22] = Double.valueOf(etCant22.getText().toString());
                    if(matCant[22]<300) {
                        tvCostU22.setText(String.valueOf(Mfarmacia[22]));
                        Double aux = matCant[22] * Mfarmacia[22];
                        matTotal[22] = aux;
                        tvCostT22.setText(aux.toString());
                    }else {
                        Double aux = matCant[22] * Mdistribuidor[22];
                        tvCostU22.setText(String.valueOf(Mdistribuidor[22]));
                        matTotal[22] = aux;
                        tvCostT22.setText(aux.toString());
                    }
                }else{
                    matCant[22] = 0.00;
                    matTotal[22] = 0.00;
                    tvCostT22.setText(matTotal[22].toString());
                }
            }
        });
        etCant23.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant23.getText().toString().isEmpty()){
                    matCant[23] = Double.valueOf(etCant23.getText().toString());
                    if(matCant[23]<300) {
                        tvCostU23.setText(String.valueOf(Mfarmacia[23]));
                        Double aux = matCant[23] * Mfarmacia[23];
                        matTotal[23] = aux;
                        tvCostT23.setText(aux.toString());
                    }else {
                        Double aux = matCant[23] * Mdistribuidor[23];
                        tvCostU23.setText(String.valueOf(Mdistribuidor[23]));
                        matTotal[23] = aux;
                        tvCostT23.setText(aux.toString());
                    }
                }else{
                    matCant[23] = 0.00;
                    matTotal[23] = 0.00;
                    tvCostT23.setText(matTotal[23].toString());
                }
            }
        });
        etCant24.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant24.getText().toString().isEmpty()){
                    matCant[24] = Double.valueOf(etCant24.getText().toString());
                    if(matCant[24]<300) {
                        tvCostU24.setText(String.valueOf(Mfarmacia[24]));
                        Double aux = matCant[24] * Mfarmacia[24];
                        matTotal[24] = aux;
                        tvCostT24.setText(aux.toString());
                    }else {
                        Double aux = matCant[23] * Mdistribuidor[23];
                        tvCostU23.setText(String.valueOf(Mdistribuidor[23]));
                        matTotal[23] = aux;
                        tvCostT23.setText(aux.toString());
                    }
                }else{
                    matCant[24] = 0.00;
                    matTotal[24] = 0.00;
                    tvCostT24.setText(matTotal[24].toString());
                }
            }
        });
        etCant25.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant25.getText().toString().isEmpty()){
                    matCant[25] = Double.valueOf(etCant25.getText().toString());
                    if(matCant[25]<300) {
                        tvCostU25.setText(String.valueOf(Mfarmacia[25]));
                        Double aux = matCant[25] * Mfarmacia[25];
                        matTotal[25] = aux;
                        tvCostT25.setText(aux.toString());
                    }else {
                        Double aux = matCant[25] * Mdistribuidor[25];
                        tvCostU25.setText(String.valueOf(Mdistribuidor[25]));
                        matTotal[25] = aux;
                        tvCostT25.setText(aux.toString());
                    }
                }else{
                    matCant[25] = 0.00;
                    matTotal[25] = 0.00;
                    tvCostT25.setText(matTotal[25].toString());
                }
            }
        });
        etCant26.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant26.getText().toString().isEmpty()){
                    matCant[26] = Double.valueOf(etCant26.getText().toString());
                    if(matCant[26]<300) {
                        tvCostU26.setText(String.valueOf(Mfarmacia[26]));
                        Double aux = matCant[26] * Mfarmacia[26];
                        matTotal[26] = aux;
                        tvCostT26.setText(aux.toString());
                    }else {
                        Double aux = matCant[26] * Mdistribuidor[26];
                        tvCostU26.setText(String.valueOf(Mdistribuidor[26]));
                        matTotal[26] = aux;
                        tvCostT26.setText(aux.toString());
                    }
                }else{
                    matCant[26] = 0.00;
                    matTotal[26] = 0.00;
                    tvCostT26.setText(matTotal[26].toString());
                }
            }
        });
        etCant27.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant27.getText().toString().isEmpty()){
                    matCant[27] = Double.valueOf(etCant27.getText().toString());
                    if(matCant[27]<300) {
                        tvCostU27.setText(String.valueOf(Mfarmacia[27]));
                        Double aux = matCant[27] * Mfarmacia[27];
                        matTotal[27] = aux;
                        tvCostT27.setText(aux.toString());
                    }else {
                        Double aux = matCant[27] * Mdistribuidor[27];
                        tvCostU27.setText(String.valueOf(Mdistribuidor[27]));
                        matTotal[27] = aux;
                        tvCostT27.setText(aux.toString());
                    }
                }else{
                    matCant[27] = 0.00;
                    matTotal[27] = 0.00;
                    tvCostT27.setText(matTotal[27].toString());
                }
            }
        });
        etCant28.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (!etCant28.getText().toString().isEmpty()){
                    matCant[28] = Double.valueOf(etCant28.getText().toString());
                    if(matCant[28]<300) {
                        tvCostU28.setText(String.valueOf(Mfarmacia[28]));
                        Double aux = matCant[28] * Mfarmacia[28];
                        matTotal[28] = aux;
                        tvCostT28.setText(aux.toString());
                    }else {
                        Double aux = matCant[28] * Mdistribuidor[28];
                        tvCostU28.setText(String.valueOf(Mdistribuidor[28]));
                        matTotal[28] = aux;
                        tvCostT28.setText(aux.toString());
                    }
                }else{
                    matCant[28] = 0.00;
                    matTotal[28] = 0.00;
                    tvCostT28.setText(matTotal[28].toString());
                }
            }
        });

        btCotizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    cortizarVenta();
            }
        });

        btVender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cortizarVenta();
                if (SelectListClientes!=0 && !etFactura.getText().toString().isEmpty() && !etObserVenta.getText().toString().isEmpty() && Sumatoria!=0) {
                    FacturaVal = etFactura.getText().toString();
                    ObserVal = etObserVenta.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);
                    new SendRequest().execute();

                    //Toast.makeText(getContext(), FacturaVal+", "+ObserVal, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Ingrese Cliente, Factura y Observación", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void setNombreProducts(){
        tvNombrep0.setText(Mproductos[0]);
        tvNombrep1.setText(Mproductos[1]);
        tvNombrep2.setText(Mproductos[2]);
        tvNombrep3.setText(Mproductos[3]);
        tvNombrep4.setText(Mproductos[4]);
        tvNombrep5.setText(Mproductos[5]);
        tvNombrep6.setText(Mproductos[6]);
        tvNombrep7.setText(Mproductos[7]);
        tvNombrep8.setText(Mproductos[8]);
        tvNombrep9.setText(Mproductos[9]);
        tvNombrep10.setText(Mproductos[10]);
        tvNombrep11.setText(Mproductos[11]);
        tvNombrep12.setText(Mproductos[12]);
        tvNombrep13.setText(Mproductos[13]);
        tvNombrep14.setText(Mproductos[14]);
        tvNombrep15.setText(Mproductos[15]);
        tvNombrep16.setText(Mproductos[16]);
        tvNombrep17.setText(Mproductos[17]);
        tvNombrep18.setText(Mproductos[18]);
        tvNombrep19.setText(Mproductos[19]);
        tvNombrep20.setText(Mproductos[20]);
        tvNombrep21.setText(Mproductos[21]);
        tvNombrep22.setText(Mproductos[22]);
        tvNombrep23.setText(Mproductos[23]);
        tvNombrep24.setText(Mproductos[24]);
        tvNombrep25.setText(Mproductos[25]);
        tvNombrep26.setText(Mproductos[26]);
        tvNombrep27.setText(Mproductos[27]);
        tvNombrep28.setText(Mproductos[28]);
    }
    public void setPrecioUnit(){

        tvCostU0.setText(Mfarmacia[0].toString());
        tvCostU1.setText(Mfarmacia[1].toString());
        tvCostU2.setText(Mfarmacia[2].toString());
        tvCostU3.setText(Mfarmacia[3].toString());
        tvCostU4.setText(Mfarmacia[4].toString());
        tvCostU5.setText(Mfarmacia[5].toString());
        tvCostU6.setText(Mfarmacia[6].toString());
        tvCostU7.setText(Mfarmacia[7].toString());
        tvCostU8.setText(Mfarmacia[8].toString());
        tvCostU9.setText(Mfarmacia[9].toString());
        tvCostU10.setText(Mfarmacia[10].toString());
        tvCostU11.setText(Mfarmacia[11].toString());
        tvCostU12.setText(Mfarmacia[12].toString());
        tvCostU13.setText(Mfarmacia[13].toString());
        tvCostU14.setText(Mfarmacia[14].toString());
        tvCostU15.setText(Mfarmacia[15].toString());
        tvCostU16.setText(Mfarmacia[16].toString());
        tvCostU17.setText(Mfarmacia[17].toString());
        tvCostU18.setText(Mfarmacia[18].toString());
        tvCostU19.setText(Mfarmacia[19].toString());
        tvCostU20.setText(Mfarmacia[20].toString());
        tvCostU21.setText(Mfarmacia[21].toString());
        tvCostU22.setText(Mfarmacia[22].toString());
        tvCostU23.setText(Mfarmacia[23].toString());
        tvCostU24.setText(Mfarmacia[24].toString());
        tvCostU25.setText(Mfarmacia[25].toString());
        tvCostU26.setText(Mfarmacia[26].toString());
        tvCostU27.setText(Mfarmacia[27].toString());
        tvCostU28.setText(Mfarmacia[28].toString());
    }
    Double Sumatoria;
    private SimpleDateFormat timeStampFormat;

    public void cortizarVenta(){
        int i = 0;
        Sumatoria=0.00;
        while (i<=matTotal.length-1){
            Sumatoria = Sumatoria + matTotal[i];
            i++;
        }
        tvTotal.setText("TOTAL: "+String.valueOf(Sumatoria));
        AvanceEnvio=0;
    }

    public void deshabilitarEditText(){
        etCant0.setEnabled(false);
        etCant1.setEnabled(false);
        etCant2.setEnabled(false);
        etCant3.setEnabled(false);
        etCant4.setEnabled(false);
        etCant5.setEnabled(false);
        etCant6.setEnabled(false);
        etCant7.setEnabled(false);
        etCant8.setEnabled(false);
        etCant9.setEnabled(false);
        etCant10.setEnabled(false);
        etCant11.setEnabled(false);
        etCant12.setEnabled(false);
        etCant13.setEnabled(false);
        etCant14.setEnabled(false);
        etCant15.setEnabled(false);
        etCant16.setEnabled(false);
        etCant17.setEnabled(false);
        etCant18.setEnabled(false);
        etCant19.setEnabled(false);
        etCant20.setEnabled(false);
        etCant21.setEnabled(false);
        etCant22.setEnabled(false);
        etCant23.setEnabled(false);
        etCant24.setEnabled(false);
        etCant25.setEnabled(false);
        etCant26.setEnabled(false);
        etCant27.setEnabled(false);
        etCant28.setEnabled(false);

    }
    public void habilitarEditText(){
        etCant0.setEnabled(true);
        etCant1.setEnabled(true);
        etCant2.setEnabled(true);
        etCant3.setEnabled(true);
        etCant4.setEnabled(true);
        etCant5.setEnabled(true);
        etCant6.setEnabled(true);
        etCant7.setEnabled(true);
        etCant8.setEnabled(true);
        etCant9.setEnabled(true);
        etCant10.setEnabled(true);
        etCant11.setEnabled(true);
        etCant12.setEnabled(true);
        etCant13.setEnabled(true);
        etCant14.setEnabled(true);
        etCant15.setEnabled(true);
        etCant16.setEnabled(true);
        etCant17.setEnabled(true);
        etCant18.setEnabled(true);
        etCant19.setEnabled(true);
        etCant20.setEnabled(true);
        etCant21.setEnabled(true);
        etCant22.setEnabled(true);
        etCant23.setEnabled(true);
        etCant24.setEnabled(true);
        etCant25.setEnabled(true);
        etCant26.setEnabled(true);
        etCant27.setEnabled(true);
        etCant28.setEnabled(true);

    }

    public void iniciaAsyncTask(){
        new SendRequest().execute();
    }

    public class SendRequest extends AsyncTask<String, Void, String> {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected String doInBackground(String... strings) {
            timeStampFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
            Date myDate = new Date();
            String fechaN = timeStampFormat.format(myDate);

            try{
                //Change your web app deployed URL or u can use this for attributes (name, country)
                URL url=null;
                JSONObject postDataParams = new JSONObject();
                StringBuffer sb = new StringBuffer("");
                if (AvanceEnvio<=28 && matTotal[AvanceEnvio]>0){
                    postDataParams.put("fecha",fechaN);
                    postDataParams.put("cliente",vClientes[SelectListClientes]);
                    postDataParams.put("producto",Mproductos[AvanceEnvio]);
                    postDataParams.put("cantidad",matCant[AvanceEnvio].toString());
                    postDataParams.put("valor",matTotal[AvanceEnvio].toString());
                    postDataParams.put("factura",FacturaVal);

                    url = new URL("https://script.google.com/macros/s/AKfycbzv9m8i6lGKBv4Dwqe_UfBPXAJ_hIxz45lfIXwZtcG978DqAg/exec");

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

                }if (AvanceEnvio==29){

                    postDataParams.put("fecha",fechaN);
                    postDataParams.put("cliente",vClientes[SelectListClientes]);
                    postDataParams.put("valor",String.valueOf(Sumatoria));
                    postDataParams.put("factura",FacturaVal);
                    postDataParams.put("observacion",ObserVal);
                    postDataParams.put("responsable",responsable);

                    url = new URL("https://script.google.com/macros/s/AKfycbyk_oTtJJfVgUZqjuJE1bDfM3_e_fDWBgcel81bB8jX4L4-JA/exec");
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
                if (matTotal[AvanceEnvio]==0){

                    //Toast.makeText(getContext(), "Pasa a siguiente", Toast.LENGTH_SHORT).show();
                }
                return new String("false : ");
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

            AvanceEnvio++;
            if (AvanceEnvio<=29){

                iniciaAsyncTask();
                /*
                Toast.makeText(getContext(), "Registro de 111111111",
                        Toast.LENGTH_LONG).show();
                        */
            }else {
                limpiaCajas();
                setPrecioUnit();
                Toast.makeText(getContext(), "Registro de venta realizado",
                        Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                Log.e("registro","fin de los registros");
            }
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

    public String[] parser(JSONArray response){

        ArrayList<Clientes> clienteAux = new ArrayList<Clientes>();

        String[] Clientes = new String[response.length()+1];
        Clientes[0] = "Selecciones un Cliente";
        for (int i = 0; i < response.length(); i++){
            Clientes clientes = new Clientes();
            try {
                JSONObject jsonObject = (JSONObject) response.get(i);
                clientes.setNombre(jsonObject.getString("nombre"));
                clientes.setCanton(jsonObject.getString("canton"));
                Clientes[i+1] = clientes.getNombre().toString()+" de "+clientes.getCanton().toString();
                //vCanton[i] = clientes.getCanton().toString();

                Log.e("Dato Individual",clientes.getNombre().toString());
                Log.e(TAG,Clientes[i]);

                clienteAux.add(clientes);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG,Clientes[3]);
        Log.e(TAG, String.valueOf(Clientes));
        return Clientes;
    }

    public void limpiaCajas() {
        listCliente.setSelection(0);

        etCant0.setText("");
        tvCostU0.setText("");
        tvCostT0.setText("0.00");

        etCant1.setText("");
        tvCostU1.setText("");
        tvCostT1.setText("0.00");

        etCant2.setText("");
        tvCostU2.setText("");
        tvCostT2.setText("0.00");

        etCant3.setText("");
        tvCostU3.setText("");
        tvCostT3.setText("0.00");

        etCant4.setText("");
        tvCostU4.setText("");
        tvCostT4.setText("0.00");

        etCant5.setText("");
        tvCostU5.setText("");
        tvCostT5.setText("0.00");

        etCant6.setText("");
        tvCostU6.setText("");
        tvCostT6.setText("0.00");

        etCant7.setText("");
        tvCostU7.setText("");
        tvCostT7.setText("0.00");

        etCant8.setText("");
        tvCostU8.setText("");
        tvCostT8.setText("0.00");

        etCant9.setText("");
        tvCostU9.setText("");
        tvCostT9.setText("0.00");

        etCant10.setText("");
        tvCostU10.setText("");
        tvCostT10.setText("0.00");

        etCant11.setText("");
        tvCostU11.setText("");
        tvCostT11.setText("0.00");

        etCant12.setText("");
        tvCostU12.setText("");
        tvCostT12.setText("0.00");

        etCant13.setText("");
        tvCostU13.setText("");
        tvCostT13.setText("0.00");

        etCant14.setText("");
        tvCostU14.setText("");
        tvCostT14.setText("0.00");

        etCant14.setText("");
        tvCostU14.setText("");
        tvCostT14.setText("0.00");

        etCant15.setText("");
        tvCostU15.setText("");
        tvCostT15.setText("0.00");

        etCant16.setText("");
        tvCostU16.setText("");
        tvCostT16.setText("0.00");

        etCant17.setText("");
        tvCostU17.setText("");
        tvCostT17.setText("0.00");

        etCant18.setText("");
        tvCostU18.setText("");
        tvCostT18.setText("0.00");

        etCant19.setText("");
        tvCostU19.setText("");
        tvCostT19.setText("0.00");

        etCant20.setText("");
        tvCostU20.setText("");
        tvCostT20.setText("0.00");

        etCant28.setText("");
        tvCostU28.setText("");
        tvCostT28.setText("0.00");

        etCant21.setText("");
        tvCostU21.setText("");
        tvCostT21.setText("0.00");

        etCant22.setText("");
        tvCostU22.setText("");
        tvCostT22.setText("0.00");

        etCant23.setText("");
        tvCostU23.setText("");
        tvCostT23.setText("0.00");

        etCant24.setText("");
        tvCostU24.setText("");
        tvCostT24.setText("0.00");

        etCant25.setText("");
        tvCostU25.setText("");
        tvCostT25.setText("0.00");

        etCant26.setText("");
        tvCostU26.setText("");
        tvCostT26.setText("0.00");

        etCant27.setText("");
        tvCostU27.setText("");
        tvCostT27.setText("0.00");

        etFactura.setText("");
        etObserVenta.setText("");

        tvTotal.setText("TOTAL: ");

        for (int i = 0 ; i < matTotal.length ; i++){
            matTotal[i]=0.00;
            matCant[i]=0.00;
            SelectListClientes=0;
        }

    }
}
