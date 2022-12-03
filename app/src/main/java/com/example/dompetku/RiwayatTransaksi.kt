package com.example.dompetku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.properties.Delegates

class RiwayatTransaksi : AppCompatActivity(), View.OnClickListener {

    lateinit var addPemasukkanPengeluaran: FloatingActionButton
    lateinit var addHutang: FloatingActionButton
    lateinit var addButton: ExtendedFloatingActionButton
    var isAllFABVisible by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat_transaksi)

        addPemasukkanPengeluaran = findViewById(R.id.btn_pemasukkan_pengeluaran)
        addHutang = findViewById(R.id.btn_hutang)
        addButton = findViewById(R.id.btn_add)

        addPemasukkanPengeluaran.visibility = View.GONE
        addHutang.visibility = View.GONE

        isAllFABVisible = false

        addButton.shrink()
        addButton.setOnClickListener(this)
        addPemasukkanPengeluaran.setOnClickListener(this)
        addHutang.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (!isAllFABVisible){
            addPemasukkanPengeluaran.show()
            addHutang.show()
            addButton.extend()
            isAllFABVisible = true
        }else{
            addPemasukkanPengeluaran.hide()
            addHutang.hide()
            addButton.shrink()
            isAllFABVisible = false
        }

        when (v?.id){
            R.id.btn_pemasukkan_pengeluaran -> {
                val movePemasukkanPengeluaran = Intent(this@RiwayatTransaksi, Pengeluaran::class.java)
                startActivity(movePemasukkanPengeluaran)
            }
            R.id.btn_hutang -> {
                val moveHutang = Intent(this@RiwayatTransaksi, Hutang::class.java)
                startActivity(moveHutang)
            }
        }

    }



}