package woowacourse.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import domain.Movie
import java.io.Serializable

class ReservationActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        val posterImageView: ImageView = findViewById(R.id.reservation_movie_image_view)
        val nameTextView: TextView = findViewById(R.id.reservation_movie_name_text_view)
        val screeningDateTextView: TextView =
            findViewById(R.id.reservation_movie_screening_date_text_view)
        val runningTimeTextView: TextView =
            findViewById(R.id.reservation_movie_running_time_text_view)
        val descriptionTextView: TextView =
            findViewById(R.id.reservation_movie_description_text_view)

        intent.customGetSerializable<Movie>("movie")?.let {
            it.posterImage?.let { id -> posterImageView.setImageResource(id) }
            nameTextView.text = it.name
            screeningDateTextView.text = SCREENING_TIME.format(
                it.screeningDate.year,
                it.screeningDate.monthValue,
                it.screeningDate.dayOfMonth
            )
            runningTimeTextView.text = RUNNING_TIME.format(it.runningTime)
            descriptionTextView.text = it.description
        }

        val minusButton: Button = findViewById(R.id.reservation_ticket_count_minus_button)
        val plusButton: Button = findViewById(R.id.reservation_ticket_count_plus_button)
        val ticketCountTextView: TextView = findViewById(R.id.reservation_ticket_count_text_view)

        minusButton.setOnClickListener {
            val ticketCount = ticketCountTextView.text.toString().toInt()
            if (ticketCount > 1) {
                ticketCountTextView.text = (ticketCount - 1).toString()
            }
        }
        plusButton.setOnClickListener {
            val ticketCount = ticketCountTextView.text.toString().toInt()
            ticketCountTextView.text = (ticketCount + 1).toString()
        }
    }

    @Suppress("DEPRECATION")
    inline fun <reified T : Serializable> Intent.customGetSerializable(key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getSerializableExtra(key, T::class.java)
        } else {
            getSerializableExtra(key) as? T
        }
    }

    companion object {
        private const val SCREENING_TIME = "상영일: %d.%d.%d"
        private const val RUNNING_TIME = "러닝타임: %d분"
    }
}
