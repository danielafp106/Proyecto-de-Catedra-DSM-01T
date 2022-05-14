package udb.fp180271dsm.calculadorasalariossv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText txtCorreo, txtContra;
    private Button btnLogin;

    private FirebaseAuth mAuth;

    TextView txtRegistrarse1, txtRegistrarse2, txtRestablecerContra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //empieza codigo para inicio de sesion con firebase
        mAuth = FirebaseAuth.getInstance();

        initializeUI();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });
        //region Abrir Registarse
        txtRegistrarse1 = findViewById(R.id.txtRegistrar1);
        txtRegistrarse2 = findViewById(R.id.txtRegistrar2);
        txtRestablecerContra = findViewById(R.id.txtRegresar);

        txtRegistrarse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirActivity(Login.this, Registrarse.class,"", "");
            }
        });
        txtRegistrarse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirActivity(Login.this, Registrarse.class,"","");
            }
        });

        txtRestablecerContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirActivity(Login.this, RestablecerContra.class,"","");
            }
        });


        //endregion
    }

    //funcion para inicio de sesion ocn firebase
    private void iniciarSesion()
    {
        String correo, contrasena;
        correo = txtCorreo.getText().toString();
        contrasena = txtContra.getText().toString();

        if (TextUtils.isEmpty(correo)) {
            Toast.makeText(getApplicationContext(), "Ingrese un Correo...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(contrasena)) {
            Toast.makeText(getApplicationContext(), "Ingrese una contraseña!", Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Inicio de Sesion Correcto!", Toast.LENGTH_LONG).show();
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            //abrimos la pantalla del menu
                            AbrirActivity(Login.this, ContenedorMenu.class,user.getDisplayName(),user.getUid());
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Inicio de Sesion Incorrecto", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }




    public void AbrirActivity(Context ActivityActual, Class ActivityNueva, String user,String uid) {
        Intent intento = new Intent(ActivityActual, ActivityNueva);
        if(user!="")
        {
            intento.putExtra("name",user);
            intento.putExtra("uid",uid);
        }
        startActivity(intento);
    }

    public void InicioSesionCorrecto(View v)
    {
        AbrirActivity(Login.this, ContenedorMenu.class,"","");
    }
/*
    public void prueba(View v)
    {
        AbrirActivity(Login.this,Registrarse.class);
    }

*/



    private void initializeUI() {
        txtCorreo = findViewById(R.id.txtCorreo);
        txtContra = findViewById(R.id.txtContra);

        btnLogin = findViewById(R.id.btnRestablecer);

    }

}