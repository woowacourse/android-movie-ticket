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
import woowacourse.movie.model.MovieContent
import woowacourse.movie.model.Ticket
import woowacourse.movie.ui.DateUi

class MovieReservationCompleteActivity : AppCompatActivity() {
    private val titleText by lazy { findViewById<TextView>(R.id.title_text) }
    private val screeningDateText by lazy { findViewById<TextView>(R.id.screening_date_text) }
    private val reservationCountText by lazy { findViewById<TextView>(R.id.reservation_count_text) }
    private val reservationAmountText by lazy { findViewById<TextView>(R.id.reservation_amount_text) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation_complete)

        val movieContentId = intent.getLongExtra(MovieContentKey.ID, MOVIE_CONTENT_ID_DEFAULT_VALUE)
        val reservationCount = intent.getIntExtra(MovieReservationKey.COUNT, RESERVATION_COUNT_DEFAULT_VALUE)
        if (movieContentId == MOVIE_CONTENT_ID_DEFAULT_VALUE || reservationCount == RESERVATION_COUNT_DEFAULT_VALUE) {
            Log.e(TAG, "Invalid Key")
            Toast.makeText(this, resources.getString(R.string.invalid_key), Toast.LENGTH_LONG).show()
            finish()
            return
        }

        MovieContentsImpl.find(movieContentId).setUpUi()
        Ticket(reservationCount).setUpUi()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun MovieContent.setUpUi() {
        titleText.text = title
        screeningDateText.text = DateUi.dateMessage(screeningDate, this@MovieReservationCompleteActivity)
    }

    private fun Ticket.setUpUi() {
        reservationCountText.text = resources.getString(R.string.reservation_count).format(count)
        reservationAmountText.text = resources.getString(R.string.reservation_amount).format(amount())
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
