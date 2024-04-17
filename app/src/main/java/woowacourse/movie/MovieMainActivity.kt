package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MovieMainActivity : AppCompatActivity(), MovieMainContract.View {
    private lateinit var movieDetailActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var movieMainPresenter: MovieMainPresenter
    private lateinit var movieList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_main)

        movieDetailActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
        movieMainPresenter = MovieMainPresenter(this)
        movieList = findViewById<ListView>(R.id.movieList)
        movieList.adapter = movieMainPresenter.getAdapter(this)
    }

    override fun onMovieItemClick(movie: Movie) {
        Intent(this, MovieDetailActivity::class.java).apply {
            putExtra("movie", movie)
            movieDetailActivityResultLauncher.launch(this)
        }
    }
}
