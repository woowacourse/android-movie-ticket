package woowacourse.movie.ui.movielistactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.ui.model.MovieUIModel
import woowacourse.movie.ui.moviebookingactivity.MovieBookingActivity

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        initMovieListView()
    }

    private fun initMovieListView() {
        MovieListView(findViewById(R.id.lv_movie), ::moveToMovieBookingActivity)
    }

    private fun moveToMovieBookingActivity(data: MovieUIModel) {
        val intent = Intent(this, MovieBookingActivity::class.java)
            .putExtra(MovieBookingActivity.MOVIE_DATA, data)
        startActivity(intent)
    }
}
