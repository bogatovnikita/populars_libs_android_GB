package com.bogatovnikita.popularslibsandroidgb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bogatovnikita.popularslibsandroidgb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapterUserEntity: AdapterUserEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
    }

    private fun initView() {
        with(binding.recyclerViewActivityMain) {
            addItemDecoration(DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL))
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterUserEntity
//            adapterUserEntity.setListUsers()
        }
    }
}