package marcespal.sifal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    String[] vUsuario ={"MARCELO","DARIO","FABIAN","BLANCA","ROSY","MARCESPAL"};
    String[] vPassword = {"123","456","789","011","121","180"};
    private EditText etUsuario, etPassword;
    public String Usuario, Password;
    private Button btSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btSesion = findViewById(R.id.btSesion);

        btSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario = etUsuario.getText().toString();
                Password = etPassword.getText().toString();

                Log.e("PRUEBA",Usuario+", "+Password);
                Log.e("PRUEBA",vUsuario[0]+", "+vPassword[0]);
                iniciarSesion();
            }
        });
    }

    public void iniciarSesion(){
        if (Usuario.equals(vUsuario[0])){
            if (Password.equals(vPassword[0])){
                guardarUsuario();
            }else {
                Toast.makeText(this, "La contraseña es incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
        if (Usuario.equals(vUsuario[1])){
            if (Password.equals(vPassword[1])){
                guardarUsuario();
            }else {
                Toast.makeText(this, "La contraseña es incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
        if (Usuario.equals(vUsuario[2])){
            if (Password.equals(vPassword[2])){
                guardarUsuario();
            }else {
                Toast.makeText(this, "La contraseña es incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
        if (Usuario.equals(vUsuario[3])){
            if (Password.equals(vPassword[3])){
                guardarUsuario();
            }else {
                Toast.makeText(this, "La contraseña es incorrectos", Toast.LENGTH_SHORT).show();
            }
        }if (Usuario.equals(vUsuario[4])){
            if (Password.equals(vPassword[4])){
                guardarUsuario();
            }else {
                Toast.makeText(this, "La contraseña es incorrectos", Toast.LENGTH_SHORT).show();
            }
        }if (Usuario.equals(vUsuario[5])) {
            if (Password.equals(vPassword[5])) {
                guardarUsuario();
            } else {
                Toast.makeText(this, "La contraseña es incorrectos", Toast.LENGTH_SHORT).show();
            }
            if (!Usuario.equals(vUsuario[0]) && !Usuario.equals(vUsuario[1]) && !Usuario.equals(vUsuario[2])
                    && !Usuario.equals(vUsuario[3]) && !Usuario.equals(vUsuario[4]) && !Usuario.equals(vUsuario[5])) {
                Toast.makeText(this, "El Usuario no existe", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void guardarUsuario(){

        SharedPreferences preferences = getSharedPreferences
                ("Credenciales",MODE_PRIVATE);

        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("User",Usuario);
        editor.putString("Pass",Password);

        editor.commit();

        Intent Main = new Intent(getApplicationContext(), MainActivity.class);
        Main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Main);

    }
}
