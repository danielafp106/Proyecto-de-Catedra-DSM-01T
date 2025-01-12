package udb.fp180271dsm.calculadorasalariossv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class ContenedorMenu extends AppCompatActivity {

    Toolbar tb;
    ActionBar ab;
    DrawerLayout dl;
    NavigationView menuLateral;
    FragmentManager fm;
    FragmentTransaction ft;
    TextView user;
    View header;
    public String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //region Instancias
        setContentView(R.layout.activity_contenedor_menu);
        tb = findViewById(R.id.toolBar);
        dl = findViewById(R.id.drawerlayout);
        menuLateral = findViewById(R.id.menuLateral);
        fm = getSupportFragmentManager();
        String name = getIntent().getStringExtra("name");
        uid = getIntent().getStringExtra("uid");
        header = menuLateral.getHeaderView(0);
        user = header.findViewById(R.id.txtNombreUsuario);
        user.setText(name);
        Bundle b = new Bundle();
        b.putString("uid",uid);
        //endregion

        //region Menu Lateral Navegacion - Configuraciones
        menuLateral.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.OpcSalario:
                        Fragment fCalculo = new CalculoSalario();
                        fCalculo.setArguments(b);
                        ft = fm.beginTransaction();
                        ft.replace(R.id.fragmentContainerView, fCalculo,"Salario");
                        //ft.addToBackStack(null);
                        ft.disallowAddToBackStack();
                        ft.commit();
                        Toolbar("Cálculo de Salario");
                        dl.close();
                        break;
                    case R.id.OpcHistorico:
                        Fragment fHistorico = new Historico();
                        fHistorico.setArguments(b);
                        ft = fm.beginTransaction();
                        ft.replace(R.id.fragmentContainerView, fHistorico,"Historico");
                        //ft.addToBackStack(null);
                        ft.disallowAddToBackStack();
                        ft.commit();
                        Toolbar("Historico de Cálculos");
                        dl.close();
                        break;
                    case R.id.OpcCambiarContra:
                        Intent intento = new Intent(ContenedorMenu.this,CambioContrasena.class);
                        startActivity(intento);
                        dl.close();
                        break;
                    case R.id.OpcCerrarSesion:
                        Intent intento2 = new Intent(ContenedorMenu.this,Login.class);
                        startActivity(intento2);
                        dl.close();
                        break;
                }
                return false;
            }
        });
        Fragment fCalculo = new CalculoSalario();
        fCalculo.setArguments(b);
        ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainerView, fCalculo,"Salario");
        ft.disallowAddToBackStack();
        ft.commit();
        Toolbar("Cálculo de Salario");
//endregion

    }

    //region Configuracion del Toolbar
    private void Toolbar(String titulo){
        setSupportActionBar(tb);
        ab = getSupportActionBar();
        if(ab!= null){
            ab.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(titulo);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                dl.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion
}