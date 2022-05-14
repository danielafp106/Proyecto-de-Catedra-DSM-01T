package udb.fp180271dsm.calculadorasalariossv;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Historico#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Historico extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private String mParam1;

    public Historico() {
        // Required empty public constructor
    }

    public static Historico newInstance(String param1, String param2) {
        Historico fragment = new Historico();
        Bundle args = new Bundle();
        return fragment;
    }

    ListView lvHistorial;
    ArrayList<HistoricoModel> dataModalArrayList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference historicoRef;
    String uid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("uid");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_historico, container, false);
        historicoRef = db.collection("calculos");
        lvHistorial = v.findViewById(R.id.lvHistorico);
        BuscarHistorial();
        return v;

    }

    public void BuscarHistorial()
    {
        uid = mParam1;
        dataModalArrayList = new ArrayList<>();
        loadDatainListview(uid);
    }

    private void loadDatainListview(String uid) {
        historicoRef.whereEqualTo("idUsuario",uid).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                HistoricoModel dataModal = d.toObject(HistoricoModel.class);
                                dataModalArrayList.add(dataModal);
                            }
                            HistoricoListViewAdapter adapter = new HistoricoListViewAdapter(getActivity(), dataModalArrayList);
                            lvHistorial.setAdapter(adapter);
                        } else {
                            Toast.makeText(getActivity(), "No se encontraron historicos", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}