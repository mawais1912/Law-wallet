package com.livewall.lawwalletfinalyearproject.LaywerActivity.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.livewall.lawwalletfinalyearproject.Adapters.ShowLaywerforChatAdapter;
import com.livewall.lawwalletfinalyearproject.Adapters.ShowSeeUSerForChatAdapter;
import com.livewall.lawwalletfinalyearproject.ModelClass.BookAppiontmentModelClass;
import com.livewall.lawwalletfinalyearproject.ModelClass.BookedModelClass;
import com.livewall.lawwalletfinalyearproject.R;

import java.util.ArrayList;


public class ViewusertochatFragment extends Fragment {

    private FirebaseAuth mAuth;
    public ViewusertochatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_viewusertochat, container, false);
        ShowUser(view);
        return  view;
    }
    private void ShowUser(View view){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        RecyclerView recyclerView;
        recyclerView=view.findViewById(R.id.showuserforchatID);
        ArrayList<BookAppiontmentModelClass> list;
        list=new ArrayList<>();
        ShowSeeUSerForChatAdapter adapters=new ShowSeeUSerForChatAdapter (list,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapters);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference ref= firebaseDatabase.getReference("BookingDetails").child(
                "lawyers").child(uid);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                       BookAppiontmentModelClass  obj=dataSnapshot.getValue(BookAppiontmentModelClass.class);
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