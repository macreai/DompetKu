package com.example.dompetku

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TransaksiDao {
    @Query("SELECT * FROM transaksi")
    fun getAll(): List<Transaksi>

    @Insert
    fun insertAll(vararg transaksi: Transaksi)

    @Delete
    fun delete(transaksi: Transaksi)

    @Update
    fun update(vararg transaksi: Transaksi)
}