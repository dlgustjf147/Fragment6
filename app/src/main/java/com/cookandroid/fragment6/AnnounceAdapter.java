package com.cookandroid.fragment6;

import android.content.Intent;
import android.util.Log;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AnnounceAdapter extends RecyclerView.Adapter<AnnounceAdapter.ViewHolder> {
    private static Context context;
    //private static Context context;
    ArrayList<Announce> items = new ArrayList<Announce>();

    //여기부터~
    // 리스너 객체 참조를 저장하는 변수
    private AdapterView.OnItemClickListener mListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.mListener = listener ;
    }
    //~여기까지

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.announce_item, parent, false);
        // ㄴ인플레이션을 통해 뷰 객체 만들기
        return new ViewHolder(itemView);
        // ㄴ뷰홀더 객체를 생성하면서 뷰 객체를 전달하고 그 뷰홀더 객체를 반환하기
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Announce item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;

        // 뷰 홀더 생성자로 전달되는 뷰 객체 참조하기
        public ViewHolder(View itemView) { // View itemVIew
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);

            // 추가
            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        //Intent intent = new Intent(v.getContext(), AnnConActivity.class);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        v.getContext().startActivity(new Intent(AnnounceAdapter.context, AnnConActivity.class));
                    }
                }
            });
/*
            textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        v.getContext().startActivity(new Intent(AnnounceAdapter.context, AnnConActivity.class));
                    }
                }
            });*/

/*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    //여기부터~
                    if(pos != RecyclerView.NO_POSITION) {
                        //Intent intent = new Intent(v.getContext(), AnnounceContentFragment.class);
                        //v.getContext().startActivity(intent);
                    }
                    //~여기까지
                }
            });
            */

        }

        public void setItem(Announce item) {
            textView.setText(item.getNumber());
            textView2.setText(item.getTitle());
        }
    }

    public void addItem(Announce item) {
        items.add(item);
    }

    public void setItems(ArrayList<Announce> items) {
        this.items = items;
    }

    public Announce getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Announce item) {
        items.set(position, item);
    }

}