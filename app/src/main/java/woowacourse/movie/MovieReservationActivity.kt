package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.constants.MovieContentKey
import woowacourse.movie.constants.MovieReservationKey
import woowacourse.movie.dao.MovieContentsImpl
import woowacourse.movie.model.ReservationCount
import woowacourse.movie.ui.DateUi

class MovieReservationActivity : AppCompatActivity() {
    private var reservationCount = ReservationCount()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        val id = intent.getLongExtra(MovieContentKey.ID, -1)
        if (id == -1L) {
            Log.e(TAG, "Invalid MovieContentKey")
        }

        val posterImage = findViewById<ImageView>(R.id.poster_image)
        val titleText = findViewById<TextView>(R.id.title_text)
        val screeningDateText = findViewById<TextView>(R.id.screening_date_text)
        val runningTimeText = findViewById<TextView>(R.id.running_time_text)
        val synopsisText = findViewById<TextView>(R.id.synopsis_text)
        val minusButton = findViewById<Button>(R.id.minus_button)
        val reservationCountText = findViewById<TextView>(R.id.reservation_count_text)
        val plusButton = findViewById<Button>(R.id.plus_button)
        val reservationButton = findViewById<Button>(R.id.reservation_button)

        MovieContentsImpl.find(id).run {
            posterImage.setImageResource(imageId)
            titleText.text = title
            screeningDateText.text = DateUi.format(screeningDate, this@MovieReservationActivity)
            runningTimeText.text = resources.getString(R.string.running_time).format(runningTime)
            synopsisText.text = synopsis
        }

        reservationCountText.text = reservationCount.count.toString()

        minusButton.setOnClickListener {
            reservationCountText.text = (--reservationCount).count.toString()
        }

        plusButton.setOnClickListener {
            reservationCountText.text = (++reservationCount).count.toString()
        }

        reservationButton.setOnClickListener {
            Intent(this, MovieReservationCompleteActivity::class.java).apply {
                putExtra(MovieContentKey.ID, id)
                putExtra(MovieReservationKey.COUNT, reservationCount.count)
                startActivity(this)
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val TAG = MovieReservationActivity::class.simpleName
    }
}
