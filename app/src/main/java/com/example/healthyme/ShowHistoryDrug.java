package com.example.healthyme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ShowHistoryDrug extends AppCompatActivity {

    TextView textViewData;
    EditText name;
    LoginActivity Login = new LoginActivity();
    String id = Login.getAddr().getText().toString();
    String path = id + "/takingDrugs";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference Drugs = db.collection(path);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history_drug);
        textViewData = findViewById(R.id.textViewData);
        name = findViewById(R.id.medname);
        String mname = name.getText().toString();
        Drugs.whereArrayContains("medication",mname ).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Taking drug = documentSnapshot.toObject(Taking.class);
                            data += "\n" +drug;
                        }
                        textViewData.setText(data);
                    }
                });

    }
}
