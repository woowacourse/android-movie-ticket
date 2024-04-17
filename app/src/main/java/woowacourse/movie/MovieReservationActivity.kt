package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.constants.MovieContentKey
import woowacourse.movie.constants.MovieReservationKey
import woowacourse.movie.dao.MovieContentsImpl
import woowacourse.movie.model.MovieContent
import woowacourse.movie.model.ReservationCount
import woowacourse.movie.ui.DateUi

class MovieReservationActivity : AppCompatActivity() {
    private var reservationCount = ReservationCount()
    private val posterImage by lazy { findViewById<ImageView>(R.id.poster_image) }
    private val titleText by lazy { findViewById<TextView>(R.id.title_text) }
    private val screeningDateText by lazy { findViewById<TextView>(R.id.screening_date_text) }
    private val runningTimeText by lazy { findViewById<TextView>(R.id.running_time_text) }
    private val synopsisText by lazy { findViewById<TextView>(R.id.synopsis_text) }
    private val minusButton by lazy { findViewById<Button>(R.id.minus_button) }
    private val reservationCountText by lazy { findViewById<TextView>(R.id.reservation_count_text) }
    private val plusButton by lazy { findViewById<Button>(R.id.plus_button) }
    private val reservationButton by lazy { findViewById<Button>(R.id.reservation_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        val movieContentId = intent.getLongExtra(MovieContentKey.ID, DEFAULT_VALUE)
        if (movieContentId == DEFAULT_VALUE) {
            Log.e(TAG, "Invalid MovieContentKey")
            Toast.makeText(this, resources.getString(R.string.invalid_key), Toast.LENGTH_LONG).show()
            finish()
            return
        }

        MovieContentsImpl.find(movieContentId).setUpUi()
        setUpReservationCountUi(movieContentId)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun MovieContent.setUpUi() {
        posterImage.setImageResource(imageId)
        titleText.text = title
        screeningDateText.text = DateUi.format(screeningDate, this@MovieReservationActivity)
        runningTimeText.text = resources.getString(R.string.running_time).format(runningTime)
        synopsisText.text = synopsis
    }

    private fun setUpReservationCountUi(movieContentId: Long) {
        reservationCountText.text = reservationCount.count.toString()

        minusButton.setOnClickListener {
            reservationCountText.text = (--reservationCount).count.toString()
        }

        plusButton.setOnClickListener {
            reservationCountText.text = (++reservationCount).count.toString()
        }

        reservationButton.setOnClickListener {
            Intent(this, MovieReservationCompleteActivity::class.java).apply {
                putExtra(MovieContentKey.ID, movieContentId)
                putExtra(MovieReservationKey.COUNT, reservationCount.count)
                startActivity(this)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val TAG = MovieReservationActivity::class.simpleName
        private const val DEFAULT_VALUE = -1L
    }
}
