package udb.fp180271dsm.calculadorasalariossv;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import udb.fp180271dsm.calculadorasalariossv.Dialogos_CalculoSalario.Dialogo_GuardarCalculo;


public class CalculoSalario extends Fragment {

    private Button btn_Calcular;

    public CalculoSalario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculo_salario, container, false);

        btn_Calcular=view.findViewById(R.id.btnCalcularSalario);

        btn_Calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialogo_GuardarCalculo CuadroDialogo = new Dialogo_GuardarCalculo();
                CuadroDialogo.show(getChildFragmentManager(),"Salario");

            }
        });

        return view;
    }
}