package udb.fp180271dsm.calculadorasalariossv.Dialogos_CalculoSalario;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import udb.fp180271dsm.calculadorasalariossv.CalculoSalario;
import udb.fp180271dsm.calculadorasalariossv.ContenedorMenu;
import udb.fp180271dsm.calculadorasalariossv.Historico;
import udb.fp180271dsm.calculadorasalariossv.R;
import udb.fp180271dsm.calculadorasalariossv.Resultados;


public class Dialogo_GuardarCalculo extends DialogFragment {

    Button Boton_Si, Boton_No;
    Toolbar tb;
    ActionBar ab;
    private Double salario;
    private Integer idcontrato;
    private String contrato;
    private String uid;



    public Dialogo_GuardarCalculo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialogo__guardar_calculo, container, false);
        Boton_Si=view.findViewById(R.id.btnDialogoSi);
        Boton_No=view.findViewById(R.id.btnDialogoNo);

        Bundle Datos = getArguments();
        salario=Datos.getDouble("salario");
        idcontrato=Datos.getInt("idcontrato");
        contrato=Datos.getString("contrato");
        uid=Datos.getString("uid");


        Resultados fResultados = new Resultados();

        Boton_Si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle EnviarDatos = new Bundle();
                EnviarDatos.putDouble("RecibirSalario",salario);
                EnviarDatos.putInt("RecibirIdContrato",idcontrato);
                EnviarDatos.putString("RecibirContrato",contrato);
                EnviarDatos.putInt("RecibirOpcion",1);
                EnviarDatos.putString("uid",uid);
                fResultados.setArguments(EnviarDatos);
                getDialog().dismiss();
                getActivity().getSupportFragmentManager().beginTransaction()
                       .replace(R.id.fragmentContainerView,fResultados)
                       .addToBackStack(null).commit();
            }
        });

        Boton_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle EnviarDatos = new Bundle();
                EnviarDatos.putDouble("RecibirSalario",salario);
                EnviarDatos.putInt("RecibirIdContrato",idcontrato);
                EnviarDatos.putString("RecibirContrato",contrato);
                EnviarDatos.putInt("RecibirOpcion",2);
                EnviarDatos.putString("uid",uid);
                fResultados.setArguments(EnviarDatos);
                getDialog().dismiss();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView,fResultados)
                        .addToBackStack(null).commit();
            }
        });


        return view;

    }



}