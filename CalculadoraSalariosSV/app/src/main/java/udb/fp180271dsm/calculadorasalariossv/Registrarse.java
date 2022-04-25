package udb.fp180271dsm.calculadorasalariossv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registrarse extends AppCompatActivity {

    TextView txtIniciarSesion, txtNombreApellido, txtCorreoRegistro, txtContra, txtConfirmarContra;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        //region Abrir Iniciar Sesión
        txtIniciarSesion = findViewById(R.id.txtIniciarSesion);
        txtIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirActivity(Registrarse.this,Login.class);
            }
        });
        //endregion
    }

    public void AbrirActivity(Context ActivityActual, Class ActivityNueva) {
        Intent intento = new Intent(ActivityActual, ActivityNueva);
        startActivity(intento);
    }
}