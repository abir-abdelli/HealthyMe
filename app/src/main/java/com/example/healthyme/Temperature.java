package com.example.healthyme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Temperature extends AppCompatActivity {
    EditText phone,name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        phone = findViewById(R.id.phone);
        name = findViewById(R.id.name);
        Button send = findViewById(R.id.button);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mphone = phone.getText().toString();
                String mname = name.getText().toString();
                if (phone != null) {
                    SmsManager smsManager = SmsManager.getDefault();
                    String message = " HealthMe App: the patient " + name + "is having temperature > 40, please check on him";

                    if (mphone == "0") {
                        mphone =  "71249029";
                        smsManager.sendTextMessage( mphone, null,message, null, null);
                        Toast.makeText(Temperature.this, "sent!",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Temperature.this, "message was not sent because phone or name  was empty!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
