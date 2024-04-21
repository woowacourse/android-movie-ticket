package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.presenter.MainContract
import woowacourse.movie.presenter.MainPresenterImpl

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var mainPresenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenterImpl(this)
        mainPresenter.fetchMovies()
    }

    override fun updateMovieList(movies: List<Movie>) {
        val listView = findViewById<ListView>(R.id.movie_list)
        listView.adapter = MovieAdapter(movies)
        listView.setOnItemClickListener { _, _, position, _ ->
            startActivity(
                Intent(
                    this,
                    DetailActivity::class.java
                ).apply {
                    putExtra("movie", movies[position])
                }
            )
        }
    }
}
