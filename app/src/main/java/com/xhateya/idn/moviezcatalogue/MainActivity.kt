package com.xhateya.idn.moviezcatalogue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.xhateya.idn.moviezcatalogue.models.Movie
import com.xhateya.idn.moviezcatalogue.models.MovieReview
import com.xhateya.idn.moviezcatalogue.service.MovieApiInterface
import com.xhateya.idn.moviezcatalogue.service.MovieApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rv_movie_list.layoutManager = LinearLayoutManager(this)
        rv_movie_list.setHasFixedSize(true)
        getMovieData { movies : List<Movie> ->
            rv_movie_list.adapter = MovieAdapter(movies)
    }

}

    private fun getMovieData(callback: (List<Movie>) -> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieReview> {
            override fun onFailure(call: Call<MovieReview>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieReview>, response: Response<MovieReview>) {
                return callback(response.body()!!.movies)
            }

        })
    }
}
