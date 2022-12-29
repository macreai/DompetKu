package com.example.dompetku

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_pengeluaran.*
import kotlinx.android.synthetic.main.activity_pengeluaran.amountInput
import kotlinx.android.synthetic.main.activity_pengeluaran.amountLayout
import kotlinx.android.synthetic.main.activity_pengeluaran.closeBtn
import kotlinx.android.synthetic.main.activity_pengeluaran.descriptionInput
import kotlinx.android.synthetic.main.activity_pengeluaran.labelInput
import kotlinx.android.synthetic.main.activity_pengeluaran.labelLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var  transaksi: Transaksi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        transaksi = intent.getSerializableExtra("transaksi") as Transaksi

        labelInput.setText(transaksi.label)
        amountInput.setText(transaksi.amount.toString())
        descriptionInput.setText(transaksi.desc)

        rootView.setOnClickListener{
            this.window.decorView.clearFocus()

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }

        labelInput.addTextChangedListener{
            updateBtn.visibility = View.VISIBLE
            if(it!!.count() > 0)
                labelLayout.error = null
        }

        amountInput.addTextChangedListener{
            updateBtn.visibility = View.VISIBLE
            if(it!!.count() > 0)
                amountLayout.error = null
        }

        descriptionInput.addTextChangedListener{
            updateBtn.visibility = View.VISIBLE
        }

        updateBtn.setOnClickListener{
            val barang = labelInput.text.toString()
            val desc = descriptionInput.text.toString()
            val harga = amountInput.text.toString().toDoubleOrNull()

            if (barang.isEmpty())
                labelLayout.error = "Masukkan nama barang!"
            else if (harga == null){
                amountLayout.error = "Masukkan harga barang!"
            } else{
                val transaksi = Transaksi(transaksi.id, barang, harga, desc)
                update(transaksi)
            }
        }

        closeBtn.setOnClickListener {
            finish()
        }
    }

    private fun update(transaksi: Transaksi){
        val db = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "transaksi").build()

        GlobalScope.launch {
            db.transaksiDao().update(transaksi)
            finish()
        }
    }
}