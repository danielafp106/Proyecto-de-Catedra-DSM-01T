package udb.fp180271dsm.calculadorasalariossv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    TextView txtRegistrarse1, txtRegistrarse2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //region Abrir Registarse
        txtRegistrarse1 = findViewById(R.id.txtRegistrar1);
        txtRegistrarse2 = findViewById(R.id.txtRegistrar2);
        txtRegistrarse1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirActivity(Login.this,Registrarse.class);
            }
        });
        txtRegistrarse2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirActivity(Login.this,Registrarse.class);
            }
        });
        //endregion
    }

    public void AbrirActivity(Context ActivityActual, Class ActivityNueva) {
        Intent intento = new Intent(ActivityActual, ActivityNueva);
        startActivity(intento);
    }

    public void IniciarSesion(View v)
    {
        AbrirActivity(Login.this, ContenedorMenu.class);
    }

}