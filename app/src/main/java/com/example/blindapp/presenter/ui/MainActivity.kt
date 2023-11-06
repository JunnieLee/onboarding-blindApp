package com.example.blindapp.presenter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blindapp.R
import com.example.blindapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            view = this@MainActivity
        }
    }

    fun onClickAdd(){

    }
}