package com.example.healthyme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity implements  View.OnClickListener {


    Button showProg, addProg, addDrug, health;
    TextView logout;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set Onclick Listener.
        findViewById(R.id.showProg).setOnClickListener(this);
        findViewById(R.id.addProg).setOnClickListener(this);
        findViewById(R.id.addDrug).setOnClickListener(this);
        findViewById(R.id.health).setOnClickListener(this);
        findViewById(R.id.showMed).setOnClickListener(this);
        findViewById(R.id.history).setOnClickListener(this);
        findViewById(R.id.temp).setOnClickListener(this);


    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.showProg:
                Intent iShowProg = new Intent(HomeActivity.this,ShowProgram.class);
                startActivity(iShowProg);
                break;

            case R.id.addProg:
                Intent iAddProg = new Intent(HomeActivity.this,NewProgram.class);
                startActivity(iAddProg);
                break;

            case R.id.addDrug:

                Intent iaddDrug = new Intent(HomeActivity.this,NewMedication.class);
                break;
            case R.id.health:
                Intent ihealth = new Intent(HomeActivity.this,HealthActivity.class);
                startActivity(ihealth);
                break;
            case R.id.showMed:
                Intent imed = new Intent(HomeActivity.this,ShowMedication.class);
                startActivity(imed);
                break;
            case R.id.history:
                Intent ihist = new Intent(HomeActivity.this,ShowHistoryDrug.class);
                startActivity(ihist);
                break;
            case R.id.temp:
                Intent itemp = new Intent(HomeActivity.this,ShowTemperature.class);
                startActivity(itemp);
                break;
        }



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ilogout = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(ilogout);
            }
        });

    }
}
