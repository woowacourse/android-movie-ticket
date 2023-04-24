package woowacourse.movie.movieList

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.movieReservation.MovieReservationActivity
import woowacourse.movie.utils.SampleData.MOVIE_SAMPLE

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        initAdapter()
    }

    private fun initAdapter() {
        val movieListView = findViewById<RecyclerView>(R.id.movie_list)
        movieListView.adapter = MovieListAdapter(MOVIE_SAMPLE) { movieScheduleUi ->
            val intent = Intent(this, MovieReservationActivity::class.java)
            intent.putExtra(MovieReservationActivity.KEY_MOVIE_SCHEDULE, movieScheduleUi)
            startActivity(intent)
        }
    }
}
