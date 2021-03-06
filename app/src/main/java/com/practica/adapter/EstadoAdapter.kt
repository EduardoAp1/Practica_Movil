package com.practica.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.practica.databinding.EstadoFilaBinding
import com.practica.model.Estado
import com.practica.ui.practica.PracticaFragmentDirections

class EstadoAdapter : RecyclerView.Adapter<EstadoAdapter.EstadoViewHolder>(){
    //LOS ADAPTER TIENEN LA MISMA ESTRUCTURA SIEMPRE

    //LISTA PARA GESTIONAR LA INFORMACION DE LOS LUGARES
    private var lista = emptyList<Estado>()

    inner class EstadoViewHolder(private val itemBinding: EstadoFilaBinding)
        : RecyclerView.ViewHolder (itemBinding.root){

        fun dibuja(estado: Estado){
            itemBinding.tvNombreEstado.text = estado.NombreEstado
            itemBinding.tvCapital.text = estado.Capital
            itemBinding.tvPoblacion.text = estado.Poblacion.toString()
            itemBinding.tvCostas.text = estado.Costas

            itemBinding.vistaFila.setOnClickListener{
                val accion =  PracticaFragmentDirections
                    .actionNavPracticaToUpdateEstadoFragment(estado)
                itemView.findNavController().navigate(accion)
            }

        }
    }

    //ACA SE VA CREAR UNA "CAJITA" DEL RECICLADOR... UNA FILA... SOLO LA ESTRUCTURA
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstadoViewHolder {
        val itemBinding =
            EstadoFilaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false)
        return  EstadoViewHolder(itemBinding)
    }

    //ACA SE VA A SOLICITAR "DIBUJAR" UNA CAJITA, SEGUN EL ELEMENTO DE LA LISTA
    override fun onBindViewHolder(holder: EstadoViewHolder, position: Int) {
        val estado = lista[position]
        holder.dibuja(estado)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun setData(estados : List<Estado>){
        lista = estados
        notifyDataSetChanged()
    }

}