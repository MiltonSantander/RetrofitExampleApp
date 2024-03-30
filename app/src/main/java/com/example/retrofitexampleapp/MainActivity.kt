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
        /*setUpRequestWithPathParameters()*/
        saveNewAlbum()
    }

    private fun setUpRequestWithPathParameters() {
        liveData {
            emit(retrofit.getAlbum(5))
        }.observe(this) {
            textView.text = getString(
                R.string.textview_text,
                it.body()?.title,
                it.body()?.id,
                it.body()?.userId
            )
        }
    }

    private fun setUpRequestWithQueryParameters() {
        liveData {
            emit(retrofit.getSortedAlbumList(3))
        }.observe(this) { albumList ->
            albumList.body()?.forEach {
                Log.i("logtest", it.title)
                textView.text = getString(
                    R.string.textview_text,
                    it.title,
                    it.id,
                    it.userId
                )
            }
        }
    }

    private fun saveNewAlbum() {
        liveData {
            emit(retrofit.saveNewAlbum(Album(101, "Thunderstruck", 234)))
        }.observe(this) {
            Log.i("newAlbum", it.body().toString())
            textView.text = getString(
                R.string.textview_text,
                it.body()?.title,
                it.body()?.id,
                it.body()?.userId
            )
        }
    }
}