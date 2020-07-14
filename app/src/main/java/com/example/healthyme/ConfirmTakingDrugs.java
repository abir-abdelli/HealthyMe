package com.example.healthyme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

import static java.lang.String.valueOf;

public class ConfirmTakingDrugs extends AppCompatActivity implements  View.OnClickListener {

    EditText mmedications, mnotes;
    TimePicker timePicker;

    LoginActivity Login = new LoginActivity();
    String id = Login.getAddr().getText().toString();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String path = id + "/takingDrugs";
    private CollectionReference Takings = db.collection(path);//each user has a collection which is called after his address mail.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_taking_drugs);
        // Set Onclick Listener.
        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }

        @Override
        public void onClick(View view) {
            mmedications = findViewById(R.id.medication);
            mnotes = findViewById(R.id.note);
            timePicker = findViewById(R.id.timePicker);

            switch (view.getId()) {
                case R.id.setBtn:
                    int hour = timePicker.getCurrentHour();
                    int minute = timePicker.getCurrentMinute();

                    //adding the medication to medication list in Firebase
                    String medications = mmedications.getText().toString();
                    String notes = mnotes.getText().toString();
                    String date = valueOf(hour) + ":" + valueOf(minute);

                    Taking taking = new Taking(medications, notes, date);
                    Takings.add(taking);

                    Intent i = new Intent(ConfirmTakingDrugs.this, HomeActivity.class);
                    startActivity(i);

                    Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.cancelBtn:
                    Intent i1 = new Intent(ConfirmTakingDrugs.this, HomeActivity.class);
                    startActivity(i1);
                    Toast.makeText(this, "Canceled.", Toast.LENGTH_SHORT).show();
                    break;
        }

    }
}
