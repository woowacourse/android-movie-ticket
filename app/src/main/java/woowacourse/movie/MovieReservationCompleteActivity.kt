package woowacourse.movie

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
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

        val id = intent.getLongExtra(MovieContentKey.ID, -1)
        val ticket = Ticket(intent.getIntExtra(MovieReservationKey.COUNT, -1))
        if (id == -1L || ticket.count == -1) {
            Log.e(TAG, "Invalid Key")
        }

        val titleText = findViewById<TextView>(R.id.title_text)
        val screeningDateText = findViewById<TextView>(R.id.screening_date_text)
        val reservationCountText = findViewById<TextView>(R.id.reservation_count_text)
        val reservationAmountText = findViewById<TextView>(R.id.reservation_amount_text)

        MovieContentsImpl.find(id).run {
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
    }
}
