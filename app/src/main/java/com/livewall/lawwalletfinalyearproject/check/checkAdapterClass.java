package com.livewall.lawwalletfinalyearproject.check;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.livewall.lawwalletfinalyearproject.R;

import java.util.ArrayList;

public class checkAdapterClass extends RecyclerView.Adapter<checkHolder> implements Filterable {

    ArrayList<CheckModelClass> data;
    Context context;
    ArrayList<CheckModelClass> backup;

    public checkAdapterClass(ArrayList<CheckModelClass> data, Context context) {
        this.data = data;
        this.context = context;
        backup = new ArrayList<>(data);
    }

    @NonNull
    @Override
    public checkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View vw = inflater.inflate(R.layout.chackitem,parent,false);
        return new checkHolder(vw);
    }

    @Override
    public void onBindViewHolder(@NonNull checkHolder holder, int position) {
        final CheckModelClass temp = data.get(position);

        holder.head.setText(temp.getHeader());
        holder.desc.setText(temp.getDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
            ArrayList<CheckModelClass> filtereddata = new ArrayList<>();

            if (keyword.toString().isEmpty())
                filtereddata.addAll(backup);
            else
            {
                for (CheckModelClass obj : backup)
                {
                    if (obj.getHeader().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                        filtereddata.add(obj);
                }
            }
            FilterResults results = new FilterResults();
            results.values=filtereddata;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            data.clear();
            data.addAll((ArrayList<CheckModelClass>) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
class checkHolder extends RecyclerView.ViewHolder {
    TextView head, desc;

    public checkHolder(@NonNull View itemView) {
        super(itemView);
        head = (TextView) itemView.findViewById(R.id.headerTxt);
        desc = (TextView) itemView.findViewById(R.id.descriptionTxt);
    }
}
