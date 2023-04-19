package woowacourse.movie.movieReservation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import model.MovieListItem
import model.SeatSelectionModel
import woowacourse.movie.R
import woowacourse.movie.seatSelection.SeatSelectionActivity
import woowacourse.movie.seatSelection.SeatSelectionActivity.Companion.KEY_SEAT_SELECTION
import woowacourse.movie.utils.getSerializableExtraCompat

class ReservationActivity : AppCompatActivity() {
    private val movieListItem by lazy {
        intent.getSerializableExtraCompat(KEY_MOVIE_Screening) as? MovieListItem
            ?: run {
                finish()
                Toast.makeText(this, INVALID_MOVIE_SCREENING, Toast.LENGTH_LONG).show()
                MovieListItem.EMPTY
            }
    }

    private val activityView by lazy { window.decorView.rootView }

    private val contents by lazy { ReservationContents(activityView, movieListItem) }
    private val navigation by lazy { ReservationNavigation(activityView, movieListItem, ::onReservationButtonClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        initToolbar()
        initMovieView()
        initNavigate()

        loadInstanceState(savedInstanceState)
    }

    private fun loadInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            navigation.load(it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        navigation.save(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initToolbar() {
        val reservationToolbar = findViewById<Toolbar>(R.id.reservation_toolbar)
        setSupportActionBar(reservationToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initMovieView() {
        contents
    }

    private fun initNavigate() {
        run { navigation }
    }

    private fun onReservationButtonClicked() {
        val seatSelectionModel = SeatSelectionModel(
            title = movieListItem.title,
            reserveTime = navigation.selectedDateTime,
            peopleNumber = navigation.ticketQuantity.toInt(),
        )
        startActivity(
            Intent(this, SeatSelectionActivity::class.java).apply {
                putExtra(KEY_SEAT_SELECTION, seatSelectionModel)
            },
            null,
        )
    }

    companion object {
        const val INVALID_MOVIE_SCREENING = "잘못된 접근입니다."
        const val KEY_MOVIE_Screening = "movieScreening"
    }
}
