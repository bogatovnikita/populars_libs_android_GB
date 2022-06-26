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
        setContentView(binding.root)
        if (savedInstanceState == null) {
            initView()
        }
    }

    private fun initView() {
        Log.d("pie", "initView:activity ")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container_frame_layout, ListUsersFragment())
            .commit()
    }

    override fun onDestroy() {
        Log.e("pie", "onDestroy: MainActivity")
        super.onDestroy()
    }
}