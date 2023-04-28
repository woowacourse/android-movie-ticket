package woowacourse.movie.movieReservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import model.ReservationModel
import model.SeatSelectionModel
import movie.TicketQuantity
import woowacourse.movie.R
import woowacourse.movie.seatSelection.SeatSelectionActivity
import woowacourse.movie.utils.getSerializableExtraCompat

class ReservationActivity : AppCompatActivity() {
    private val reservationModel by lazy {
        intent.getSerializableExtraCompat(KEY_MOVIE_SCREENING) as? ReservationModel
            ?: run {
                finish()
                Toast.makeText(this, INVALID_MOVIE_SCREENING, Toast.LENGTH_LONG).show()
                ReservationModel.EMPTY
            }
    }

    private val activityView by lazy { window.decorView.rootView }
    private val navigation by lazy { ReservationNavigation(activityView, reservationModel.startDate, reservationModel.endDate, ::onReservationButtonClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        initToolbar()
        initMovieView()

        loadInstanceState(savedInstanceState)
    }

    private fun loadInstanceState(savedInstanceState: Bundle?) {
        navigation.setTicketQuantity(TicketQuantity(savedInstanceState?.getInt(KEY_COUNT) ?: DEFAULT_TICKET_QUANTITY))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        navigation.ticketQuantity.let { outState.putInt(KEY_COUNT, it) }
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
        ReservationContents(activityView, reservationModel)
    }

    private fun onReservationButtonClicked() {
        val seatSelectionModel = SeatSelectionModel(
            title = reservationModel.title,
            reserveTime = navigation.selectedDateTime,
            Quantity = navigation.ticketQuantity,
        )
        SeatSelectionActivity.start(this, seatSelectionModel)
    }

    companion object {
        private const val KEY_COUNT = "count"
        private const val DEFAULT_TICKET_QUANTITY = 1
        private const val INVALID_MOVIE_SCREENING = "잘못된 접근입니다."
        private const val KEY_MOVIE_SCREENING = "key_movie_screening"

        fun start(context: Context, reservationModel: ReservationModel) {
            context.startActivity(
                Intent(context, ReservationActivity::class.java).apply {
                    putExtra(KEY_MOVIE_SCREENING, reservationModel)
                },
                null,
            )
        }
    }
}
