package marcespal.sifal;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import marcespal.sifal.fragments.AddBodegaFragment;
import marcespal.sifal.fragments.AddClienteFragment;
import marcespal.sifal.fragments.AddVentaFragment;
import marcespal.sifal.fragments.BodegaFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Double latitud, longitud;
    String[] Mproductos = {
            "BARDANA día","BARDANA noche","CANCHINACUI tabletas","CANCHINACUI vino","CARANDAY ungüento",
            "CATAPLAC elíxir","CHUCUTA emulsión","CHUCUTA tabletas","DENDRITAS jarabe","HUASCARAY extracto",
            "JAPIKAY 250gr","JAPIKAY 500gr","JINAJAY tónico","KUMARA jaléa","LAMOCHITA callicida",
            "LOMVERSAN jarabe","LOMVERSAN tabletas","MASAJE CAYUGA líquido","PASION&MACA","PIEL ROJA emulsión",
            "PROSEROT tabletas","PROSEROT vino","SANAJOL 350ml","SANAJOL 500ml","SIFADERMA crema",
            "SILVESTRE jarabe","SILVESTRIN tabletas","VERBENOL fungicida","YAKASARA elíxir"};

    public String user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        PERMISO DE USO DEL GPS
        */

        cargarPreferencias();
        if(user.equals("nulo")){
            Intent Main = new Intent(getApplicationContext(), LoginActivity.class);
            Main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(Main);
        }


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            //Toast.makeText(this, "Aqui llama al GPS", Toast.LENGTH_SHORT).show();
            locationStart();
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fragment = new BodegaFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.Contenedor, fragment).commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Boolean FragmentSelect = false;

        if (id == R.id.nav_camera) {
            // Handle the camera action
            fragment = new AddClienteFragment();
            FragmentSelect = true;

        } else if (id == R.id.nav_gallery) {
            fragment = new AddVentaFragment();
            FragmentSelect = true;

        } else if (id == R.id.nav_slideshow) {
            fragment = new AddBodegaFragment();
            FragmentSelect = true;

        } else if (id == R.id.nav_send) {
            cerrarSesion();
            Intent Main = new Intent(getApplicationContext(), LoginActivity.class);
            Main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(Main);
        }

        if (FragmentSelect){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.Contenedor,fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMainActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);
/*
        mensaje1.setText("Localizacion agregada");
        mensaje2.setText("");
*/
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }

    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    /*
                    mensaje2.setText("Mi direccion es: \n"
                            + DirCalle.getAddressLine(0));
                            */
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getLongitud(){
        String longText;
        longText = String.valueOf(longitud);
        return longText;
    }
    public String getLatitud(){
        String latText;
        latText = String.valueOf(latitud);
        return latText;
    }

    /* Aqui empieza la Clase Localizacion */
    public class Localizacion implements LocationListener {
        MainActivity mainActivity;

        public MainActivity getMainActivity() {
            return mainActivity;
        }

        public void setMainActivity(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion

            loc.getLatitude();
            loc.getLongitude();

            latitud = loc.getLatitude();
            longitud = loc.getLongitude();
/*
            String Text = "Mi ubicacion actual es: " + "\n Lat = "
                    + loc.getLatitude() + "\n Long = " + loc.getLongitude();
            mensaje1.setText(Text);
 */
            this.mainActivity.setLocation(loc);
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            Toast.makeText(MainActivity.this, "GPS Desactivado", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            Toast.makeText(MainActivity.this, "GPS Activado", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }

    public void cargarPreferencias(){
        SharedPreferences preferences = getSharedPreferences
                ("Credenciales",MODE_PRIVATE);
        user = preferences.getString("User","nulo");
        pass = preferences.getString("Pass","nulo");

    }
    public void cerrarSesion(){
        SharedPreferences preferences = getSharedPreferences
                ("Credenciales",MODE_PRIVATE);

        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("User","nulo");
        editor.putString("Pass","nulo");

        editor.commit();
    }

    public String enviarUsuario(){
        SharedPreferences preferences = getSharedPreferences("Credenciales",MODE_PRIVATE);
        user = preferences.getString("User","nulo");

        return user;
    }

}
