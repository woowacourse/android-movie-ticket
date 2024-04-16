package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MovieDetailActivity : AppCompatActivity() {
    var reservationCount = MIN_RESERVATION_COUNT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val posterImageId = intent.getIntExtra("posterImageId", 0)
        val title = intent.getStringExtra("title")
        val screeningDate = intent.getStringExtra("screeningDate")
        val runningTime = intent.getIntExtra("runningTime", 0)
        val summary = intent.getStringExtra("summary")

        val reservationCountTextView = findViewById<TextView>(R.id.reservationCount)

        findViewById<ImageView>(R.id.posterImage).setImageResource(posterImageId)
        findViewById<TextView>(R.id.title).text = title
        findViewById<TextView>(R.id.screeningDate).text = screeningDate
        findViewById<TextView>(R.id.runningTime).text =
            getString(R.string.running_time_format, runningTime)
        findViewById<TextView>(R.id.summary).text = summary

        findViewById<TextView>(R.id.minusButton).setOnClickListener {
            if (reservationCount > MIN_RESERVATION_COUNT) {
                reservationCount--
                reservationCountTextView.text = reservationCount.toString()
            }
        }

        findViewById<TextView>(R.id.plusButton).setOnClickListener {
            if (reservationCount < MAX_RESERVATION_COUNT) {
                reservationCount++
                reservationCountTextView.text = reservationCount.toString()
            }
        }

        findViewById<TextView>(R.id.reserveButton).setOnClickListener {
            val intent = Intent(this, ReservationResultActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("screeningDate", screeningDate)
            intent.putExtra("reservationCount", reservationCount)
            startActivity(intent)
        }
    }

    companion object {
        const val MIN_RESERVATION_COUNT = 1
        const val MAX_RESERVATION_COUNT = 20
    }
}
