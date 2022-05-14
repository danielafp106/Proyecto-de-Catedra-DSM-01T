package udb.fp180271dsm.calculadorasalariossv;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HistoricoListViewAdapter extends ArrayAdapter<HistoricoModel> {

    private ArrayList<HistoricoModel> pData;
    public double total;

    public HistoricoListViewAdapter(@NonNull Context context, ArrayList<HistoricoModel> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
        pData=dataModalArrayList;
        total=0;
    }

    @Override
    public int getCount() {;
        return pData.size();
    }

    @Override
    public HistoricoModel getItem(int position) {
        return pData.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.historico_item, parent, false);
        }
        HistoricoModel dataModal = getItem(position);
        TextView txtfechaH = listitemView.findViewById(R.id.txtFechaHistorico);
        TextView txtSalarioBrutoH = listitemView.findViewById(R.id.txtSalarioBrutoH);
        TextView txtContratoH = listitemView.findViewById(R.id.txtContratoH);
        TextView txtPorAFP = listitemView.findViewById(R.id.txtPorAFP);
        TextView txtPorISSS = listitemView.findViewById(R.id.txtPorISSS);
        TextView txtRentaPorcentajeH = listitemView.findViewById(R.id.txtRentaPorcentajeH);
        TextView txtSalarioBrutoH2 = listitemView.findViewById(R.id.txtSalarioBrutoH2);
        TextView txtAFPDolaresH = listitemView.findViewById(R.id.txtAFPDolaresH);
        TextView txtISSSDolaresH = listitemView.findViewById(R.id.txtISSSDolaresH);
        TextView txtRentaDolaresH = listitemView.findViewById(R.id.txtRentaDolaresh);
        TextView txtSalarioLMensualH = listitemView.findViewById(R.id.txtSalarioLMensualH);
        TextView txtSalarioLQuincenalH = listitemView.findViewById(R.id.txtSalarioLQuincenalH);

        DecimalFormat form = new DecimalFormat("0.00");
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MMMM/yyyy");
        txtfechaH.setText("Historial: "+dateformat.format(dataModal.getFechaHistorico()));
        txtSalarioBrutoH.setText("$ "+form.format(dataModal.getSalarioBruto()));
        txtContratoH.setText(dataModal.getTipoContrato());
        txtPorAFP.setText(dataModal.getPorcentajeAFP());
        txtPorISSS.setText(dataModal.getPorcentajeISSS());
        txtRentaPorcentajeH.setText(dataModal.getPorcentajeRenta());
        txtSalarioBrutoH2.setText("$ "+form.format(dataModal.getSalarioBruto()));
        txtAFPDolaresH.setText("- $ "+form.format(dataModal.getAFP()));
        txtISSSDolaresH.setText("- $ "+form.format(dataModal.getISSS()));
        txtRentaDolaresH.setText("- $ "+form.format(dataModal.getRenta()));
        txtSalarioLMensualH.setText("$ "+form.format(dataModal.getSalarioLiquidoMensual()));
        txtSalarioLQuincenalH.setText("$ "+form.format(dataModal.getSalarioLiquidoQuincenal()));
        return listitemView;
    }
}
