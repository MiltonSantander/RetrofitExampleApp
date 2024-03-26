package com.example.retrofitexampleapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumAPI {
    @GET("/albums")
    suspend fun getAlbumList(): Response<AlbumList>

    @GET("/albums")
    suspend fun getSortedAlbumList(@Query("userId") userId: Int): Response<AlbumList>
}