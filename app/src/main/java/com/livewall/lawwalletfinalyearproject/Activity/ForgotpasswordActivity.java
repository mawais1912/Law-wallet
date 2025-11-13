package com.livewall.lawwalletfinalyearproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.livewall.lawwalletfinalyearproject.R;

public class ForgotpasswordActivity extends AppCompatActivity {
    Button btnsnnemail;
    TextView tvlogin;
    EditText edemail,edfunlllname;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        IDs();
        Clicklistener();
    }

    private void IDs(){
        btnsnnemail=findViewById(R.id.btnforgotid);
        edemail=findViewById(R.id.edemailid);
        tvlogin=findViewById(R.id.signuptvid);
    }
    private void Clicklistener(){
        btnsnnemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edemail.getText().toString().isEmpty()
                ) {
                    Toast.makeText(ForgotpasswordActivity.this, "Enter valid Email", Toast.LENGTH_SHORT).show();
                } else if (!edemail.getText().toString().contains("@gmail.com")) {
                    Toast.makeText(ForgotpasswordActivity.this, "Please Enter valid Email", Toast.LENGTH_SHORT).show();
                } else {
                    ValidData(edemail.getText().toString());
                }
            }
        });
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SigninActivity.class));
            }
        });
    }
    private void ValidData(String email){
        mAuth = FirebaseAuth.getInstance();
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotpasswordActivity.this, "PLz Check your email", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ForgotpasswordActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}