package com.example.trabajopractico2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajopractico2.databinding.ItemRecyclerviewBinding

//private val itemClickListener: OnItemClickListener
class ListAdapter(private val itemClickListener: (String, Int) -> Unit): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    val list = listOf("Pedro", "Pepe", "Mengano", "Fulano","Pedro", "Pepe", "Mengano", "Fulano","Pedro", "Pepe", "Mengano", "Fulano","Pedro", "Pepe", "Mengano", "Fulano","Pedro", "Pepe", "Mengano", "Fulano")


    inner class ListViewHolder(private val binding: ItemRecyclerviewBinding)
        :RecyclerView.ViewHolder(binding.root) { /*View.OnClickListener*/

            //init {
            //    binding.root.setOnClickListener(this)
            //}

        fun bind(name: String, position: Int) {
            binding.tvItemName.text = name
            binding.root.setOnClickListener {
                itemClickListener(name, position)
            }
        }

        //override fun onClick(v: View?) {
        //    val position = adapterPosition
         //   if (position != RecyclerView.NO_POSITION) {
         //       val name = list.get(position)
         //       itemClickListener.onItemClick(name)
           // }
        //}
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerviewBinding.inflate(layoutInflater, parent, false)
        return ListViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val name = list.get(position)
        holder.bind(name, position)
    }

    override fun getItemCount(): Int = list.size



    interface OnItemClickListener {
        fun onItemClick(name: String)
    }

}

