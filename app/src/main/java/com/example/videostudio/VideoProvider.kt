package com.example.videostudio

import android.app.Activity
import android.database.Cursor
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi


object VideoProvider : Activity() {

    var list: List<Movie> ?=null
    private var count: Long = 0

    @RequiresApi(Build.VERSION_CODES.Q)
    fun setupMovies(activity: Activity): List<Movie>? {

        val proj = arrayOf(
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.ARTIST
        )

        val orderBy = MediaStore.Video.Media.TITLE
        val rs: Cursor? = activity.applicationContext.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, proj, null, null, orderBy)

        if (rs != null) {

            val title: MutableList<String> = mutableListOf()
            var videoUrl: MutableList<String> = mutableListOf()
            var movieDirector: MutableList<String> = mutableListOf()
            var bgImageUrl: MutableList<String> = mutableListOf()
            var cardImageUrl: MutableList<String> = mutableListOf()

            var count = 0
            while (rs.moveToNext()) {
                if(rs.getString(4) == "video/mp4"){
                    Log.d("VIDEO", rs.getString(2))
                    title.add(rs.getString(2))
                    videoUrl.add(rs.getString(0))
                    movieDirector.add(rs.getString(7))
                    bgImageUrl.add(rs.getString(0))
                    cardImageUrl.add((rs.getString(0)))
                }
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
                    movieDirector[it],
                    videoUrl[it],
                    cardImageUrl[it],
                    bgImageUrl[it]
                )
            }
        }
        return list
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
