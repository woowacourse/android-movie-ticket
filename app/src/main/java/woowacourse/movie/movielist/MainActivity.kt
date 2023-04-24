package woowacourse.movie.movielist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.dto.AdDto
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.dto.MovieDummy
import woowacourse.movie.moviedetail.MovieDetailActivity

class MainActivity : AppCompatActivity() {

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
        )

        movieRV.adapter = movieRVAdapter

        onMovieItemClickListener(movieRVAdapter)
        onAdItemClickListener(movieRVAdapter)

        movieRVAdapter.notifyDataSetChanged()
    }

    private fun onMovieItemClickListener(adapter: MovieRVAdapter) {
        adapter.itemMovieClick = object : OnClickListener<MovieDto> {
            override fun onClick(item: MovieDto) {
                val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
                intent.putExtra(MOVIE_KEY, item)
                startActivity(intent)
            }
        }
    }

    private fun onAdItemClickListener(adapter: MovieRVAdapter) {
        adapter.itemAdClick = object : OnClickListener<AdDto> {
            override fun onClick(item: AdDto) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                startActivity(intent)
            }
        }
    }

    companion object {
        private const val MOVIE_KEY = "movie"
    }
}
