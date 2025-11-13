package com.livewall.lawwalletfinalyearproject.AdminActivity.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.livewall.lawwalletfinalyearproject.Activity.SignupActivity;
import com.livewall.lawwalletfinalyearproject.Adapters.ShowLaywerAdapter;
import com.livewall.lawwalletfinalyearproject.Adapters.ShowUserAdapter;
import com.livewall.lawwalletfinalyearproject.ModelClass.AddLawyerDetailToRealtym;
import com.livewall.lawwalletfinalyearproject.ModelClass.AddUserDetailToRealtym;
import com.livewall.lawwalletfinalyearproject.R;

import java.util.ArrayList;

public class ShowUserNdLawyersFragment extends Fragment {
    ProgressDialog dialog;
    public ShowUserNdLawyersFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_approve_user_nd_lawyers, container, false);
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("please wait...");
        dialog.show();
        Getlistoflawyer(view);
        GetlistofUSer(view);
        return view;
     }
     private void Getlistoflawyer(View view){
         RecyclerView recyclerView;
         recyclerView=view.findViewById(R.id.reycleviewlaywer);
         ArrayList<AddLawyerDetailToRealtym> list;
         list=new ArrayList<>();
         ShowLaywerAdapter adapters=new ShowLaywerAdapter(list,getContext());
         recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
         recyclerView.setAdapter(adapters);
         FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
         DatabaseReference ref= firebaseDatabase.getReference("VisitProfile").child(
                 "lawyer");

         ref.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 list.clear();
                 if (snapshot.exists()){
                     for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        AddLawyerDetailToRealtym  obj=dataSnapshot.getValue(AddLawyerDetailToRealtym.class);
                         list.add(obj);
                     }
                     dialog.dismiss();
                     adapters.notifyDataSetChanged();
                 }
             }
             @Override
             public void onCancelled(@NonNull DatabaseError error) {
                 Toast.makeText(getContext(), "Error"+error, Toast.LENGTH_SHORT).show();
             }
         });
     }
    private void GetlistofUSer(View view){
        RecyclerView recyclerView;
        recyclerView=view.findViewById(R.id.reycleviewuser);
        ArrayList<AddUserDetailToRealtym> list;
        list=new ArrayList<>();
        ShowUserAdapter adapters=new ShowUserAdapter(list,getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapters);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference ref= firebaseDatabase.getReference("VisitProfile").child(
                        "Users");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        AddUserDetailToRealtym  obj=dataSnapshot.getValue(AddUserDetailToRealtym.class);
                        list.add(obj);
                    }
                    adapters.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error"+error, Toast.LENGTH_SHORT).show();
            }
        });
    }
     }
