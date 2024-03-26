package com.example.retrofitexampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = RetrofitInstance.getRetrofitInstance().create(AlbumAPI::class.java)
        val response: LiveData<Response<AlbumList>> = liveData {
            emit(retrofit.getAlbumList())
        }
        response.observe(this) { albumList ->
            val textView = findViewById<TextView>(R.id.text_view)
            albumList.body()?.forEach {
                Log.i("logtest", it.title)
                val album = " " + "Album title: " + it.title + "\n" +
                        " " + "Album id: " + "${it.id}" + "\n" +
                        " " + "User id: " + "${it.userId}" + "\n\n\n"
                textView.append(album)
            }
        }
    }
}