package com.example.dompetku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import com.example.dompetku.databinding.ActivityRegistrationPageBinding

class RegistrationPage : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegistrationPageBinding
    private lateinit var edName: EditText
    private lateinit var edAge: EditText
    private lateinit var edLive: EditText
    private lateinit var edJob: EditText
    private lateinit var edMonthSal: EditText
    private lateinit var edYearSal: EditText
    private lateinit var edMarriage: RadioGroup




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

        edName = findViewById(R.id.edt_name)
        edAge = findViewById(R.id.edt_age)
        edLive = findViewById(R.id.edt_live)
        edJob = findViewById(R.id.edt_job)
        edMonthSal = findViewById(R.id.edt_month_salary)
        edYearSal = findViewById(R.id.edt_year_salary)
        edMarriage = findViewById(R.id.radio_grup)

        val btnMoveNext: Button = findViewById(R.id.next_from_regpage)
        btnMoveNext.setOnClickListener(this)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onClick(v: View?) {
        /*if (v?.id == R.id.next_from_regpage){
            val inputName = edName.text.toString().trim()
            val inputAge = edAge.text.toString().trim()
            val inputLive = edLive.text.toString().trim()
            val inputJob = edJob.text.toString().trim()
            val inputMonthSal = edMonthSal.text.toString().trim()
            val inputYearSal = edYearSal.text.toString().trim()

            var isEmptyFields = false

            if (inputName.isEmpty()){
                isEmptyFields = true
                edName.error = "Field ini tidak boleh kosong!"
            }
            if (inputAge.isEmpty()){
                isEmptyFields = true
                edAge.error = "Field ini tidak boleh kosong!"
            }
            if (inputJob.isEmpty()){
                isEmptyFields = true
                edJob.error = "Field ini tidak boleh kosong!"
            }
            if (inputLive.isEmpty()){
                isEmptyFields = true
                edLive.error = "Field ini tidak boleh kosong!"
            }
            if (inputMonthSal.isEmpty()){
                isEmptyFields = true
                edMonthSal.error = "Field ini tidak boleh kosong!"
            }
            if (inputYearSal.isEmpty()){
                isEmptyFields = true
                edYearSal.error = "Field ini tidak boleh kosong!"
            }

            if (!isEmptyFields){
                when(v?.id){
                    R.id.next_from_regpage -> {
                        val moveNext = Intent(this@RegistrationPage, RiwayatTransaksi::class.java)
                        startActivity(moveNext)
                    }
                }
            }
         */
        when(v?.id){
            R.id.next_from_regpage -> {
                val moveNext = Intent(this@RegistrationPage, RiwayatTransaksi::class.java)
                startActivity(moveNext)
            }
        }

    }
}