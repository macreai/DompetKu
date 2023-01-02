package com.example.dompetku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_riwayat_transaksi.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var addPemasukkanPengeluaran: FloatingActionButton
    lateinit var addHutang: FloatingActionButton
    lateinit var addButton: ExtendedFloatingActionButton
    var isAllFABVisible by Delegates.notNull<Boolean>()

    private lateinit var deletedTransaksi: Transaksi
    private lateinit var transaksi: List<Transaksi>
    private lateinit var transaksiLama: List<Transaksi>
    private lateinit var transaksiAdapter: TransaksiAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var db: AppDatabase

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

        db = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "transaksi").allowMainThreadQueries().build()

        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deleteTransaksi(transaksi[viewHolder.adapterPosition])
            }

        }

        val swipeHelper = ItemTouchHelper(itemTouchHelper)
        swipeHelper.attachToRecyclerView(rv_list_daftar_barang)

    }

    private fun fetchAll(){
        GlobalScope.launch {
            transaksi = db.transaksiDao().getAll()

            runOnUiThread{
                updateDashboard()
                transaksiAdapter.setData(transaksi)
            }
        }

    }

    private fun updateDashboard(){
        val totalAmount = transaksi.map { it.amount }.sum()
        val budgetAmount = transaksi.filter { it.amount > 0 }.map{it.amount}.sum()
        val expenseAmount = totalAmount - budgetAmount

        balance.text = "Rp %.1f".format(totalAmount)
        budget.text = "Rp %.1f".format(budgetAmount)
        expense.text = "Rp %.1f".format(expenseAmount)
    }

    private fun deleteTransaksi(transaksii: Transaksi){
        deletedTransaksi = transaksii
        transaksiLama = transaksi

        GlobalScope.launch {
            db.transaksiDao().delete(transaksii)

            transaksi = transaksi.filter { it.id != transaksii.id }
            runOnUiThread {
                updateDashboard()
                transaksiAdapter.setData(transaksi)
                showSnackbar()
            }
        }
    }

    private fun showSnackbar() {
        val view = findViewById<View>(R.id.coordinator)
        val snackbar = Snackbar.make(view, "Transaksi Dihapus", Snackbar.LENGTH_LONG)
        snackbar.setAction("Undo"){
            undoDelete()
        }
            .setActionTextColor(ContextCompat.getColor(this,R.color.red))
            .setActionTextColor(ContextCompat.getColor(this,R.color.white))
            .show()
    }

    private fun undoDelete() {
        db.transaksiDao().insertAll(deletedTransaksi)

        transaksi = transaksiLama
        runOnUiThread{
            transaksiAdapter.setData(transaksi)
            updateDashboard()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchAll()
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
                val movePemasukkanPengeluaran = Intent(this@MainActivity, PengeluaranPemasukkan::class.java)
                startActivity(movePemasukkanPengeluaran)
            }
            R.id.btn_hutang -> {
                val moveHutang = Intent(this@MainActivity, Hutang::class.java)
                startActivity(moveHutang)
            }
        }

    }
}