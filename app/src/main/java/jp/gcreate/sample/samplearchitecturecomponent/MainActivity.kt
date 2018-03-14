/*
 * Copyright 2018 gen0083
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.gcreate.sample.samplearchitecturecomponent

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.gcreate.sample.samplearchitecturecomponent.customviewmodel.CustomActivity
import jp.gcreate.sample.samplearchitecturecomponent.databinding.ActivityMainBinding
import jp.gcreate.sample.samplearchitecturecomponent.list.SampleListActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.textOne.text = this.toString()
        viewModel.viewModelInstance.observe(this, Observer { binding.textTwo.text = it ?: "instance is null" })
        binding.openList.setOnClickListener { startActivity(Intent(this, SampleListActivity::class.java)) }
        binding.openCustom.setOnClickListener { startActivity(Intent(this, CustomActivity::class.java)) }
    }
}
