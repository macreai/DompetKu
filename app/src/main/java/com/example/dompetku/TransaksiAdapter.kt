package com.example.dompetku

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TransaksiAdapter(private var transaksi: List<Transaksi>):
    RecyclerView.Adapter<TransaksiAdapter.TransaksiHolder>(){

    class TransaksiHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val label: TextView = itemView.findViewById(R.id.label)
        val amount: TextView = itemView.findViewById(R.id.amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransaksiHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaksi, parent, false)
        return TransaksiHolder(view)
    }

    override fun onBindViewHolder(holder: TransaksiHolder, position: Int) {
        val transaksi = transaksi[position]
        val context = holder.amount.context

        if(transaksi.amount >= 0){
            holder.amount.text = "+ Rp%.1f".format(transaksi.amount)
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.green))
        } else {
            holder.amount.text = "- Rp%.1f".format(Math.abs(transaksi.amount))
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.red))
        }

        holder.label.text = transaksi.label
    }

    override fun getItemCount(): Int {
        return transaksi.size
    }

    fun setData(transaksi: List<Transaksi>){
        this.transaksi = transaksi
        notifyDataSetChanged()
    }
}