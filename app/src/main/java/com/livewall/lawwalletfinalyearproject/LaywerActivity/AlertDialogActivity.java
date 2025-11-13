package com.livewall.lawwalletfinalyearproject.LaywerActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.livewall.lawwalletfinalyearproject.ModelClass.AddLawyerDetailToRealtym;
import com.livewall.lawwalletfinalyearproject.R;

public class AlertDialogActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    String uid;
    String statusapproveID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);

         Checkprofile();

//         ShowApproveprofile();

    }
    private void Checkprofile(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        uid = user.getUid();
            DatabaseReference ref= firebaseDatabase.getReference("VisitProfile").child(
                    "lawyer").child(uid);
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            AddLawyerDetailToRealtym userProfile = dataSnapshot.getValue(AddLawyerDetailToRealtym.class);
                            statusapproveID = userProfile.getStatus();
                            if (statusapproveID!=null&&statusapproveID.equals("Yes")){
                                Intent intent=new Intent(AlertDialogActivity.this,LawyerDashboard.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(AlertDialogActivity.this, "Status" + statusapproveID, Toast.LENGTH_SHORT).show();
                                Toast.makeText(AlertDialogActivity.this, "Admin dont approve your profile yet", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(AlertDialogActivity.this, "Error"+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }


}

