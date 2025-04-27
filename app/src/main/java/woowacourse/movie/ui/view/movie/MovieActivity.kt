package woowacourse.movie.ui.view.movie

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presenter.movie.MovieContract
import woowacourse.movie.presenter.movie.MoviePresenter
import woowacourse.movie.ui.model.movie.MovieUiModel
import woowacourse.movie.ui.view.booking.BookingActivity

class MovieActivity : AppCompatActivity(), MovieContract.View {
    private val moviePresenter by lazy { generatePresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie)
        applySystemBarInsets()

        moviePresenter.loadMovies()
    }

    override fun showMovies(movieUiModels: List<MovieUiModel>) {
        val movieAdapter =
            MovieAdapter(
                movieUiModels = movieUiModels,
                onSelectMovieListener = { movieUiModel ->
                    moviePresenter.onMovieSelect(movieUiModel)
                },
            )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_layout)
        recyclerView.adapter = movieAdapter
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this@MovieActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun moveTo(movieUiModel: MovieUiModel) {
        // movieUiModel 내부에 이동할 페이지에 대한 정보가 있으면 여기에서 분기처리
        startActivity(BookingActivity.newIntent(this, movieUiModel))
    }

    private fun generatePresenter(): MovieContract.Presenter {
        return MoviePresenter(this@MovieActivity)
    }

    private fun applySystemBarInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
