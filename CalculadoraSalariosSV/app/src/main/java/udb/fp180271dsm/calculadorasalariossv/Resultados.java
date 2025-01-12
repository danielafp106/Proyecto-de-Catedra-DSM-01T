package udb.fp180271dsm.calculadorasalariossv;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public class Resultados extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference calculosRef;

    private Double salario,RentaResult,AFPResult,ISSSResult;
    private String contrato,uid;
    private Integer resultado,idcontrato;
    private Double SalarioDescuento;
    Double SalarioMensual;
    Double SalarioQuincenal;


    private TextView afp,isss,renta,SalarioB1,SalarioB2,AFP_Dolar,ISSS_Dolar,Renta_Dolar,SalarioLMensual,SalarioLQuincena,TContrato;

    private  static final Double porcentajeRentaSP = 0.10;
    private  static final Double porcentajeRentaII = 0.10;
    private  static final Double porcentajeRentaIII = 0.20;
    private  static final Double porcentajeRentaIV = 0.30;
    private  static final Double porcentajeISSS = 0.03;
    private  static final Double porcentajeAFP = 0.0725;
    private static final Double ExcesoII = 472.00;
    private static final Double ExcesoIII = 895.24;
    private static final Double ExcesoIV = 2038.10;
    private static final Double CuotaII  = 17.67;
    private static final Double CuotaIII  = 60.00;
    private static final Double CuotaIV  = 288.57;




    public Resultados() {
    }

    private Double CalcularSalarioLiquido(Double Salario, Integer Contrato){
      Double Salario_Final=0.00;
      if (Contrato==2){
          //Solo se les descuenta 10% de Renta
          AFPResult=0.00;
          ISSSResult=0.00;
          RentaResult = Salario*porcentajeRentaSP;
          Salario_Final=Salario-RentaResult;
      }
      else{
          AFPResult=Salario*porcentajeAFP;
          //Tope Salarial de cotizaciones del ISSS es de $1000
          if (Salario>1000){
              ISSSResult=30.00;
          }
          else{
              ISSSResult=Salario*porcentajeISSS;
          }
          SalarioDescuento=Salario-AFPResult-ISSSResult;
          //TRAMO I
          if ((SalarioDescuento>=0.01) && (SalarioDescuento <= 472.00)){
              RentaResult=0.00;
              Salario_Final=Salario-AFPResult-ISSSResult-RentaResult;
          }//TRAMO II
          else if ((SalarioDescuento >= 472.01) && (SalarioDescuento <= 895.24)){
              RentaResult=((SalarioDescuento-ExcesoII)*porcentajeRentaII)+CuotaII;
              Salario_Final=SalarioDescuento-RentaResult;

          }//TRAMO III
          else if ((SalarioDescuento >=895.25) && (SalarioDescuento <= 2038.10)){
              RentaResult=((SalarioDescuento-ExcesoIII)*porcentajeRentaIII)+CuotaIII;
              Salario_Final=SalarioDescuento-RentaResult;

          }//TRAMO IV
          else if (SalarioDescuento >= 2038.11){
              RentaResult=((SalarioDescuento-ExcesoIV)*porcentajeRentaIV)+CuotaIV;
              Salario_Final=SalarioDescuento-RentaResult;
          }
      }

      return Salario_Final;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultados, container, false);
        afp=view.findViewById(R.id.text_AFP_R);
        isss=view.findViewById(R.id.textISSS_R);
        renta=view.findViewById(R.id.txtRentaPorcentajeH_R);
        SalarioB1=view.findViewById(R.id.txtSalarioBrutoH_R);
        SalarioB2=view.findViewById(R.id.txtSalarioBrutoH2_R);
        AFP_Dolar=view.findViewById(R.id.txtAFPDolaresH_R);
        ISSS_Dolar=view.findViewById(R.id.txtISSSDolaresH_R);
        Renta_Dolar=view.findViewById(R.id.txtRentaDolaresh_R);
        SalarioLMensual=view.findViewById(R.id.txtSalarioLMensualH_R);
        SalarioLQuincena=view.findViewById(R.id.txtSalarioLQuincenalH_R);
        TContrato=view.findViewById(R.id.txtContratoH_R);
        Bundle RecibirDatos = getArguments();
        salario=RecibirDatos.getDouble("RecibirSalario");
        contrato =RecibirDatos.getString("RecibirContrato");
        idcontrato=RecibirDatos.getInt("RecibirIdContrato");
        resultado=RecibirDatos.getInt("RecibirOpcion");
        uid = RecibirDatos.getString("uid");


        // else (Igualmente va a mostrar los datos)
        if (idcontrato ==2){
            afp.setText("0.00%");
            isss.setText("0.00%");
            renta.setText("10.00%");
            SalarioMensual = CalcularSalarioLiquido(salario, idcontrato);
            SalarioQuincenal =SalarioMensual/2;
            TContrato.setText(contrato);
            SalarioB1.setText("$ "+String.format("%.2f",salario));
            SalarioB2.setText("$ "+String.format("%.2f",salario));
            AFP_Dolar.setText("- $ "+String.format("%.2f",AFPResult));
            Renta_Dolar.setText("- $ "+String.format("%.2f",RentaResult));
            ISSS_Dolar.setText("- $ "+String.format("%.2f",ISSSResult));

            SalarioLMensual.setText("$ "+String.format("%.2f",SalarioMensual));
            SalarioLQuincena.setText("$ "+String.format("%.2f",SalarioQuincenal));
        }
        else{
            SalarioMensual = CalcularSalarioLiquido(salario, idcontrato);
            SalarioQuincenal =SalarioMensual/2;

            //TRAMOI
            if ((SalarioDescuento>=0.01) && (SalarioDescuento <= 472.00)){
                renta.setText("00.00%");
            }//TRAMO II
            else if ((SalarioDescuento >= 472.01) && (SalarioDescuento <= 895.24)){
                renta.setText("10.00%");
            }//TRAMO III
            else if ((SalarioDescuento >=895.25) && (SalarioDescuento <= 2038.10)){
                renta.setText("20.00%");
            }//TRAMO IV
            else if (SalarioDescuento >= 2038.11){
                renta.setText("30.00%");
            }

            TContrato.setText(contrato);
            SalarioB1.setText("$ "+String.format("%.2f",salario));
            SalarioB2.setText("$ "+String.format("%.2f",salario));
            AFP_Dolar.setText("- $ "+String.format("%.2f",AFPResult));
            Renta_Dolar.setText("- $ "+String.format("%.2f",RentaResult));
            ISSS_Dolar.setText("- $ "+String.format("%.2f",ISSSResult));

            SalarioLMensual.setText("$ "+String.format("%.2f",SalarioMensual));
            SalarioLQuincena.setText("$ "+String.format("%.2f",SalarioQuincenal));

        }

        if (resultado==1){
            HistoricoModel resultado = new HistoricoModel();
            resultado.setAFP(AFPResult);
            resultado.setISSS(ISSSResult);
            resultado.setRenta(RentaResult);
            resultado.setFechaHistorico(new Date(System.currentTimeMillis()));
            resultado.setIdUsuario(uid);
            resultado.setPorcentajeRenta(renta.getText().toString());
            resultado.setPorcentajeISSS(isss.getText().toString());
            resultado.setPorcentajeAFP(afp.getText().toString());
            resultado.setSalarioBruto(salario);
            resultado.setSalarioLiquidoMensual(SalarioMensual);
            resultado.setSalarioLiquidoQuincenal(SalarioQuincenal);
            resultado.setTipoContrato(contrato);
            GuardarSalario(resultado);
        }



        return view;
    }

    private void GuardarSalario(HistoricoModel resultados)
    {
        db.collection("calculos").add(resultados).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getActivity(), "Resultado guardado con éxito", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Algo salió mal..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}