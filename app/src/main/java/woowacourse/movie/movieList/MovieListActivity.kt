package woowacourse.movie.movieList

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import model.ItemViewType
import model.ReservationModel
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
            onClickButton = ::onMovieItemClick,
            onAdClick = ::onMovieAdItemClick,
        )
    }

    private fun onMovieItemClick(movieItem: ItemViewType.Movie) {
        val reservationModel = ReservationModel(movieItem)
        ReservationActivity.start(this, reservationModel)
    }

    private fun onMovieAdItemClick(adItem: ItemViewType.Ad) {
        Log.d("MovieListRecycler", "ad")
    }
}
