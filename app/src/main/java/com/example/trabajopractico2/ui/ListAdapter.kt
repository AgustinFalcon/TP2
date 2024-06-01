package com.example.trabajopractico2.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajopractico2.TpDesaMobileApp
import com.example.trabajopractico2.databinding.ItemRecyclerviewBinding
import com.example.trabajopractico2.model.User

//private val itemClickListener: OnItemClickListener
class ListAdapter(private val itemClickListener: (User) -> Unit): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var list = emptyList<User>()

    inner class ListViewHolder(private val binding: ItemRecyclerviewBinding)
        :RecyclerView.ViewHolder(binding.root) { /*View.OnClickListener*/

            //init {
            //    binding.root.setOnClickListener(this)
            //}

        fun bind(user: User, position: Int) {
            binding.tvItemName.text = user.name
            binding.root.setOnClickListener {
                itemClickListener(user)
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


    @SuppressLint("NotifyDataSetChanged")
    fun setList(users: List<User>) {
        list = users
        notifyDataSetChanged()
    }


    interface OnItemClickListener {
        fun onItemClick(name: String)
    }

}

