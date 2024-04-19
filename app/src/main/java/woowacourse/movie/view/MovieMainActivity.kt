package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.presenter.MovieMainContract
import woowacourse.movie.presenter.MovieMainPresenter
import woowacourse.movie.utils.MovieErrorCode

class MovieMainActivity : AppCompatActivity(), MovieMainContract.View {
    private lateinit var movieDetailActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var movieMainPresenter: MovieMainPresenter
    private lateinit var movieList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_main)

        movieDetailActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == MovieErrorCode.INVALID_MOVIE_ID.key) {
                    Toast.makeText(this, MovieErrorCode.INVALID_MOVIE_ID.msg, Toast.LENGTH_SHORT)
                        .show()
                }
            }

        movieMainPresenter = MovieMainPresenter(this)
        movieList = findViewById<ListView>(R.id.mainList)
        movieList.adapter = movieMainPresenter.getAdapter(this)
    }

    override fun onMovieItemClick(id: Long) {
        Intent(this, MovieDetailActivity::class.java).apply {
            putExtra("movieId", id)
            movieDetailActivityResultLauncher.launch(this)
        }
    }
}
