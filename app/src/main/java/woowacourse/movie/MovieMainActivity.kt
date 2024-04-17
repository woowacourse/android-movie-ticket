package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MovieMainActivity : AppCompatActivity(), ViewInterface {
    private lateinit var movieDetailActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var moviePresenter: MoviePresenter
    private lateinit var movieList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_main)

        movieDetailActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
        moviePresenter = MoviePresenter(this)
        movieList = findViewById<ListView>(R.id.movieList)
        movieList.adapter = moviePresenter.getAdapter(this)
    }

    override fun onMovieItemClick(movie: Movie) {
        Intent(this, MovieDetailActivity::class.java).apply {
            putExtra("movie", movie)
            movieDetailActivityResultLauncher.launch(intent)
        }
    }
}
