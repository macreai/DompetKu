package com.example.dompetku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveRegis: Button = findViewById(R.id.btn_regis)
        btnMoveRegis.setOnClickListener(this)

        val btnMoveResult: Button = findViewById(R.id.btn_result)
        btnMoveResult.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btn_regis -> {
                val moveRegis = Intent(this@MainActivity, RegistrationPage::class.java)
                startActivity(moveRegis)
            }
            R.id.btn_result -> {
                val moveResult = Intent(this@MainActivity, ResultPage::class.java)
                startActivity(moveResult)
            }
        }
    }

}