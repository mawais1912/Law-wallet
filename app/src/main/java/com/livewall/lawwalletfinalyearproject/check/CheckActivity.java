package com.livewall.lawwalletfinalyearproject.check;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.livewall.lawwalletfinalyearproject.R;

public class CheckActivity extends AppCompatActivity {
    Button checkBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        checkBtn = (Button) findViewById(R.id.sButton);

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSupportFragmentManager().beginTransaction().replace(R.id.frameC,new checkFragment()).commit();
            }
        });
    }
}