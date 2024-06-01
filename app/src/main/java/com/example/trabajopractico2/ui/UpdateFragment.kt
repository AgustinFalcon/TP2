package com.example.trabajopractico2.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.trabajopractico2.R
import com.example.trabajopractico2.databinding.FragmentUpdateBinding
import com.example.trabajopractico2.model.User
import com.example.trabajopractico2.viewModel.UserViewModel


class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding

    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        val user = arguments?.getSerializable("user") as User


        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.delete_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId) {
                    R.id.menu_delete -> {
                        deleteUser(user)
                        true
                    }

                    else -> {
                        false
                    }
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.etName.setText(user.name)
        binding.etLastName.setText(user.lastName)
        binding.etAge.setText(user.age.toString())


        binding.btnUpdateUser.setOnClickListener {
            val name = binding.etName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val age = binding.etAge.text.toString()

            if (validateFields(name, lastName, age)) {

                val newUser = user.copy(name = name, lastName = lastName, age = age.toInt())
                userViewModel.updateUser(user = newUser)

                Toast.makeText(requireContext(), "Update ejecutado", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)

            } else {
                Toast.makeText(requireContext(), "Complete todos los campos", Toast.LENGTH_SHORT)
                    .show()
            }
        }


        return binding.root
    }

    private fun deleteUser(user: User) {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle("Eliminar un usuario")
        dialog.setMessage("Estas seguro que desea eliminar a ${user.name}")

        dialog.setNegativeButton("No") { _,_ ->
            return@setNegativeButton
        }

        dialog.setPositiveButton("Si") { _,_ ->
            userViewModel.deleteUser(user)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        dialog.create().show()
    }

    private fun validateFields(name: String, lastName: String, age: String): Boolean =
        name.isNotBlank() && lastName.isNotBlank() && age.isNotBlank()


}