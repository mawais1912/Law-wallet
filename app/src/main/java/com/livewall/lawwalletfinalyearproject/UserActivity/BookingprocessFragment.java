package com.livewall.lawwalletfinalyearproject.UserActivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.livewall.lawwalletfinalyearproject.Activity.SignupActivity;
import com.livewall.lawwalletfinalyearproject.ModelClass.AddLawyerDetailToRealtym;
import com.livewall.lawwalletfinalyearproject.ModelClass.BookAppiontmentModelClass;
import com.livewall.lawwalletfinalyearproject.ModelClass.BookedModelClass;
import com.livewall.lawwalletfinalyearproject.R;


public class BookingprocessFragment extends Fragment {

    EditText edname,ednum,edmessage;
    Button btnbook;
    ProgressDialog dialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    String uidlawyers,Imageurl,lawyersname;
    String name,num,message;
    public BookingprocessFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_bookingprocess, container, false);
        IDs(view);
        return  view;
    }
    private void IDs(View view){
        edname=view.findViewById(R.id.edfirstnamebookid);
        ednum=view.findViewById(R.id.edclientphonenumberID);
        edmessage=view.findViewById(R.id.edshortmessageID);
        btnbook=view.findViewById(R.id.applyforapppointmentID);
        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=edname.getText().toString().trim();
                num=ednum.getText().toString().trim();
                message=edmessage.getText().toString().trim();
                Bookingprocessing(name,num,message);
                dialog = new ProgressDialog(getContext());
                dialog.setMessage("Booking your Appointments ");
                dialog.setTitle("Please wait");
                dialog.show();
            }
        });
    }
    private void Bookingprocessing(String bookname,String numbook,String messagebook){
        mAuth = FirebaseAuth.getInstance();
        uidlawyers = getArguments().getString("uid");
        Imageurl = getArguments().getString("imageurl");
        lawyersname = getArguments().getString("lawyername");
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        BookAppiontmentModelClass obj =new BookAppiontmentModelClass(uid,bookname,numbook,messagebook);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("BookingDetails").child("lawyers").child(uidlawyers).child(uid);
        databaseReference.setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
             if (task.isSuccessful()){
                 dialog.dismiss();
                 UploadedLawyerforChat();
                 Toast.makeText(getContext(), "Sucessfully book", Toast.LENGTH_SHORT).show();
             }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void UploadedLawyerforChat(){
        BookedModelClass objbook=new BookedModelClass(uidlawyers,lawyersname,Imageurl);
        FirebaseDatabase firebaseDatabaseone=FirebaseDatabase.getInstance();
        DatabaseReference ref=firebaseDatabaseone.getReference("Booked").child("lawyer").child(uidlawyers);
        ref.setValue(objbook);
    }
}