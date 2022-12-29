package com.example.dompetku

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Transaksi")
data class Transaksi(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val label: String,
    val amount: Double,
    val desc: String
): java.io.Serializable
