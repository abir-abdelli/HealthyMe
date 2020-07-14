package com.example.healthyme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    EditText Name, emailId, password, phone;
    Button register;
    TextView loginText;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = firebaseAuth.getInstance();
        Name = findViewById(R.id.Name);
        emailId = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        loginText = findViewById(R.id.loginText);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                final String mEmail = email;
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {// User is signed in
                    Intent i = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(i);
                } else {// No user is signed in
                    if (name.isEmpty()) {
                        Name.setError("Please enter your first name");
                        Name.requestFocus();
                    }
                    if (email.isEmpty()) {
                        emailId.setError("Please enter email id");
                        emailId.requestFocus();
                    }
                    if (pwd.isEmpty()) {
                        password.setError("Please enter your password");
                        password.requestFocus();
                    } else if (pwd.length() < 8) {
                        password.setError("Password Must be >= 6 Characters");
                        return;
                    }


                    if (!(email.isEmpty() && pwd.isEmpty() && name.isEmpty())) {
                        firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                } else {

                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    CollectionReference notebookRef = db.collection(mEmail);
                                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                }
                            }
                        });
                    } else {
                        Toast.makeText(MainActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                    }
                }
                }
            });


        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
