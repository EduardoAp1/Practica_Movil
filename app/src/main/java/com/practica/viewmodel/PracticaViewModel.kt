package com.practica.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.practica.data.PracticaDatabase
import com.practica.model.Estado
import com.practica.repository.PracticaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PracticaViewModel(application: Application) : AndroidViewModel(application) {
    //Atributo para acceder al repositorio de lugar
    private val repository: PracticaRepository

    //Atributo para obtener la lista de lugares en un ArrayList especial
    //LiveData permite crear hilos que estan observando si un lugar es afectado por un CRUD, esta permite poner observadores
    val getAllData: LiveData<List<Estado>>

    //Bloque de inicialización de los atributos
    init{
        val practicaDao = PracticaDatabase.getDataBase(application).practicaDao()
        repository = PracticaRepository(practicaDao)
        getAllData = repository.getAllData
    }

    fun addEstado(estado: Estado){
        viewModelScope.launch(Dispatchers.IO){
            repository.addEstado(estado)
        }
    }

    fun updateEstado(estado: Estado){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateEstado(estado)
        }
    }

    fun deleteEstado(estado: Estado){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteEstado(estado)
        }
    }
}