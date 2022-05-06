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
import androidx.fragment.app.FragmentTransaction;

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

    public Dialogo_GuardarCalculo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialogo__guardar_calculo, container, false);
        Boton_Si=view.findViewById(R.id.btnDialogoSi);
        Boton_No=view.findViewById(R.id.btnDialogoNo);

        Resultados fResultados = new Resultados();


        Boton_Si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
                ContenedorMenu Content = new ContenedorMenu();
                getActivity().getSupportFragmentManager().beginTransaction()
                       .replace(R.id.fragmentContainerView,fResultados)
                       .addToBackStack(null).commit();
            }
        });

        Boton_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });


        return view;

    }



}