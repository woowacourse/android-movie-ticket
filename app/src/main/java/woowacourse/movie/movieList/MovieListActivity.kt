package woowacourse.movie.movieList

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import entity.MovieListType
import entity.Screening
import woowacourse.movie.R
import woowacourse.movie.movieReservation.ReservationActivity
import woowacourse.movie.utils.SampleData.CINEMA_SAMPLE

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        initMovieList()
    }

    private fun initMovieList() {
        val movieListView = findViewById<ListView>(R.id.movie_list)
        movieListView.adapter = MovieListAdapter(CINEMA_SAMPLE)
        movieListView.setOnItemClickListener { parent, _, position, _ ->
            getListener(parent.getItemAtPosition(position) as MovieListType)
        }
    }

    private fun getListener(item: MovieListType) {
        when (item) {
            is Screening -> getScreeningListener(item)
        }
    }

    private fun getScreeningListener(screening: Screening) {
        val intent = Intent(this, ReservationActivity::class.java)
        intent.putExtra(ReservationActivity.KEY_MOVIE_Screening, screening)
        ContextCompat.startActivity(this, intent, null)
    }
}
