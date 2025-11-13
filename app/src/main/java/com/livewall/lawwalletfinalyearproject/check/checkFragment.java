package com.livewall.lawwalletfinalyearproject.check;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.livewall.lawwalletfinalyearproject.R;

import java.util.ArrayList;

public class checkFragment extends Fragment {
    RecyclerView recyclerViewC;
    checkAdapterClass cAdapter;
    EditText searchBox;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check, container, false);

        searchBox = (EditText) view.findViewById(R.id.searchEdtxt);
        recyclerViewC = (RecyclerView) view.findViewById(R.id.recViewCheck);
        
        checkSitems();

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                try {
                    cAdapter.getFilter().filter(charSequence);
                }catch (Exception e)
                {

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    private void checkSitems() {

        ArrayList<CheckModelClass> objl = new ArrayList<>();

        objl.add(new CheckModelClass("Shahroz","He is my teacher and he is very talented"));
        objl.add(new CheckModelClass("Ameeq","He is my friend and he is very talented"));
        objl.add(new CheckModelClass("Abdullah","He is my friend and he is very talented"));
        objl.add(new CheckModelClass("Edify","My institution"));
        objl.add(new CheckModelClass("Mudasir","He is my fellow Developer and he is very talented"));
        objl.add(new CheckModelClass("Rizwan","He is my fellow Developer and he is very talented"));
        objl.add(new CheckModelClass("Ahmed","He is my fellow Developer and he is very talented"));
        objl.add(new CheckModelClass("Sulemain","He is my fellow Developer and he is very talented"));
        objl.add(new CheckModelClass("Sir Ahmed","He is graphic Designer and he is very talented"));
        objl.add(new CheckModelClass("Sulemain","He is my fellow Developer and he is very talented"));


        recyclerViewC.setLayoutManager(new GridLayoutManager(getContext(), 2));
        cAdapter = new checkAdapterClass(objl,getContext());
        recyclerViewC.setAdapter(cAdapter);
    }


}