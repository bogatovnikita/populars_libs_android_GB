package com.bogatovnikita.popularslibsandroidgb.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bogatovnikita.popularslibsandroidgb.R
import com.bogatovnikita.popularslibsandroidgb.databinding.ActivityMainBinding
import com.bogatovnikita.popularslibsandroidgb.ui.list_users.ListUsersFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("pie", "MainActivity:onCreate")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initView()
        setContentView(binding.root)
    }

    private fun initView() {
        Log.d("pie", "initView:activity ")
        this.supportFragmentManager
            .beginTransaction()
            .add(R.id.main_container_frame_layout, ListUsersFragment())
            .disallowAddToBackStack()
            .commit()
    }
}