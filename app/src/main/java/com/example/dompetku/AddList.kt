package com.example.dompetku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class AddList : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_list)
        val actionBar = supportActionBar
        actionBar!!.title = "Transaksi"
        actionBar.setDisplayHomeAsUpEnabled(true)

        val btnMovePengeluaran: Button = findViewById(R.id.btn_pengeluaran)
        btnMovePengeluaran.setOnClickListener(this)

        val btnMovePemasukkan: Button = findViewById(R.id.btn_pemasukkan)
        btnMovePemasukkan.setOnClickListener(this)

        val btnMoveHutang: Button = findViewById(R.id.btn_hutang)
        btnMoveHutang.setOnClickListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_pengeluaran -> {
                val movePengeluaran = Intent(this@AddList, Pengeluaran::class.java)
                startActivity(movePengeluaran)
            }
            R.id.btn_pemasukkan -> {
                val movePemasukkan = Intent(this@AddList, Pemasukkan::class.java)
                startActivity(movePemasukkan)
            }
            R.id.btn_hutang -> {
                val moveHutang = Intent(this@AddList, Hutang::class.java)
                startActivity(moveHutang)
            }

        }
    }
}