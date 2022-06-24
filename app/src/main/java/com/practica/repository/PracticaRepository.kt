package com.practica.repository

import androidx.lifecycle.LiveData
import com.practica.data.PracticaDao
import com.practica.model.Estado

class PracticaRepository(private val practicaDao: PracticaDao) {
    suspend fun addEstado(estado: Estado){
        practicaDao.addEstado(estado)
    }

    suspend fun updateEstado(estado: Estado){
        practicaDao.updateEstado(estado)
    }

    suspend fun deleteEstado(estado: Estado){
        practicaDao.deleteEstado(estado)
    }

    val getAllData : LiveData<List<Estado>> = practicaDao.getAllData()
}