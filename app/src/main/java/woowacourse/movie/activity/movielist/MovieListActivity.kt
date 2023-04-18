package woowacourse.movie.activity.movielist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.activity.moviedetail.MovieDetailActivity
import woowacourse.movie.model.MovieDTO
import woowacourse.movie.model.toPresentation
import woowacourse.movie.util.DummyData

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val listView = findViewById<ListView>(R.id.list_view)
        val adapter = MovieListAdapter(
            DummyData.movies.map { it.toPresentation(R.drawable.img) },
            object : MovieListItemListener {
                override fun onClick(movie: MovieDTO, view: View) {
                    val intent = Intent(view.context, MovieDetailActivity::class.java)
                    intent.putExtra(MovieDetailActivity.MOVIE_KEY, movie)
                    view.context.startActivity(intent)
                }
            }
        )
        listView.adapter = adapter
    }
}
