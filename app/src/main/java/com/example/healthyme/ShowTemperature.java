package com.example.healthyme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ShowTemperature extends AppCompatActivity {

    TextView textViewData;
    LoginActivity Login = new LoginActivity();
    String id = Login.getAddr().getText().toString();
    String path = id + "/temperature";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference Temperature = db.collection(path);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_temperature);

        textViewData = findViewById(R.id.textViewData);
        Temperature.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String temp = documentSnapshot.toObject(String.class);
                            data += "\n" +temp;
                        }
                        textViewData.setText(data);
                    }
                });
    }
}
