package com.example.projectandroidmas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KlubAdapter (var mContext: Context, var klubList: List<DataClub>):

    RecyclerView.Adapter<KlubAdapter.ListViewHolder>() {

    inner class ListViewHolder(var v: View) : RecyclerView.ViewHolder(v) {

        val imgTT = v.findViewById<ImageView>(R.id.imgKlub)

        val judulTT = v.findViewById<TextView>(R.id.jdlKlub)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KlubAdapter.ListViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.datadata, parent, false)
        return ListViewHolder(v)
    }

    override fun onBindViewHolder(holder: KlubAdapter.ListViewHolder, position: Int) {
        val newList = klubList[position]
        holder.imgTT.loadImage(newList.gambar)
        holder.judulTT.text = newList.nama

        holder.v.setOnClickListener {

            val desk = newList.deskripsi
            val klub = newList.nama
            val gambar = newList.gambar

            val mIntent = Intent(mContext,DeskripsiKlub::class.java)
            mIntent.putExtra("DESKRIPSI", desk)
            mIntent.putExtra("KLUB", klub)
            mIntent.putExtra("GAMBAR", gambar)
            mContext.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int {
        return klubList.size
    }


}


