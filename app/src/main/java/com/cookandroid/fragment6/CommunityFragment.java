package com.cookandroid.fragment6;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommunityFragment extends Fragment {

    private RecyclerView recyclerView;
    private AnnounceAdapter announceadapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Announce> items = new ArrayList<>();

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private String st;
    private String st2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_community, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView_community);
        announceadapter = new AnnounceAdapter();
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference mref = firebaseDatabase.getReference("community");
        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    st = ds.getKey();
                    st2 = ds.child("title").getValue().toString();
                    announceadapter.addItem(new Announce(st, st2));
                    recyclerView.setAdapter(announceadapter);
                    //textView.setText(st);
                    //textView2.setText(st);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        Button button = rootView.findViewById(R.id.btn_community);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged(1);
            }
        });

        Button button2 = rootView.findViewById(R.id.btn_write);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AnnConActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
