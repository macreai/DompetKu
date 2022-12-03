package com.example.dompetku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.properties.Delegates
import kotlinx.android.synthetic.main.activity_riwayat_transaksi.*

class RiwayatTransaksi : AppCompatActivity(), View.OnClickListener {

    lateinit var addPemasukkanPengeluaran: FloatingActionButton
    lateinit var addHutang: FloatingActionButton
    lateinit var addButton: ExtendedFloatingActionButton
    var isAllFABVisible by Delegates.notNull<Boolean>()

    private lateinit var transaksi: ArrayList<Transaksi>
    private lateinit var transaksiAdapter: TransaksiAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

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

        transaksi = arrayListOf()

        transaksiAdapter = TransaksiAdapter(transaksi)
        linearLayoutManager = LinearLayoutManager(this)

        rv_list_daftar_barang.apply {
            adapter = transaksiAdapter
            layoutManager = linearLayoutManager
        }

        updateDashboard()

    }

    private fun updateDashboard(){
        val totalAmount = transaksi.map { it.amount }.sum()
        val budgetAmount = transaksi.filter { it.amount > 0 }.map{it.amount}.sum()
        val expenseAmount = totalAmount - budgetAmount

        budget.text = "Rp %.2f".format(budgetAmount)
        expense.text = "Rp %.2f".format(expenseAmount)
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
                val movePemasukkanPengeluaran = Intent(this@RiwayatTransaksi, PengeluaranPemasukkan::class.java)
                startActivity(movePemasukkanPengeluaran)
            }
            R.id.btn_hutang -> {
                val moveHutang = Intent(this@RiwayatTransaksi, Hutang::class.java)
                startActivity(moveHutang)
            }
        }

    }



}