package jp.gcreate.sample.samplearchitecturecomponent.ui.customviewmodel

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import jp.gcreate.sample.samplearchitecturecomponent.App
import jp.gcreate.sample.samplearchitecturecomponent.R
import jp.gcreate.sample.samplearchitecturecomponent.databinding.ActivityCustomBinding

/**
 * Copyright 2018 G-CREATE
 */
class CustomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomBinding
    private val viewModel: CustomViewModel by lazy {
        ViewModelProviders.of(this, (application as App).customViewModelFactory).get(CustomViewModel::class.java)
    }
    private val listAdapter: TestDataListAdapter = TestDataListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_custom)

        setUpRecyclerView()

        binding.addButton.setOnClickListener {
            val success = viewModel.addData(binding.inputName.editableText.toString(),
                              binding.inputValue.editableText.toString())
            if (success) {
                binding.inputName.editableText.clear()
                binding.inputValue.editableText.clear()
            } else {
                Snackbar.make(binding.recyclerView, "error", Snackbar.LENGTH_SHORT).show()
            }
        }

        viewModel.testData.observe(this, Observer {
            listAdapter.submitList(it)
        })
        viewModel.nameError.observe(this, Observer {
            binding.inputName.error = it
        })
        viewModel.valueError.observe(this, Observer {
            binding.inputValue.error = it
        })
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CustomActivity, LinearLayoutManager.VERTICAL, true)
            adapter = listAdapter
            addItemDecoration(DividerItemDecoration(this@CustomActivity, LinearLayoutManager.VERTICAL))
        }
    }
}
