package com.example.trabajopractico2.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.trabajopractico2.R
import com.example.trabajopractico2.databinding.FragmentAddBinding
import com.example.trabajopractico2.model.User
import com.example.trabajopractico2.viewModel.UserViewModel

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    private val userViewModel: UserViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)


        binding.btnAddUser.setOnClickListener {

            val name = binding.etName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val age = binding.etAge.text.toString()

            if (validateFields(name, lastName, age)) {
                val user = User(id = 0, name = name, lastName = lastName, age = age.toInt())

                userViewModel.insertUser(user)
                Log.d("FragmentAdd", "usuario creado! $user")
                Toast.makeText(requireContext(), "usuario creado! $user", Toast.LENGTH_SHORT).show()
                goToListFragment()

            } else {
                Toast.makeText(requireContext(), "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }

        }



        return binding.root
    }


    fun goToListFragment() {
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }


    private fun validateFields(name: String, lastName: String, age: String): Boolean
         = name.isNotBlank() && lastName.isNotBlank() && age.isNotBlank()

}