package udb.fp180271dsm.calculadorasalariossv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CambioContrasena extends AppCompatActivity {
    EditText ContraNueva;
    EditText ContraActual;
    EditText ContraConfirmar;
    Button btnCambiarContra;
    private FirebaseAuth MiAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_contrasena);
        MiAuth= FirebaseAuth.getInstance();
        Inicializar();

        btnCambiarContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser user = MiAuth.getCurrentUser();
                String NuevaContra = ContraNueva.getText().toString();
                String ActualContra= ContraActual.getText().toString();
                String ConfirmarContra= ContraConfirmar.getText().toString();

                if (TextUtils.isEmpty(ActualContra)) {
                    Toast.makeText(getApplicationContext(), "Ingresar contraseña actual", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(NuevaContra)) {
                    Toast.makeText(getApplicationContext(), "Ingresar nueva contrasela", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(ConfirmarContra)) {
                    Toast.makeText(getApplicationContext(), "Confirmar nueva contraseña", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!NuevaContra.equals(ConfirmarContra)) {
                    Toast.makeText(getApplicationContext(), "Contraseñas no coinciden", Toast.LENGTH_LONG).show();
                    return;
                }


                user.updatePassword(NuevaContra).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Contraseña Actualizada!", Toast.LENGTH_LONG).show();
                        }
                    }
                });



            }
        });



    }

    private void Inicializar() {
        ContraActual = findViewById(R.id.txtAntiguaContra);
        ContraNueva = findViewById(R.id.txtContraNueva);
        ContraConfirmar = findViewById(R.id.txtConfirmarContraseña3);
        btnCambiarContra = findViewById(R.id.btnCambiarContrasena);
    }

}