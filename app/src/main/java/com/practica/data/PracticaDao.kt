package com.practica.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.practica.model.Estado

@Dao
interface PracticaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEstado(estado: Estado)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateEstado(estado: Estado)

    @Delete
    suspend fun deleteEstado(estado: Estado)

    @Query("SELECT * FROM estado")
    fun getAllData() : LiveData<List<Estado>>

}