package com.example.crudfirebase2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

    public boolean onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String NIM = listMahasiswa.get(position).getNim();
        final String Nama = listMahasiswa.get(position).getNama();
        final String Jurusan = listMahasiswa.get(position).getJurusan();

        holder.NIM.setText("NIM : " + NIM);
        holder.Nama.setText("Nama : " + Nama);
        holder.Jurusan.setText("Jurusan : " + Jurusan);

        holder.ListItem.setOnLongClickListener(object:View.OnLongClickListener {
            override fun onLongClick(v:View ?):Boolean {
                holder.ListItem.setOnLongClickListener {
                    view ->
                            val action = arrayOf("Update", "Delete")
                    val alert:AlertDialog.Builder = AlertDialog.Builder(view.context)
                    alert.setItems(action, DialogInterface.OnClickListener {
                        dialog, i ->
                                when(i);
                        {
                            0 ->{
                            val bundle = Bundle();

                            bundle.putString("dataNIM", listMahasiswa.get(position).nim)
                            bundle.putString("dataNama", listMahasiswa.get(position).nama)
                            bundle.putString("dataJurusan", listMahasiswa.get(position).jurusan)
                            bundle.putString("getPrimaryKey", listMahasiswa.get(position).key)
                            val intent = Intent(view.context, UpdateData:: class.java)
                            intent.putExtras(bundle)
                            context.startActivity(intent)
                        }
                            1 ->{
                        }
                        }
                    })
                    alert.create()
                    alert.show()
                    true
                }
                return true;
            }
        }
    }

    private val Bundle() {
    }
}
;
                    }

@Override
public int getItemCount(){
        return listMahasiswa.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder {
    public View ListItem;
    private TextView NIM, Nama, Jurusan;

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