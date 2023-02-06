package com.example.paginationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.paginationapp.adapter.RickyMortyPageAdapter
import com.example.paginationapp.databinding.ActivityMainBinding
import com.example.paginationapp.viewModel.RickyMortyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mAdapter: RickyMortyPageAdapter
    private val viewModel: RickyMortyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRv()
        loadingData()
    }

    private fun loadingData(){
        lifecycleScope.launch {
            viewModel.listData.collect{pagingdata->
                mAdapter.submitData(pagingdata)
            }
        }
    }

    private fun setUpRv() {
        mAdapter = RickyMortyPageAdapter()
        binding.rvMain.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,StaggeredGridLayoutManager.VERTICAL
            )

            adapter = mAdapter
            setHasFixedSize(true)
        }
    }
}