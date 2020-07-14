package com.example.healthyme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

import static java.lang.String.valueOf;

public class NewMedication extends AppCompatActivity implements  View.OnClickListener {

    private int notifId = 1;
    LoginActivity Login = new LoginActivity();
    String id = Login.getAddr().getText().toString();
    String path = id + "/medications";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference medications = db.collection(path);//each user has a collection called after his address mail.

    EditText programName, desc, mdrugName;
    TimePicker timePicker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medication);
        // Set Onclick Listener.
        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        programName = findViewById(R.id.programName);
        mdrugName = findViewById(R.id.drugName);
        desc = findViewById(R.id.description);
        timePicker = findViewById(R.id.timePicker);

        // Set notificationId & text.
        Intent intent = new Intent(NewMedication.this, AlarmReceiver.class);
        intent.putExtra("notificationId", notifId);
        intent.putExtra("prog", programName.getText().toString());
        intent.putExtra("drug", mdrugName.getText().toString());
        intent.putExtra("todo", desc.getText().toString());

        // getBroadcast(context, requestCode, intent, flags)
        PendingIntent alarmIntent = PendingIntent.getBroadcast(NewMedication.this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

        switch (view.getId()) {
            case R.id.setBtn:

                //Setting the alarm
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                // Create time.
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                // Set alarm.
                // set(type, milliseconds, intent)
                alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);

                //adding the medication to medication list in Firebase
                String mProgram = programName.getText().toString();
                String drugName = mdrugName.getText().toString();
                String mdesc = desc.getText().toString();
                String date = valueOf(hour) + ":" + valueOf(minute);

                Medication medication = new Medication(mProgram, drugName, mdesc, date);
                medications.add(medication);

                Intent i = new Intent(NewMedication.this, HomeActivity.class);
                startActivity(i);

                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelBtn:
                alarm.cancel(alarmIntent);
                Toast.makeText(this, "Canceled.", Toast.LENGTH_SHORT).show();
                break;
        }



    }
}
