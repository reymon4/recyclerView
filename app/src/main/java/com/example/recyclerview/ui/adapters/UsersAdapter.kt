package com.example.recyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recyclerview.R
import com.example.recyclerview.data.entities.Users
import com.example.recyclerview.databinding.ItemsUsersBinding

class UsersAdapter(private val onDeleteItem:(Int)-> Unit,
    private val onSelectItem: (Users)->Unit) : RecyclerView.Adapter<UsersAdapter.UsersVH>() {

    var listUsers: List<Users> = listOf()

    class UsersVH(view: View) : RecyclerView.ViewHolder(view) {

        private var binding: ItemsUsersBinding = ItemsUsersBinding.bind(view)

        fun render(item: Users, onDeleteItem: (Int) -> Unit,
                   onSelectItem: (Users)->Unit) {
            binding.txtUserName.text = item.name
            binding.txtUserDesc.text = item.desc
            binding.imgUser.load(item.img)

            binding.btnDelete.setOnClickListener{
                onDeleteItem(adapterPosition)
            }
            binding.imgUser.setOnClickListener{
                onSelectItem(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersVH {
        val inflater = LayoutInflater.from(parent.context)
        return UsersVH(inflater.inflate(R.layout.items_users, parent, false))
    }

    override fun getItemCount(): Int = listUsers.size

    override fun onBindViewHolder(holder: UsersVH, position: Int) {
        holder.render(listUsers[position], onDeleteItem, onSelectItem)
    }
}