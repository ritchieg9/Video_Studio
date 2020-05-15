package com.example.videostudio

import android.Manifest
import android.app.Activity
import android.content.ContentUris
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat


object VideoProvider : Activity() {

    var list: List<Movie> ?=null
    private var count: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    fun buildMedia(activity: Activity): List<Movie>? {

        if(ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 505)
        }
        else {
            list = setupMoviesHDD(activity)
        }
        return list
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    fun setupMoviesHDD(activity: Activity): List<Movie>? {

        val proj = arrayOf(
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE
        )

        val orderBy = MediaStore.Video.Media.TITLE
        val rs = activity.managedQuery(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            proj, null, null, orderBy
        )

        if (rs != null) {

            val title: MutableList<String> = mutableListOf()
            var videoUrl: MutableList<String> = mutableListOf()
            var studio: MutableList<String> = mutableListOf()
            var bgImageUrl: MutableList<String> = mutableListOf()
            var cardImageUrl: MutableList<String> = mutableListOf()
            var count = 0
            var albumArt: MutableList<Bitmap> = mutableListOf()

            while (rs.moveToNext()) {
                count++

                Log.d("Thumb", rs.getString(0))
                title.add(rs.getString(2))
                videoUrl.add(rs.getString(0))
                studio.add(rs.getString(2))
                bgImageUrl.add(rs.getString(0))

                cardImageUrl.add(rs.getString(0))
            }
            rs.close()

            val description = "Fusce id nisi turpis. Praesent viverra bibendum semper. " +
                    "Donec tristique, orci sed semper lacinia, quam erat rhoncus massa, non congue tellus est " +
                    "quis tellus. Sed mollis orci venenatis quam scelerisque accumsan. Curabitur a massa sit " +
                    "amet mi accumsan mollis sed et magna. Vivamus sed aliquam risus. Nulla eget dolor in elit " +
                    "facilisis mattis. Ut aliquet luctus lacus. Phasellus nec commodo erat. Praesent tempus id " +
                    "lectus ac scelerisque. Maecenas pretium cursus lectus id volutpat."

            list = title.indices.map {
                buildMovieInfo(
                    title[it],
                    description,
                    studio[it],
                    videoUrl[it],
                    cardImageUrl[it],
                    bgImageUrl[it]
                )
            }
        }
        return list
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode === 505 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//            setupMovies()
        }
    }

    fun buildMovieInfo(
        title: String,
        description: String,
        studio: String,
        videoUrl: String,
        cardImageUrl: String,
        backgroundImageUrl: String): Movie {
        val movie = Movie()
        movie.id = count++
        movie.title = title
        movie.description = description
        movie.studio = studio
        movie.cardImageUrl = cardImageUrl
        movie.backgroundImageUrl = backgroundImageUrl
        movie.videoUrl = videoUrl
        return movie
    }
}
