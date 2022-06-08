package com.bogatovnikita.popularslibsandroidgb

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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

        initClickButton()
        initRecyclerView()
    }

    private fun initClickButton() {
        Log.d("!!!", "initClickButton: ")
        binding.refreshButtonMainActivity.setOnClickListener {
            showProgressBar(true)
            getData()
        }
    }

    private fun showProgressBar(flag: Boolean) {
        Log.d("!!!", "showProgressBar:$flag ")
        binding.progressBarMainActivity.isVisible = flag
        binding.recyclerViewActivityMain.isVisible = !flag
    }

    private fun initRecyclerView() {
        Log.d("!!!", "initRecyclerView: ")
        with(binding.recyclerViewActivityMain) {
            addItemDecoration(DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL))
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterUserEntity
        }
    }

    private fun setDataAdapter(data: MutableList<UserEntity>) {
        Log.d("!!!", "setDataAdapter: ")
        adapterUserEntity.setListUsers(data)
    }

    private fun onError(throwable: Throwable) {
        Log.d("!!!", "onError: ")
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun getData() {
        Log.d("!!!", "getData: ")
        userEntityRepository.getUsers(
            onSuccess = {
                showProgressBar(false)
                setDataAdapter(it)
                Log.d("!!!", "getData success $it ")
            }, onError = {
                showProgressBar(false)
                onError(it)
                Log.d("!!!", "getData error $it")
            }
        )
    }
}