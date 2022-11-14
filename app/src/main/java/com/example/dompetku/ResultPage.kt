package com.example.dompetku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ResultPage : AppCompatActivity(), View.OnClickListener {

    lateinit var  floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)

        floatingActionButton = findViewById(R.id.btn_add_new)
        floatingActionButton.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_add_new -> {
                val moveRegist = Intent(this@ResultPage, RegistrationPage::class.java)
                startActivity(moveRegist)
            }
        }
    }


}