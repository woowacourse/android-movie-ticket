package woowacourse.movie.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.Ad
import woowacourse.movie.AdOnClickListener
import woowacourse.movie.MovieMockData
import woowacourse.movie.MovieOnClickListener
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieRecyclerViewAdapter

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
