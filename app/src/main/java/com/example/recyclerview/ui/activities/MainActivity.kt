package com.example.recyclerview.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview.data.entities.Users
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.ui.adapters.UsersAdapter
import com.example.recyclerview.ui.adapters.UsersAdapterDiffUtil
import com.google.android.material.snackbar.Snackbar
import okhttp3.internal.notify

class MainActivity : AppCompatActivity() {

    private var usersList: MutableList<Users> = ArrayList<Users>()
    private var count: Int = 1
    private lateinit var binding: ActivityMainBinding

    //Como no sabemos la posicion, usamos un iterador
    private var usersAdapter = UsersAdapter({ deleteUsers(it) }, {selectUser(it)})

    private var usersDiffAdapter = UsersAdapterDiffUtil({ deleteDiffUsers(it) }, {selectUser(it)})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initListeners()
    }

    private fun initRecyclerView() {
        binding.rvUsers.adapter = usersDiffAdapter
        binding.rvUsers.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
    }

    private fun initListeners() {

        binding.btnInsert.setOnClickListener {
            val us = Users(
                1, "User $count", "cliente",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/User_icon-cp.png/1200px-User_icon-cp.png"
            )
            count++
            insertDiffUsers(us)

        }

    }
    private fun insertDiffUsers(us: Users) {
        usersList.add(us)
        usersDiffAdapter.submitList(usersList.toList())

    }

    private fun deleteDiffUsers(position: Int) {
        usersList.removeAt(position)
        usersDiffAdapter.submitList(usersList.toList())

    }
    private fun insertUsers(us: Users) {
        usersList.add(us)
        usersAdapter.listUsers = usersList
        usersAdapter.notifyItemInserted(usersList.size)
    }

    private fun deleteUsers(position: Int) {
        usersList.removeAt(position)
        usersAdapter.listUsers = usersList
        usersAdapter.notifyItemRemoved(position)

    }

    private fun selectUser(user: Users) {
        Snackbar.make(this, binding.btnInsert, "Inserted ${user.name}", Snackbar.LENGTH_LONG).show()
        //El intent necesita un punto de salida y punto de llegada
//        val i = Intent(this, )
//        i.putExtra("userID", user.id)
//        startActivity(i)
    }
}