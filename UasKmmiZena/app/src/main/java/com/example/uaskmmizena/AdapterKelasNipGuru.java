package com.example.uaskmmizena;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterKelasNipGuru extends RecyclerView.Adapter<AdapterKelasNipGuru.HolderItem> {
    List<MKelas> mKelasList ;
    Context context;

    public AdapterKelasNipGuru(List<MKelas> mKelasList, Context context){
        this.mKelasList = mKelasList;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.listkelas,parent,false);
        HolderItem holder = new HolderItem(layout);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderItem holder, int position) {
        MKelas mKelas = mKelasList.get(position);

        holder.nip_guru.setText(mKelas.getnip_guru());
        holder.namakelaslist.setText(mKelas.getnama_kelas());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, mKelas.getnama_kelas(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DaftarSiswa.class);
                intent.putExtra("nama_kelas", mKelas.getnama_kelas());
                intent.putExtra("id_kelas", mKelas.getid_kelas());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mKelasList.size();
    }

    public class HolderItem extends  RecyclerView.ViewHolder{
        LinearLayout layoutkelas;
        TextView nip_guru, namakelaslist;

        public HolderItem(View v){
            super(v);
            nip_guru = (TextView) v.findViewById(R.id.nipguru);
            namakelaslist = (TextView) v.findViewById(R.id.namakelaslist);
            layoutkelas = (LinearLayout) v.findViewById(R.id.layoutkelas);

        }
    }
}
