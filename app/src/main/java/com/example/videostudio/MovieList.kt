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

object MovieList {
    val MOVIE_CATEGORY = arrayOf(
        "Category Zero",
        "Category One",
        "Category Two",
        "Category Three",
        "Category Four",
        "Category Five")

    val list: List<Movie> by lazy {
        setupMovies()
    }
    private var count: Long = 0

    fun setupMovies(): List<Movie> {
        val title = arrayOf(
            "Frozen II",
            "Joker",
            "Sonic: The Hedgehog",
            "The Martian",
            "Interstellar")

        val description = "Fusce id nisi turpis. Praesent viverra bibendum semper. " +
                "Donec tristique, orci sed semper lacinia, quam erat rhoncus massa, non congue tellus est " +
                "quis tellus. Sed mollis orci venenatis quam scelerisque accumsan. Curabitur a massa sit " +
                "amet mi accumsan mollis sed et magna. Vivamus sed aliquam risus. Nulla eget dolor in elit " +
                "facilisis mattis. Ut aliquet luctus lacus. Phasellus nec commodo erat. Praesent tempus id " +
                "lectus ac scelerisque. Maecenas pretium cursus lectus id volutpat."
        val studio = arrayOf(
            "Studio Zero",
            "Studio One",
            "Studio Two",
            "Studio Three",
            "Studio Four")
        val videoUrl = arrayOf(
            "http://mvod.lvlt.rtve.es/resources/resources/TE_GLUCA/mp4/8/0/1428750267408.mp4?aksessionid=1394335736901_126486",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose.mp4",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue.mp4",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole.mp4",
            "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose.mp4")
        val bgImageUrl = arrayOf(
            "https://is3-ssl.mzstatic.com/image/thumb/Video113/v4/a5/0e/3a/a50e3adf-a1d7-7b72-2bb7-7e1489338b2b/DIS_FROZEN_2_TH_2000X3000_WW_ARTWORK_EN_3000x4000_2275VT000002KQ.lsr/1200x0w.jpg",
            "https://is1-ssl.mzstatic.com/image/thumb/Video113/v4/d6/4b/8b/d64b8b65-5759-4435-ec1d-4272433fd8e2/pr_source.lsr/1200x0w.jpg",
            "https://is4-ssl.mzstatic.com/image/thumb/Video113/v4/d4/ef/68/d4ef6856-f2a3-d47e-404e-941e41420f44/pr_source.lsr/1200x0w.jpg",
            "https://is2-ssl.mzstatic.com/image/thumb/Video128/v4/50/d3/03/50d3030b-be99-78a9-2250-9c4b62ea12f9/pr_source.lsr/1200x0w.png",
            "https://is4-ssl.mzstatic.com/image/thumb/Video5/v4/94/0b/4e/940b4e58-6628-dfb4-2565-5521c11dcdd4/pr_source.lsr/1200x0w.png")
        val cardImageUrl = arrayOf(
            "https://is3-ssl.mzstatic.com/image/thumb/Video113/v4/a5/0e/3a/a50e3adf-a1d7-7b72-2bb7-7e1489338b2b/DIS_FROZEN_2_TH_2000X3000_WW_ARTWORK_EN_3000x4000_2275VT000002KQ.lsr/600x0w.jpg",
            "https://is1-ssl.mzstatic.com/image/thumb/Video113/v4/d6/4b/8b/d64b8b65-5759-4435-ec1d-4272433fd8e2/pr_source.lsr/600x0w.jpg",
            "https://is4-ssl.mzstatic.com/image/thumb/Video113/v4/d4/ef/68/d4ef6856-f2a3-d47e-404e-941e41420f44/pr_source.lsr/1200x0w.jpg",
            "https://is2-ssl.mzstatic.com/image/thumb/Video128/v4/50/d3/03/50d3030b-be99-78a9-2250-9c4b62ea12f9/pr_source.lsr/600x0w.png",
            "https://is4-ssl.mzstatic.com/image/thumb/Video5/v4/94/0b/4e/940b4e58-6628-dfb4-2565-5521c11dcdd4/pr_source.lsr/600x0w.png")

        val list = title.indices.map {
            buildMovieInfo(
                title[it],
                description,
                studio[it],
                videoUrl[it],
                cardImageUrl[it],
                bgImageUrl[it])
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