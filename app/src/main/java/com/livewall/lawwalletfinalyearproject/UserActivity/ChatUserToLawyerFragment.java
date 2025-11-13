package com.livewall.lawwalletfinalyearproject.UserActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.livewall.lawwalletfinalyearproject.Adapters.ShowLaywerforChatAdapter;
import com.livewall.lawwalletfinalyearproject.Adapters.ShowUserAdapter;
import com.livewall.lawwalletfinalyearproject.ModelClass.AddUserDetailToRealtym;
import com.livewall.lawwalletfinalyearproject.ModelClass.BookedModelClass;
import com.livewall.lawwalletfinalyearproject.R;

import java.util.ArrayList;

public class ChatUserToLawyerFragment extends Fragment {


    public ChatUserToLawyerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_chat_user_to_lawyer, container, false);
        ShowLawyers(view);
        return  view;
    }
    private void ShowLawyers(View view){
        RecyclerView recyclerView;
        recyclerView=view.findViewById(R.id.showchatdemoID);
        ArrayList<BookedModelClass> list;
        list=new ArrayList<>();
        ShowLaywerforChatAdapter adapters=new ShowLaywerforChatAdapter(list,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapters);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference ref= firebaseDatabase.getReference("Booked").child("lawyer");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        BookedModelClass  obj=dataSnapshot.getValue(BookedModelClass.class);
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