package com.example.paginationapp.api

import com.example.paginationapp.model.ResponseApi
import com.example.paginationapp.utils.Constants.END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(END_POINT)
    suspend fun getAllCharacter(
        @Query("page") page:Int
    ) :Response<ResponseApi>
}