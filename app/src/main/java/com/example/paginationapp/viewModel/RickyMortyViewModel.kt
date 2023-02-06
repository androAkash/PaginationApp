package com.example.paginationapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.paginationapp.api.ApiService
import com.example.paginationapp.paging.RickyMortyPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RickyMortyViewModel @Inject constructor(private val apiService: ApiService):ViewModel(){

    val listData = Pager(PagingConfig(pageSize = 1)){
        RickyMortyPagingSource(apiService)
    } .flow.cachedIn(viewModelScope)
}