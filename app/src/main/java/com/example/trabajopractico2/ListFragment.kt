package com.example.trabajopractico2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabajopractico2.databinding.FragmentListBinding


class ListFragment : Fragment(), ListAdapter.OnItemClickListener {

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)


        val adapter = ListAdapter() { name, postion ->
            val bundle = Bundle()
            bundle.putString("name", name)
            findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Linea Divisoria
        val divider = DividerItemDecoration(requireContext(), LinearLayoutManager(requireContext()).orientation)
        binding.recyclerView.addItemDecoration(divider)


        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_detailFragment)
        }

        return binding.root
    }

    override fun onItemClick(name: String) {
        val bundle = Bundle()
        bundle.putString("name", name)
        findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
    }


}
