package com.example.retrofitexampleapp

import retrofit2.Response
import retrofit2.http.GET

interface AlbumAPI {
    @GET("/albums")
    suspend fun getAlbumList(): Response<AlbumList>
}