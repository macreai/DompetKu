package com.example.dompetku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RiwayatTransaksi : AppCompatActivity(), View.OnClickListener {

    lateinit var  floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat_transaksi)

        val btnMoveHome: ImageButton = findViewById(R.id.to_home)
        btnMoveHome.setOnClickListener(this)

        floatingActionButton = findViewById(R.id.btn_add)
        floatingActionButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.to_home -> {
                val moveHome = Intent(this@RiwayatTransaksi, MainActivity::class.java)
                startActivity(moveHome)
            }
            R.id.btn_add -> {
                val moveAdd = Intent(this@RiwayatTransaksi, AddList::class.java)
                startActivity(moveAdd)
            }

        }
    }
}