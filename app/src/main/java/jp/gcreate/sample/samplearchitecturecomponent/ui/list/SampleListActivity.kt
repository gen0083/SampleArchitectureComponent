package jp.gcreate.sample.samplearchitecturecomponent.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import jp.gcreate.sample.samplearchitecturecomponent.R
import jp.gcreate.sample.samplearchitecturecomponent.databinding.ActivityListBinding

/**
 * Copyright 2018 G-CREATE
 */
class SampleListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private val viewModel: SampleListViewModel by lazy { ViewModelProviders.of(this).get(SampleListViewModel::class.java) }
    private val sampleAdapter = SampleListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)

        setUpRecyclerView()

        viewModel.liveData.observe(this, Observer {
            it?.let {
                sampleAdapter.submitList(it)
                showNoContentview(it.isEmpty())
            } ?: showNoContentview(true)
            sampleAdapter.submitList(it)
        })
        binding.fab.setOnClickListener { viewModel.addItem() }
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SampleListActivity, LinearLayoutManager.VERTICAL, false)
            adapter = sampleAdapter
        }
    }

    private fun showNoContentview(isEmpty: Boolean) {
        if (isEmpty) {
            binding.noContentView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.noContentView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sample_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_shuffle -> {
                viewModel.shuffle()
                true
            }
            else                -> super.onOptionsItemSelected(item)
        }
    }
}
