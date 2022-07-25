package com.example.uaskmmizena;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.HolderItem>{
    List<MHistory> mHistoryList ;
    Context context;

    public AdapterHistory(List<MHistory> mHistoryList, Context context){
        if (mHistoryList.size()>0){
            this.mHistoryList.clear();
        }
        this.mHistoryList= mHistoryList;
        this.context = context;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public HolderItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.listhafalan,parent,false);
        HolderItem holder = new HolderItem(layout);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderItem holder, int position) {
        MHistory mHistory = mHistoryList.get(position);
        holder.nisn.setText(mHistory.getNisn());
        holder.surat.setText(mHistory.getSurat());
        holder.ayat.setText(mHistory.getAyat());
        holder.tanggal.setText(mHistory.getTanggal());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InputHafalan.class);
                intent.putExtra("id_hafalan", mHistory.getId_hafalan());
                intent.putExtra("surat", mHistory.getSurat());
                intent.putExtra("nama_siswa", mHistory.getNamasiswa());
                intent.putExtra("nisn", mHistory.getNisn());
                intent.putExtra("tanggal", mHistory.getTanggal());
                intent.putExtra("ayat", mHistory.getAyat());
                context.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(mHistory.getId_hafalan());
                String tgl = holder.tanggal.getText().toString();
                String surat = holder.surat.getText().toString();
                String nisn = holder.nisn.getText().toString();
                String ayat = holder.ayat.getText().toString();

                new DbHelper(0).execute(id,surat,ayat,nisn,tgl);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHistoryList.size();
    }

    public class HolderItem extends  RecyclerView.ViewHolder{
        TextView surat, ayat, tanggal, nisn, edit, delete;

        public HolderItem(View v){
            super(v);
            edit = (TextView) v.findViewById(R.id.edit);
            delete = (TextView) v.findViewById(R.id.delete);
            nisn = (TextView) v.findViewById(R.id.nisnH);
            surat = (TextView) v.findViewById(R.id.surat);
            ayat = (TextView) v.findViewById(R.id.ayat);
            tanggal = (TextView) v.findViewById(R.id.tanggal);

        }
    }
}
