package com.livewall.lawwalletfinalyearproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.livewall.lawwalletfinalyearproject.MainActivity;
import com.livewall.lawwalletfinalyearproject.R;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
public class SigninActivity extends AppCompatActivity {
    Spinner spinner;
    private FirebaseAuth mAuth;
    String currentstatus;
    EditText edemail,edpass;
    TextView tvforgotpass,tvsignup;
    Button btnlogin;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        // calling method()
        Ids();
        clickListener();

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Lawyer");
        arrayList.add("User");
        arrayList.add("Admin");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentstatus = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected:"+currentstatus,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

    }
    private void Ids(){
        spinner = findViewById(R.id.spinner);
        edemail=findViewById(R.id.edemailid);
        edpass=findViewById(R.id.edpasswordid);
        tvforgotpass=findViewById(R.id.forgotpasswordid);
        btnlogin=findViewById(R.id.btnloginid);
        tvsignup=findViewById(R.id.signuptvid);
    }
    private void clickListener(){
        tvforgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ForgotpasswordActivity.class));
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if (edemail.getText().toString().isEmpty() ||
                     edpass.getText().toString().isEmpty()){
                 Toast.makeText(SigninActivity.this, "Enter Email and password", Toast.LENGTH_SHORT).show();
             }else if(!edemail.getText().toString().contains("@gmail.com")){
                 Toast.makeText(SigninActivity.this, "Please Enter valid Email", Toast.LENGTH_SHORT).show();
             }else if(edpass.getText().toString().length()<6){
                 Toast.makeText(SigninActivity.this, "Please Enter valid Password", Toast.LENGTH_SHORT).show();
             }
             else{
                 dialog = new ProgressDialog(SigninActivity.this);
                 dialog.setMessage("please wait...");
                 dialog.show();
                 SignIn(edemail.getText().toString(),edpass.getText().toString());
             }
            }
        });
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignupActivity.class));
            }
        });
    }
    private void SignIn(String email,String pass){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent=new Intent(getApplicationContext(),DashboardActivity.class);
                    intent.putExtra("Status",currentstatus);
                    Toast.makeText(SigninActivity.this, "welcome"+currentstatus, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    dialog.dismiss();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SigninActivity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}

