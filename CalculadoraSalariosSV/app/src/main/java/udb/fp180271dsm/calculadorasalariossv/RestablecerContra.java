package udb.fp180271dsm.calculadorasalariossv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RestablecerContra extends AppCompatActivity {


    private EditText txtEmail;
    private TextView VolverLogin;
    private Button btnRestablecerContra;

    private String emailText="";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer_contra);
        VolverLogin = findViewById(R.id.txtRegresar);
        VolverLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(RestablecerContra.this, Login.class);
                startActivity(intento);
                finish();
            }
        });
        mAuth = FirebaseAuth.getInstance();
        txtEmail= (EditText) findViewById(R.id.txtCorreo);
        btnRestablecerContra =(Button) findViewById(R.id.btnRestablecer);

        btnRestablecerContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailText=txtEmail.getText().toString();
                if (!emailText.isEmpty())
                {
                    restablecerContrasena();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Ingrese un Correo...", Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    private void restablecerContrasena()
    {
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(emailText).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "Se envio correctamente el Correo para restablecer tu contraseña", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No fue posible enviar un Correo para restablecer la contraseña", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}