package woowacourse.movie.activity.movielist

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.activity.InjectedModelListener
import woowacourse.movie.activity.moviedetail.MovieDetailActivity
import woowacourse.movie.model.MovieModel
import woowacourse.movie.model.toPresentation
import woowacourse.movie.util.DummyData

class MovieListActivity : AppCompatActivity(), InjectedModelListener<MovieModel> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val listView = findViewById<ListView>(R.id.list_view)
        val adapter = MovieListAdapter(
            DummyData.movies.map { it.toPresentation(R.drawable.img) },
            this,
        )
        listView.adapter = adapter
    }

    override fun onClick(model: MovieModel) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_KEY, model)
        startActivity(intent)
    }
}
