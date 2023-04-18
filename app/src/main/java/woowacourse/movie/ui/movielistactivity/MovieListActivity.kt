package woowacourse.movie.ui.movielistactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        initMovieListView()
    }

    private fun initMovieListView() {
        MovieListView(findViewById(R.id.lv_movie))
    }
}
