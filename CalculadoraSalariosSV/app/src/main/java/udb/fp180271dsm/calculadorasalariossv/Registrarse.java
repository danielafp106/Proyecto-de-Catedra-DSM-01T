package udb.fp180271dsm.calculadorasalariossv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Registrarse extends AppCompatActivity {

    TextView txtIniciarSesion, txtNombreApellido, txtCorreoRegistro, txtContra, txtConfirmarContra;
    Button btnRegistrar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        mAuth = FirebaseAuth.getInstance();

        initializeUI();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }
        });

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

    //funcion para registrar un nuevo usuario


    private void registrarUsuario() {
        String email, password,confirmarContra, name;
        email = txtCorreoRegistro.getText().toString();
        password = txtContra.getText().toString();
        confirmarContra = txtConfirmarContra.getText().toString();
        name = txtNombreApellido.getText().toString();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Ingrese un Correo...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Ingrese una contraseña!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(confirmarContra)) {
            Toast.makeText(getApplicationContext(), "Ingrese confirmar contraseña!", Toast.LENGTH_LONG).show();
            return;
        }
       /* if (contra!=confirmarContra) {
            Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden!", Toast.LENGTH_LONG).show();
            return;
        }*/

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            UserProfileChangeRequest asignandoNombre = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();

                            user.updateProfile(asignandoNombre)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(), "Usuario Registrado Correctamente!", Toast.LENGTH_LONG).show();
                                                //abrir siguiente ventana
                                                AbrirActivity(Registrarse.this,Login.class);
                                            }
                                            else{
                                                Toast.makeText(getApplicationContext(), "Nombre y Apellido fallaron", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "El usuario no fue Registrado", Toast.LENGTH_LONG).show();
                            Log.d("Aver",task.getException().getMessage());
                        }
                    }
                });
    }


    //fin de funcion registro usuario



    public void AbrirActivity(Context ActivityActual, Class ActivityNueva) {
        Intent intento = new Intent(ActivityActual, ActivityNueva);
        startActivity(intento);
    }


    private void initializeUI() {
        txtNombreApellido = findViewById(R.id.txtNombreApellido);
        txtCorreoRegistro = findViewById(R.id.txtCorreoRegistrar);
        txtContra = findViewById(R.id.txtContrasenaRegistrar);
        txtConfirmarContra = findViewById(R.id.txtConfirmarContrasena);
        btnRegistrar=findViewById(R.id.btnRegistrar);
    }
}