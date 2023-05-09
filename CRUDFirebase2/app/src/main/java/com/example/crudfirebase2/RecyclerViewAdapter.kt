package com.example.crudfirebase2

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(
    private val listMahasiswa: ArrayList<data_mahasiswa>,
    private var context: Context
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    //    var listener: datalistener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val V = LayoutInflater.from(parent.context).inflate(R.layout.view_design, parent, false)
        return ViewHolder(V)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val NIM = listMahasiswa[position].nim
        val Nama = listMahasiswa[position].nama
        val Jurusan = listMahasiswa[position].jurusan
        val JenisKelamin = listMahasiswa[position].jenisKelamin
        val Alamat = listMahasiswa[position].alamat
        holder.NIM.text = "NIM : $NIM"
        holder.Nama.text = "Nama : $Nama"
        holder.Jurusan.text = "Jurusan : $Jurusan"
        holder.JenisKelamin.text = "Jenis Kelamin : $JenisKelamin"
        holder.Alamat.text = "Alamat : $Alamat"

        holder.ListItem.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                //Kodingan untuk membuat fungsi Edit dan Delete, yang akan dibahas pada Tutorial Berikutnya .
                holder.ListItem.setOnLongClickListener { view ->
                    val action = arrayOf("Update", "Delete")
                    val alert: AlertDialog.Builder = AlertDialog.Builder(view.context)
                    alert.setItems(action, DialogInterface.OnClickListener { dialog, i ->
                        when (i) {
                            0 -> {
                                /* Berpindah Activity pada halaman layout updateData dan mengambil data pada
                               listMahasiswa, berdasarkan posisinya untuk dikirim pada activity selanjutnya */
                                val bundle = Bundle()
                                bundle.putString("dataNIM", listMahasiswa[position].nim)
                                bundle.putString("dataNama", listMahasiswa[position].nama)
                                bundle.putString("dataJurusan", listMahasiswa[position].jurusan)
                                bundle.putString(
                                    "dataJenisKelamin",
                                    listMahasiswa[position].jenisKelamin
                                )
                                bundle.putString("dataAlamat", listMahasiswa[position].alamat)
                                bundle.putString("getPrimaryKey", listMahasiswa[position].key)
                                val intent = Intent(view.context, UpdateData::class.java)
                                intent.putExtras(bundle)
                                context.startActivity(intent)
                            }
                            1 -> {
                            }
                        }
                    })
                    alert.create()
                    alert.show()
                    true
                }
                return true;
            }
        })

    }

    override fun getItemCount(): Int {
        return listMahasiswa.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val NIM: TextView
        val Nama: TextView
        val Jurusan: TextView
        val JenisKelamin: TextView
        val Alamat: TextView
        val ListItem: LinearLayout

        init {
            NIM = itemView.findViewById(R.id.nimx)
            Nama = itemView.findViewById(R.id.namax)
            Jurusan = itemView.findViewById(R.id.jurusanx)
            JenisKelamin = itemView.findViewById(R.id.jenisKelaminx)
            Alamat = itemView.findViewById(R.id.alamatx)
            ListItem = itemView.findViewById(R.id.list_item)
        }
    }

    //Membuat Interfece
    interface dataListener {
        fun onDeleteData(data: data_mahasiswa?, position: Int)
    }

    //Deklarasi objek dari Interfece
    var listener: dataListener? = null

    //Membuat Konstruktor, untuk menerima input dari Database
    init {
        this.context = context
        this.listener = context as MyListData //menambahkan baris ini saja
    }


}