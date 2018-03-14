package jp.gcreate.sample.samplearchitecturecomponent.customviewmodel

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
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

        binding.inputName.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable) {
                setErrorMessage(binding.inputName)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                setErrorMessage(binding.inputName)
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setErrorMessage(binding.inputName)
            }
        })
        binding.inputValue.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                setErrorMessage(binding.inputValue)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                setErrorMessage(binding.inputValue)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setErrorMessage(binding.inputValue)
            }
        })
        binding.addButton.setOnClickListener {
            val success = viewModel.addData(binding.inputName.editableText.toString(),
                              binding.inputName.editableText.toString())
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
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CustomActivity, LinearLayoutManager.VERTICAL, true)
            adapter = listAdapter
            addItemDecoration(DividerItemDecoration(this@CustomActivity, LinearLayoutManager.VERTICAL))
        }
    }

    private fun setErrorMessage(editText: TextInputEditText) {
        if (editText.editableText.toString().isEmpty()) {
            editText.error = "empty is not allowed"
        } else {
            editText.error = null
        }
    }
}
