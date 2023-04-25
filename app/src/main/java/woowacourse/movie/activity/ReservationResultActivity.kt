package woowacourse.movie.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.getSerializableCompat
import woowacourse.movie.view.MovieView
import woowacourse.movie.view.mapper.TicketsMapper
import woowacourse.movie.view.model.MovieUiModel
import woowacourse.movie.view.model.TicketsUiModel
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ReservationResultActivity : AppCompatActivity() {
    private val movieTitleTextView: TextView by lazy { findViewById(R.id.movie_reservation_result_title) }
    private val dateTextView: TextView by lazy { findViewById(R.id.movie_reservation_result_date) }
    private val peopleCountTextView: TextView by lazy { findViewById(R.id.movie_reservation_result_people_count) }
    private val seatTextView: TextView by lazy { findViewById(R.id.movie_reservation_result_seat) }
    private val priceTextView: TextView by lazy { findViewById(R.id.movie_reservation_result_price) }
    private val ticketsUiModel: TicketsUiModel by lazy {
        receiveTicketsUiModel() ?: run {
            finishActivityWithMessage(getString(R.string.reservation_data_null_error))
            TicketsUiModel(listOf())
        }
    }

    private val movieUiModel: MovieUiModel by lazy {
        receiveMovieViewModel() ?: run {
            finishActivityWithMessage(getString(R.string.reservation_data_null_error))
            MovieUiModel(0, "qwe", LocalDate.MAX, LocalDate.MAX, 0, "")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        renderMovieView()
        renderReservationDetailView()
    }

    private fun finishActivityWithMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun renderMovieView() {
        MovieView(title = movieTitleTextView).render(movieUiModel)
    }

    private fun renderReservationDetailView() {
        ticketsUiModel.renderDate(dateTextView)
        ticketsUiModel.renderPeopleCount(peopleCountTextView)
        ticketsUiModel.renderSeatsInformation(seatTextView)
        ticketsUiModel.renderPrice(priceTextView)
    }
    private fun receiveTicketsUiModel(): TicketsUiModel? {
        return intent.extras?.getSerializableCompat(TICKETS_VALUE)
    }

    private fun receiveMovieViewModel(): MovieUiModel? {
        return intent.extras?.getSerializableCompat(MOVIE_KEY_VALUE)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val MOVIE_KEY_VALUE = "movie"
        private const val TICKETS_VALUE = "tickets"
        fun start(
            context: Context,
            movieUiModel: MovieUiModel,
            ticketsUiModel: TicketsUiModel
        ) {
            val intent = Intent(context, ReservationResultActivity::class.java)
            intent.putExtra(MOVIE_KEY_VALUE, movieUiModel)
            intent.putExtra(TICKETS_VALUE, ticketsUiModel)
            context.startActivity(intent)
        }
    }
}
