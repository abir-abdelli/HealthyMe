package com.example.healthyme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class HealthActivity extends AppCompatActivity implements  View.OnClickListener {
    EditText height, weight,temp;
    TextView affiche, affiche2;
    //get the email of the user ( the collection name)
    LoginActivity Login = new LoginActivity();
    String id = Login.getAddr().getText().toString();
    String path = id + "/temperature";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference Temperature = db.collection(path);//each user has a collection called after his address mail.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        findViewById(R.id.result).setOnClickListener(this);
        findViewById(R.id.answer).setOnClickListener(this);

        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        temp = findViewById(R.id.temp);
        affiche = findViewById(R.id.textView);
        affiche2 = findViewById(R.id.textView2);
    }
    public void onClick(View view) {
        int mheight = Integer.parseInt(height.getText().toString());
        int mweight = Integer.parseInt(weight.getText().toString());
        int mtemp = Integer.parseInt(temp.getText().toString());

        switch (view.getId()) {
            case R.id.result:
                int res = mweight/(mheight*mheight);
                String message;
                if (res > 25) {
                    message = "caution! Your are overweight, please consider starting a diet.";
                } else if (res < 18) {
                    message = "You're so thin, take more care of your self";
                }
                else {
                    message = "You're healthy! Keep it up!";
                }
                affiche.setText(message);
                break;
            case R.id.answer:
                Temperature.add(temp.getText().toString());

                if (mtemp > 40) {
                    Intent ihealth = new Intent(HealthActivity.this, Temperature.class);
                    startActivity(ihealth);
                }
                else {
                    String ans = " you're fine!";
                    affiche2.setText(ans);
                }

                break;
        }
    }
}
