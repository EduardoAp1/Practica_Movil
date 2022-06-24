package com.practica.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="estado")
data class Estado(
    @PrimaryKey(autoGenerate = true)
    val IdEstado: Int,
    @ColumnInfo(name="NombreEstado")
    val NombreEstado: String,
    @ColumnInfo(name="Capital")
    val Capital: String?,
    @ColumnInfo(name="Poblacion")
    val Poblacion: Int?,
    @ColumnInfo(name="Costas")
    val Costas: String?,
) : Parcelable