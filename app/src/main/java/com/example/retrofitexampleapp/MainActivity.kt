package com.example.retrofitexampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retrofit: AlbumAPI
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retrofit = RetrofitInstance.getRetrofitInstance().create(AlbumAPI::class.java)
        textView = findViewById(R.id.text_view)
        /*setUpRequestWithQueryParameters()*/
        setUpRequestWithPathParameters()
    }

    private fun setUpRequestWithPathParameters() {
        liveData {
            emit(retrofit.getAlbum(5))
        }.observe(this) {
            textView.text = " " + "Album title: " + it.body()?.title + "\n" +
                    " " + "Album id: " + it.body()?.id + "\n" +
                    " " + "User id: " + it.body()?.userId + "\n\n\n"
        }
    }

    private fun setUpRequestWithQueryParameters() {
        liveData {
            emit(retrofit.getSortedAlbumList(3))
        }.observe(this) { albumList ->
            albumList.body()?.forEach {
                Log.i("logtest", it.title)
                val album = " " + "Album title: " + it.title + "\n" +
                        " " + "Album id: " + it.id + "\n" +
                        " " + "User id: " + it.userId + "\n\n\n"
                textView.append(album)
            }
        }
    }
}