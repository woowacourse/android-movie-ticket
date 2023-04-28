package woowacourse.movie.movieList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

    private fun onMovieItemClick(movieItem: ItemViewType.MOVIE) {
        val reservationModel = ReservationModel(movieItem)
        val intent = Intent(this, ReservationActivity::class.java)
        intent.putExtra(ReservationActivity.KEY_MOVIE_Screening, reservationModel)
        ContextCompat.startActivity(this, intent, null)
    }

    private fun onMovieAdItemClick(adItem: ItemViewType.AD) {
        Log.d("MovieListRecycler", "ad")
    }
}
