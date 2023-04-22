package woowacourse.movie.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.ui.Ads
import woowacourse.movie.ui.Movies
import woowacourse.movie.ui.adapter.MovieAdapter
import woowacourse.movie.ui.model.AdModel
import woowacourse.movie.ui.model.MovieModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMovieList()
    }

    private fun setMovieList() {
        val moviesView = findViewById<RecyclerView>(R.id.main_movie_list)
        moviesView.adapter = MovieAdapter(
            movies = Movies().getAll(),
            ads = Ads().getAll(),
            onMovieItemClick = { moveToDetailActivity(it) },
            onAdItemClick = { openAdvertiseUrl(it) }
        )
    }

    private fun moveToDetailActivity(movie: MovieModel) {
        val intent = MovieDetailActivity.createIntent(this, movie)
        startActivity(intent)
    }

    private fun openAdvertiseUrl(it: AdModel) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
        startActivity(intent)
    }
}
