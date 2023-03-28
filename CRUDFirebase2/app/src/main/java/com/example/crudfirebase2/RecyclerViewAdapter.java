package com.example.crudfirebase2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private ArrayList<data_mahasiswa> listMahasiswa;
    private Context context;
    datalistener listener;

    public RecyclerViewAdapter(ArrayList<data_mahasiswa> listMahasiswa, Context context) {
        this.listMahasiswa = listMahasiswa;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String NIM = listMahasiswa.get(position).getNim();
        final String Nama = listMahasiswa.get(position).getNama();
        final String Jurusan = listMahasiswa.get(position).getJurusan();

        holder.NIM.setText("NIM : " + NIM);
        holder.Nama.setText("Nama : " + Nama);
        holder.Jurusan.setText("Jurusan : " + Jurusan);
    }

    @Override
    public int getItemCount() {
        return listMahasiswa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView NIM, Nama, Jurusan;
        private LinearLayout ListItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NIM = itemView.findViewById(R.id.nimx);
            Nama = itemView.findViewById(R.id.namax);
            Jurusan = itemView.findViewById(R.id.jurusanx);
            ListItem = itemView.findViewById(R.id.list_item);
        }
    }

    public interface datalistener {

    }
}