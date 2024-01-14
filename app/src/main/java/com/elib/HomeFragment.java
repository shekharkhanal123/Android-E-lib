package com.elib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {
    Button dip,man,sci;
    Activity context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        context = getActivity();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        dip = context.findViewById(R.id.dipbtn);
        sci = context.findViewById(R.id.scibtn);
        man = context.findViewById(R.id.manbtn);

        Intent dipnav = new Intent(context,diploma.class);
        Intent scinav = new Intent(context, science.class);
        Intent mannav = new Intent(context, management.class);

        dip.setOnClickListener(v -> {
            startActivity(dipnav);
        });

        sci.setOnClickListener(v -> {
            startActivity(scinav);
        });

        man.setOnClickListener(v -> {
            startActivity(mannav);
        });
    }
}