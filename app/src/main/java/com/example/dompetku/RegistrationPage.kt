package com.example.dompetku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import com.example.dompetku.databinding.ActivityRegistrationPageBinding

class RegistrationPage : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegistrationPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar!!.title = "DompetKu"
        actionBar.setDisplayHomeAsUpEnabled(true)

        val items = listOf(1, 2, 3, "lebih dari 3")
        val adapter = ArrayAdapter(this, R.layout.list_anak, items)
        binding.dropdownMenu.setAdapter(adapter)

        val btnMoveNext: Button = findViewById(R.id.next_from_regpage)
        btnMoveNext.setOnClickListener(this)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.next_from_regpage -> {
                val moveNext = Intent(this@RegistrationPage, RiwayatTransaksi::class.java)
                startActivity(moveNext)
            }
        }
    }
}