package com.bogatovnikita.popularslibsandroidgb

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bogatovnikita.popularslibsandroidgb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapterUserEntity = AdapterUserEntity()
    private val userEntityRepository: UserEntityRepository by lazy { app.userEntityRepositorySingleton }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initRecyclerView()
        getData()
    }

    private fun initRecyclerView() {
        with(binding.recyclerViewActivityMain) {
            addItemDecoration(DividerItemDecoration(this@MainActivity, RecyclerView.HORIZONTAL))
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterUserEntity


        }
    }

    private fun setDataAdapter(data: MutableList<UserEntity>) {
        adapterUserEntity.setListUsers(data)
    }

    private fun onError(throwable: Throwable) {
        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show()
    }

    private fun getData() {
        userEntityRepository.getUsers(
            onSuccess = {
                setDataAdapter(it)
            }, onError = { onError(it) }
        )
    }
}