package com.example.paginationapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paginationapp.api.ApiService
import com.example.paginationapp.model.RickyMorty

class RickyMortyPagingSource(private val apiService: ApiService):PagingSource<Int,RickyMorty>() {

    override fun getRefreshKey(state: PagingState<Int, RickyMorty>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickyMorty> {
        return try {
            val currentPage = params.key?:1
            val response = apiService.getAllCharacter(currentPage)
            val data = response.body()?.results?: emptyList()
            val responseData = mutableListOf<RickyMorty>()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage==1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}