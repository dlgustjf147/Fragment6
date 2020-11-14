package com.cookandroid.fragment6;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class AnnounceFragment extends Fragment {

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

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_announce, container, false);

       recyclerView = rootView.findViewById(R.id.recyclerView);
        announceadapter = new AnnounceAdapter();
        //announceadapter.addItem(new Announce("라민수", "010-5000-1000"));
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(AnnounceFragment.this, LinearLayoutManager.VERTICAL, false);
        //recyclerView.setAdapter(announceadapter);

        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference mref = firebaseDatabase.getReference("announce");
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

        Button button = rootView.findViewById(R.id.btn_announce);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged(1);
            }
        });

        return rootView;
    }
}
