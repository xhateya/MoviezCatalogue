package com.xhateya.idn.moviezcatalogue.service

import com.xhateya.idn.moviezcatalogue.models.MovieReview
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {

    @GET("/3/movie/popular?api_key=fe7d90235b5c8864b5f27fed09087398")
    fun getMovieList(): Call<MovieReview>
}