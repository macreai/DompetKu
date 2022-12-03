package com.example.dompetku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edInput = findViewById(R.id.awal_saldo)

        val btnMoveNext: Button = findViewById(R.id.submit)
        btnMoveNext.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        if (v?.id == R.id.submit) {
            val inputSaldo = edInput.text.toString().trim()

            var isEmptyFields = false

            if (inputSaldo.isEmpty()) {
                isEmptyFields = true
                edInput.error = "Field ini tidak boleh kosong!"
            }
            if (!isEmptyFields) {
                when (v?.id) {
                    R.id.submit -> {
                        val moveNext = Intent(this@MainActivity, RiwayatTransaksi::class.java)
                        startActivity(moveNext)
                    }
                }
            }
        }

    }
}