/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.videostudio

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat

/**
 * Loads [MainFragment].
 */
class MainActivity : Activity() {

    var list : List<Movie> ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 505)
        }
        else {
           setupMovies()
            
        }
    }

    fun setupMovies() {

        list = MovieList.setupMovies()

        val proj = arrayOf(
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media._ID, MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE
        )

        val orderBy = MediaStore.Video.Media.TITLE
        val rs = managedQuery(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            proj, null, null, orderBy
        )

        var vidsCount = 0
        if (rs != null) {
            vidsCount = rs.getCount()
            while (rs.moveToNext()) {
                Log.d("VIDEO", rs.getString(0))
            }
            rs.close()
        }
        Log.d("VIDEO", "Total count of videos: $vidsCount")

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode === 505 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            setupMovies()
        }
    }
}
