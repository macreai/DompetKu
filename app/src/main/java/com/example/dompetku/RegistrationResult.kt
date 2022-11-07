package com.example.dompetku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class RegistrationResult : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_result)

        val btnMoveRegis: Button = findViewById(R.id.btn_regis)
        btnMoveRegis.setOnClickListener(this)

        val btnMoveResult: Button = findViewById(R.id.btn_result)
        btnMoveResult.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btn_result -> {
                val moveResult = Intent(this@RegistrationResult, RegistrationPage::class.java)
            }
        }
    }
}