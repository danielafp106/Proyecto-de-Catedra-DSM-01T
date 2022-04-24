package udb.fp180271dsm.calculadorasalariossv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pgb;
    private TextView txtPorcentaje;
    int progress =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializaciones
        pgb = findViewById(R.id.cargandoPgb);
        txtPorcentaje = findViewById(R.id.txtPorcentaje);
        Handler h = new Handler();

        //Animacion Barra Progreso
        Thread hiloPgb = new Thread(new Runnable(){
            @Override
            public void run(){
                while(progress<=100){
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            txtPorcentaje.setText(progress+"%");
                            pgb.setProgress(progress);
                        }
                    });
                    try {
                        Thread.sleep(450);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(progress==100){
                        Intent intento= new Intent(MainActivity.this,Login.class);
                        startActivity(intento);
                        finish();
                    }
                    progress+=20;
                }
            }
        });
        hiloPgb.start();
    }
}