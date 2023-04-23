package woowacourse.movie.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.Ad
import woowacourse.movie.R
import woowacourse.movie.movie.MovieMockData
import woowacourse.movie.movielist.MovieRecyclerViewAdapter
import woowacourse.movie.movielist.listener.AdOnClickListener
import woowacourse.movie.movielist.listener.MovieOnClickListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMovieRecyclerView()
    }

    private fun setMovieRecyclerView() {
        val movieRecyclerView = findViewById<RecyclerView>(R.id.rv_movie_list)
        val movieRecyclerViewAdapter = MovieRecyclerViewAdapter(
            MovieMockData.movies10000,
            Ad.dummyAd,
            MovieOnClickListener(this),
            AdOnClickListener(this),
        )
        movieRecyclerView.adapter = movieRecyclerViewAdapter
        movieRecyclerViewAdapter.notifyDataSetChanged()
    }
}
