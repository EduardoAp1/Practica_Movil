package com.practica.ui.practica

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.practica.R
import com.practica.databinding.FragmentAddEstadoBinding
import com.practica.model.Estado
import com.practica.viewmodel.PracticaViewModel
import kotlin.math.cos

class AddEstadoFragment : Fragment() {
    private var _binding: FragmentAddEstadoBinding? = null
    private val binding get() = _binding!!

    private lateinit var practicaViewModel: PracticaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        practicaViewModel =
            ViewModelProvider(this).get(PracticaViewModel::class.java)

        _binding = FragmentAddEstadoBinding.inflate(inflater, container, false)

        binding.btAddEstado.setOnClickListener{
            addEstado()
        }


        return binding.root
    }

    private fun addEstado() {
        val nombreEstado = binding.etNombreestado.text.toString()
        val capital = binding.etCapital.text.toString()
        val poblacion = binding.etPoblacion.text.toString().toInt()
        val costas = binding.etCostas.text.toString()


        if(nombreEstado.isNotEmpty()){ //Crea el lugar
            val estado= Estado(0,nombreEstado,capital,poblacion,costas)
            practicaViewModel.addEstado(estado)
            Toast.makeText(requireContext(),getString(R.string.msg_estado_added),Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addEstadoFragment_to_nav_practica)
        }else{ //Mensaje de error...
            Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_SHORT).show()
        }
    }


}