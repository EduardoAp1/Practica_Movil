package com.practica.ui.practica

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.practica.R
import com.practica.databinding.FragmentUpdateEstadoBinding
import com.practica.model.Estado
import com.practica.viewmodel.PracticaViewModel


class UpdateEstadoFragment : Fragment() {

    //SE RECIBEN LOS PARAMETROS PASADOS POR ARGUMENTO
    private  val args by navArgs<UpdateEstadoFragmentArgs>()

    private var _binding: FragmentUpdateEstadoBinding? = null
    private val binding get() = _binding!!

    private lateinit var practicaViewModel: PracticaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        practicaViewModel =
            ViewModelProvider(this).get(PracticaViewModel::class.java)

        _binding = FragmentUpdateEstadoBinding.inflate(inflater, container, false)

        //Coloco la informacion del lugar en los campos del fragmento para modificar
        binding.etNombreestado.setText(args.estado.NombreEstado)
        binding.etPoblacion.setText(args.estado.Poblacion.toString())
        binding.etCapital.setText(args.estado.Capital)
        binding.etCostas.setText(args.estado.Costas)


        binding.btActualizar.setOnClickListener{
            updateLugar()
        }

        //Se indica que esta pantalla tiene un menu personalizado....
        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Consulto si se dio click en el icono de borrar
        if(item.itemId==R.id.menu_delete){
            deleteLugar()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteLugar() {
        val pantalla = AlertDialog.Builder(requireContext())

        pantalla.setTitle(R.string.delete)
        pantalla.setMessage(getString(R.string.seguroBorrar)+" ${args.estado.NombreEstado}?")

        pantalla.setPositiveButton(getString(R.string.si)){ _,_->
            practicaViewModel.deleteEstado(args.estado)
            findNavController().navigate(R.id.action_updateEstadoFragment_to_nav_practica)
        }

        pantalla.setNegativeButton(getString(R.string.no)){ _,_->}
        pantalla.create().show()
    }

    private fun updateLugar() {
        val nombreEstado = binding.etNombreestado.text.toString()
        val capital = binding.etCapital.text.toString()
        val poblacion = binding.etPoblacion.text.toString().toInt()
        val costas = binding.etCostas.text.toString()

        if(nombreEstado.isNotEmpty()){ //Crea el lugar
            val estado= Estado(args.estado.IdEstado,nombreEstado,capital,poblacion,costas)
            practicaViewModel.updateEstado(estado)
            Toast.makeText(requireContext(),getString(R.string.msg_estado_update),Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateEstadoFragment_to_nav_practica)
        }else{ //Mensaje de error...
            Toast.makeText(requireContext(),getString(R.string.msg_data),Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}