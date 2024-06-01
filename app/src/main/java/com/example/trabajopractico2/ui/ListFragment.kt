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
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabajopractico2.R
import com.example.trabajopractico2.databinding.FragmentListBinding
import com.example.trabajopractico2.viewModel.UserViewModel


class ListFragment : Fragment(), ListAdapter.OnItemClickListener, MenuProvider {

    private lateinit var binding: FragmentListBinding

    private val userViewModel by viewModels<UserViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)


        val adapter = ListAdapter() { user ->
            val bundle = Bundle()
            bundle.putSerializable("user", user)
            findNavController().navigate(R.id.action_listFragment_to_updateFragment, bundle)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Linea Divisoria
        val divider = DividerItemDecoration(requireContext(), LinearLayoutManager(requireContext()).orientation)
        binding.recyclerView.addItemDecoration(divider)


        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        userViewModel.readAllData.observe(viewLifecycleOwner) { userList ->
            adapter.setList(userList)
        }


        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)



        return binding.root
    }

    override fun onItemClick(name: String) {
        val bundle = Bundle()
        bundle.putString("name", name)
        findNavController().navigate(R.id.action_listFragment_to_updateFragment, bundle)
    }

    private fun deleteAll() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle("Eliminar todos")
        dialog.setMessage("Estas seguro que desea eliminar a todos?")

        dialog.setNegativeButton("No") { _,_ ->
            return@setNegativeButton
        }

        dialog.setPositiveButton("Si") { _,_ ->
            userViewModel.deleteAllUsers()
        }

        dialog.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.menu_delete -> {
                deleteAll()
                true
            }

            else -> {
                false
            }
        }
    }


}
