package woowacourse.movie.movielist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.dto.AdDto
import woowacourse.movie.dto.MovieDummy

class MainActivity : AppCompatActivity() {

    private val onMovieClickListener by lazy { OnMovieClickListener(this) }
    private val onAdClickListener by lazy { OnAdClickListener(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpMovieDatas()
    }

    private fun setUpMovieDatas() {
        val movieRV = findViewById<RecyclerView>(R.id.movie_rv)
        val movieRVAdapter = MovieRVAdapter(
            MovieDummy.movieDatas,
            AdDto.getAdData(),
            onMovieClickListener,
            onAdClickListener,
        )

        movieRV.adapter = movieRVAdapter
        movieRVAdapter.notifyDataSetChanged()
    }
}
