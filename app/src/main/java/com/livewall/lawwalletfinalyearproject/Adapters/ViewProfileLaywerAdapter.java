package com.livewall.lawwalletfinalyearproject.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.livewall.lawwalletfinalyearproject.AdminActivity.Fragment.ShowuserandLawyerDetailsFragment;
import com.livewall.lawwalletfinalyearproject.ModelClass.AddLawyerDetailToRealtym;
import com.livewall.lawwalletfinalyearproject.R;
import com.livewall.lawwalletfinalyearproject.UserActivity.ShowLawyerProFileDetailsToUserFragment;

import java.util.ArrayList;

public class ViewProfileLaywerAdapter extends RecyclerView.Adapter<ViewProfileLaywerAdapter.Viewholder> {
    ArrayList<AddLawyerDetailToRealtym> data;
    Context context;

    public ViewProfileLaywerAdapter(ArrayList<AddLawyerDetailToRealtym> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewProfileLaywerAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.showviewprofilelawyerlistlayout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewProfileLaywerAdapter.Viewholder holder, int position) {
        final AddLawyerDetailToRealtym temp=data.get(position);
        holder.name.setText(temp.getLawyername());
        Glide.with(context.getApplicationContext()).load(temp.getImageuri()).into(holder.profileimage);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Bundle args = new Bundle();
                ShowLawyerProFileDetailsToUserFragment nextFrag= new ShowLawyerProFileDetailsToUserFragment();
                args.putString("uid", temp.getCurrentuid());
                args.putString("imageurl", temp.getImageuri());
                args.putString("lawyername",temp.getLawyername());
                nextFrag.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framelayoutid, nextFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView profileimage;
        TextView name;
        CardView cardView;
        Button btn;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            profileimage=itemView.findViewById(R.id.createprofileimageid);
            name=itemView.findViewById(R.id.userprofilenameid);
            cardView=itemView.findViewById(R.id.cardprofileid);
            btn=itemView.findViewById(R.id.btnviewprofileID);
        }
    }
}
