package com.example.dompetku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_pengeluaran.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PengeluaranPemasukkan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengeluaran)

        labelInput.addTextChangedListener{
            if(it!!.count() > 0)
                labelLayout.error = null
        }

        amountInput.addTextChangedListener{
            if(it!!.count() > 0)
                amountLayout.error = null
        }

        addTransactionBtn.setOnClickListener{
            val barang = labelInput.text.toString()
            val desc = descriptionInput.text.toString()
            val harga = amountInput.text.toString().toDoubleOrNull()

            if (barang.isEmpty())
                labelLayout.error = "Masukkan nama barang!"
            else if (harga == null){
                amountLayout.error = "Masukkan harga barang!"
            } else{
                val transaksi = Transaksi(0, barang, harga, desc)
                insert(transaksi)
            }
        }

        closeBtn.setOnClickListener {
            finish()
        }
    }

    private fun insert(transaksi: Transaksi){
        val db = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "transaksi").build()

        GlobalScope.launch {
            db.transaksiDao().insertAll(transaksi)
            finish()
        }
    }
}