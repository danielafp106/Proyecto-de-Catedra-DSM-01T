package udb.fp180271dsm.calculadorasalariossv.Dialogos_CalculoSalario;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import udb.fp180271dsm.calculadorasalariossv.R;


public class Dialogo_GuardarCalculo extends DialogFragment {

    Button Boton_Si, Boton_No;

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

        Boton_Si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aquí llamaremos al siguiente fragment (Siguiente Entrega) por el momento solo se cerrará
                //-------------Temporal----------
                getDialog().dismiss();
                //-------------------------------
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