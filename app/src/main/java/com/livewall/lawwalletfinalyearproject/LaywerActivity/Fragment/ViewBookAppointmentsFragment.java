package com.livewall.lawwalletfinalyearproject.LaywerActivity.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.livewall.lawwalletfinalyearproject.Adapters.ViewBookingAdapter;
import com.livewall.lawwalletfinalyearproject.ModelClass.AddUserDetailToRealtym;
import com.livewall.lawwalletfinalyearproject.ModelClass.BookAppiontmentModelClass;
import com.livewall.lawwalletfinalyearproject.R;

import java.util.ArrayList;

public class ViewBookAppointmentsFragment extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;


    public ViewBookAppointmentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_view_book_appointments, container, false);
        ViewBookedAppointment(view);
        return view;
    }
    private void ViewBookedAppointment(View view){
        RecyclerView recyclerView;
        recyclerView=view.findViewById(R.id.recycleappointmentsID);
        ArrayList<BookAppiontmentModelClass> list;
        list=new ArrayList<>();
        ViewBookingAdapter adapters=new ViewBookingAdapter(list,getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapters);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        Toast.makeText(getContext(), "uid"+uid, Toast.LENGTH_SHORT).show();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference(
                "BookingDetails").child("lawyers").child(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        BookAppiontmentModelClass obj=dataSnapshot.getValue(BookAppiontmentModelClass.class);
                        list.add(obj);


                    }
                    adapters.notifyDataSetChanged();
                }else{
                    Toast.makeText(getContext(), "NO Appointment", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error"+error, Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}