package woowacourse.movie.movieList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import model.MovieListItem
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
        val movieListView = findViewById<RecyclerView>(R.id.movie_list)
        movieListView.adapter = MovieListAdapter(
            items = CINEMA_SAMPLE,
            onClickButton = ::navigateToReservation,
            onAdClick = { Log.d("MovieListRecycler", "ad") },
        )
    }

    private fun navigateToReservation(movieListItem: MovieListItem) {
        val intent = Intent(this, ReservationActivity::class.java)
        intent.putExtra(ReservationActivity.KEY_MOVIE_Screening, movieListItem)
        ContextCompat.startActivity(this, intent, null)
    }
}
