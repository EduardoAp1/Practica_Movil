package com.practica.ui.practica

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.practica.R
import com.practica.databinding.FragmentPracticaBinding
import com.practica.viewmodel.PracticaViewModel

class PracticaFragment : Fragment() {

    private var _binding: FragmentPracticaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var practicaViewModel: PracticaViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        practicaViewModel =
            ViewModelProvider(this).get(PracticaViewModel::class.java)


        _binding = FragmentPracticaBinding.inflate(inflater, container, false)
        binding.addEstado.setOnClickListener{
            findNavController().navigate(R.id.action_nav_practica_to_addEstadoFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}