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

public class AdapterSiswaKelas extends RecyclerView.Adapter<AdapterSiswaKelas.HolderItem> {

    List<MSiswa> mSiswaList ;
    Context context;

    public AdapterSiswaKelas(List<MSiswa> mSiswaList, Context context){
        this.mSiswaList = mSiswaList;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.listsiswa,parent,false);
        HolderItem holder = new HolderItem(layout);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderItem holder, int position) {
        MSiswa mSiswa = mSiswaList.get(position);

        holder.id_kelas.setText(mSiswa.getid_kelas());
        holder.namasiswalist.setText(mSiswa.getnama_siswa());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, mSiswa.getnama_siswa(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DaftarHafalan.class);
                intent.putExtra("nama_siswa", mSiswa.getnama_siswa());
                intent.putExtra("nisn", mSiswa.getnisn());
                intent.putExtra("id_kelas", mSiswa.getid_kelas());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSiswaList.size();
    }

    public class HolderItem extends  RecyclerView.ViewHolder{
        LinearLayout layoutsiswa;
        TextView id_kelas, namasiswalist;

        public HolderItem(View v){
            super(v);
            id_kelas= (TextView) v.findViewById(R.id.id_kelas);
            namasiswalist = (TextView) v.findViewById(R.id.namasiswalist);
            layoutsiswa = (LinearLayout) v.findViewById(R.id.layoutsiswa);

        }
    }
}
