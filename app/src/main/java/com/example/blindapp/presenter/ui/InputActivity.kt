package com.example.blindapp.presenter.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.blindapp.R
import com.example.blindapp.databinding.ActivityInputBinding
import com.example.blindapp.domain.model.Content
import com.example.blindapp.presenter.viewModel.InputViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputBinding

    private val viewModel : InputViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater).apply {
            setContentView(root)
            lifecycleOwner = this@InputActivity
            viewModel = this@InputActivity.viewModel
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 수정시 - 아이템 받아오기
        (intent.getSerializableExtra(ITEM) as? Content)?.let {
            viewModel.initData(it)
        }

    }

    private fun observeViewModel() {
        viewModel.doneEvent.observe(this) {
            Toast.makeText(this, it.second, Toast.LENGTH_SHORT).show()
            if (it.first) {
                finish()
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


    // 아이템 받아올 수 있는 통로 뚫어줌
    companion object {
        private const val ITEM = "item"

        fun start(context: Context, item: Content? = null) {
            Intent(context, InputActivity::class.java).apply {
                putExtra(ITEM, item)
            }.run {
                context.startActivity(this)
            }
        }
    }
}