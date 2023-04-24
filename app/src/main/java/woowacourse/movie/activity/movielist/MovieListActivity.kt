package woowacourse.movie.activity.movielist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.activity.moviedetail.MovieDetailActivity
import woowacourse.movie.model.MovieModel
import woowacourse.movie.model.toPresentation
import woowacourse.movie.util.DummyData

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = MovieListAdapter(
            3,
            DummyData.movies.map { it.toPresentation(R.drawable.img) },
            ::onClick,
        )
        recyclerView.adapter = adapter
    }

    private fun onClick(model: MovieModel) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.MOVIE_KEY, model)
        startActivity(intent)
    }
}
