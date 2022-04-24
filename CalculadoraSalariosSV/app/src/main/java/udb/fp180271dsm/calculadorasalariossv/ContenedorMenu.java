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

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class ContenedorMenu extends AppCompatActivity {

    Toolbar tb;
    ActionBar ab;
    DrawerLayout dl;
    NavigationView menuLateral;
    FragmentManager fm;
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //region Instancias
        setContentView(R.layout.activity_contenedor_menu);
        tb = findViewById(R.id.toolBar);
        dl = findViewById(R.id.drawerlayout);
        menuLateral = findViewById(R.id.menuLateral);
        fm = getSupportFragmentManager();
        //endregion

        menuLateral.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.OpcSalario:
                        Fragment fCalculo = new CalculoSalario();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.fragmentContainerView, fCalculo,"Salario");
                        //ft.addToBackStack(null);
                        ft.disallowAddToBackStack();
                        ft.commit();
                        break;
                    case R.id.OpcHistorico:
                        Fragment fHistorico = new Historico();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.fragmentContainerView, fHistorico,"Historico");
                        //ft.addToBackStack(null);
                        ft.disallowAddToBackStack();
                        ft.commit();
                        break;
                }
                return false;
            }
        });

        Toolbar("Cálculo de Salario");

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