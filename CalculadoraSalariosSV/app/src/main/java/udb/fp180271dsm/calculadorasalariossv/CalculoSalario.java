package udb.fp180271dsm.calculadorasalariossv;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import udb.fp180271dsm.calculadorasalariossv.Dialogos_CalculoSalario.Dialogo_GuardarCalculo;


public class CalculoSalario extends Fragment {

    private Button btn_Calcular;
    private Double salario;
    private Integer idcontrato;
    private String contrato;
    private EditText edit_Salario;
    private Spinner spin_Contratos;
    public CalculoSalario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculo_salario, container, false);

        btn_Calcular=view.findViewById(R.id.btnCalcularSalario);
        edit_Salario=view.findViewById(R.id.txtSalarioBruto);
        spin_Contratos=view.findViewById(R.id.spinnerContrato);

        btn_Calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idcontrato=spin_Contratos.getSelectedItemPosition();
                contrato=spin_Contratos.getSelectedItem().toString();

                edit_Salario.setError(null);
                String ValidarCampo = edit_Salario.getText().toString();
                if ("".equals(ValidarCampo)){
                    edit_Salario.setError("Introduce el Salario");
                    edit_Salario.requestFocus();
                    return;
                }
                salario=Double.parseDouble(ValidarCampo);

                Bundle bundle = new Bundle();
                bundle.putInt("idcontrato",idcontrato);
                bundle.putDouble("salario",salario);
                bundle.putString("contrato",contrato);
                Dialogo_GuardarCalculo CuadroDialogo = new Dialogo_GuardarCalculo();
                CuadroDialogo.setArguments(bundle);
                CuadroDialogo.show(getChildFragmentManager(),"Salario");
            }
        });

        return view;
    }
}