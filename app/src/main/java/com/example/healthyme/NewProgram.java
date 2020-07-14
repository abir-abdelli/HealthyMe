package com.example.healthyme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;

public class NewProgram extends AppCompatActivity  implements  View.OnClickListener {
    private static final String TAG = "NewProgram";

    private TextView DisplayStartDate, DisplayEndDate;
    private DatePickerDialog.OnDateSetListener mDateSetListenerStart, mDateSetListenerEnd;
    public String dateS, dateE;
    public Date date1, date2;
    EditText programName, illness;

    LoginActivity Login = new LoginActivity();
    String id = Login.getAddr().getText().toString();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String path = id + "/programs";
    private CollectionReference Programs = db.collection(path);//each user has a collection which is called after his address mail.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_program);

        findViewById(R.id.DisplayStartDate).setOnClickListener(this);
        findViewById(R.id.DisplayEndDate).setOnClickListener(this);
        findViewById(R.id.save).setOnClickListener(this);

        programName = findViewById(R.id.programName);
        illness = findViewById(R.id.illness);


        mDateSetListenerStart = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                dateS = month + "/" + day + "/" + year;
                date1 = new Date(year,month,day);
                DisplayStartDate.setText(dateS);
            }
        };

        mDateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                dateE = month + "/" + day + "/" + year;
                date1 = new Date(year,month,day);
                if ( date2.compareTo(date1) == -1 ) {       //the end date has to be after the start date
                    DisplayEndDate.setError("Please enter your first name");
                }
                else {
                    DisplayEndDate.setText(dateE);
                }

            }
        };


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.DisplayStartDate:
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        NewProgram.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListenerStart,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;

            case R.id.DisplayEndDate:
                Calendar cal1 = Calendar.getInstance();
                int year1 = cal1.get(Calendar.YEAR);
                int month1 = cal1.get(Calendar.MONTH);
                int day1 = cal1.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog1 = new DatePickerDialog(
                        NewProgram.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListenerEnd,
                        year1,month1,day1);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog1.show();
                break;

            case R.id.addDrug:
                String mProgram = programName.getText().toString();
                String millness = illness.getText().toString();

                Program program = new Program(mProgram, millness, dateE, dateS);
                Programs.add(program);

                Intent i = new Intent(NewProgram.this, HomeActivity.class);
                startActivity(i);
                break;
        }

    }



    }


