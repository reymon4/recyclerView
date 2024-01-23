package com.example.recyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recyclerview.R
import com.example.recyclerview.data.entities.Users
import com.example.recyclerview.databinding.ItemsUsersBinding

class UsersAdapterDiffUtil(
    private val onDeleteItem: (Int) -> Unit,
    private val onSelectItem: (Users) -> Unit
) : ListAdapter<Users, UsersAdapterDiffUtil.UsersVH>(DiffUtilUserCallBack) {


    class UsersVH(view: View) : RecyclerView.ViewHolder(view) {

        private var binding: ItemsUsersBinding = ItemsUsersBinding.bind(view)

        fun render(
            item: Users, onDeleteItem: (Int) -> Unit,
            onSelectItem: (Users) -> Unit
        ) {
            binding.txtUserName.text = item.name
            binding.txtUserDesc.text = item.desc
            binding.imgUser.load(item.img)

            binding.btnDelete.setOnClickListener {
                onDeleteItem(adapterPosition)
            }
            binding.imgUser.setOnClickListener {
                onSelectItem(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersVH {
        val inflater = LayoutInflater.from(parent.context)
        return UsersVH(inflater.inflate(R.layout.items_users, parent, false))
    }


    override fun onBindViewHolder(holder: UsersVH, position: Int) {
        holder.render(getItem(position), onDeleteItem, onSelectItem)
    }
}

//DAmos las instrucciones para el manejo de listas en segundo plano
//Ademas, este objeto implicitamente, podemos acceder a "metodos de posiciones" dentro de Users, en este caso
private object DiffUtilUserCallBack : DiffUtil.ItemCallback<Users>() {
    override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
        return (oldItem == newItem)
    }

}
