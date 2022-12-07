package com.example.dompetku

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Transaksi::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun transaksiDao(): TransaksiDao
}