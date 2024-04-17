package woowacourse.movie

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.constants.MovieContentKey
import woowacourse.movie.constants.MovieReservationKey
import woowacourse.movie.dao.MovieContentsImpl
import woowacourse.movie.model.Ticket
import woowacourse.movie.ui.DateUi

class MovieReservationCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation_complete)

        val movieContentId = intent.getLongExtra(MovieContentKey.ID, MOVIE_CONTENT_ID_DEFAULT_VALUE)
        val ticket = Ticket(intent.getIntExtra(MovieReservationKey.COUNT, RESERVATION_COUNT_DEFAULT_VALUE))
        if (movieContentId == MOVIE_CONTENT_ID_DEFAULT_VALUE || ticket.count == RESERVATION_COUNT_DEFAULT_VALUE) {
            Log.e(TAG, "Invalid Key")
            Toast.makeText(this, resources.getString(R.string.invalid_key), Toast.LENGTH_LONG).show()
            finish()
            return
        }

        val titleText = findViewById<TextView>(R.id.title_text)
        val screeningDateText = findViewById<TextView>(R.id.screening_date_text)
        val reservationCountText = findViewById<TextView>(R.id.reservation_count_text)
        val reservationAmountText = findViewById<TextView>(R.id.reservation_amount_text)

        MovieContentsImpl.find(movieContentId).run {
            titleText.text = title
            screeningDateText.text =
                DateUi.format(screeningDate, this@MovieReservationCompleteActivity)
        }
        reservationCountText.text =
            resources.getString(R.string.reservation_count).format(ticket.count)
        reservationAmountText.text = resources.getString(R.string.reservation_amount).format(ticket.amount())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val TAG = MovieReservationCompleteActivity::class.simpleName
        private const val MOVIE_CONTENT_ID_DEFAULT_VALUE = -1L
        private const val RESERVATION_COUNT_DEFAULT_VALUE = -1
    }
}
