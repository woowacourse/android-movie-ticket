package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate

class BookingDetailActivity : AppCompatActivity() {
    private var ticketCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_booking_detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val title = intent.getStringExtra(MOVIE_TITLE_KEY)
        val startDate = intent.getStringExtra(MOVIE_START_DATE_KEY)
        val endDate = intent.getStringExtra(MOVIE_END_DATE_KEY)
        val runningTime = intent.getStringExtra(MOVIE_RUNNING_TIME_KEY)
        val poster = intent.getIntExtra(MOVIE_POSTER_KEY, 0)

        findViewById<TextView>(R.id.tv_booking_detail_movie_title).text = title
        findViewById<TextView>(R.id.tv_booking_detail_date).text = "$startDate ~ $endDate"
        findViewById<TextView>(R.id.tv_booking_detail_running_time).text = "${runningTime}분"
        findViewById<TextView>(R.id.tv_booking_detail_count).text = ticketCount.toString()
        findViewById<ImageView>(R.id.iv_booking_detail_movie_poster).setImageResource(poster)

        val dates: List<LocalDate> =
            getDatesBetween(
                LocalDate.parse(startDate),
                LocalDate.parse(endDate),
            )

        findViewById<Spinner>(R.id.sp_booking_detail_date).adapter =
            ArrayAdapter<LocalDate>(
                this,
                android.R.layout.simple_spinner_item,
                dates,
            )

        val times: List<String> =
            listOf(
                "09:00",
                "11:00",
                "13:00",
                "15:00",
                "17:00",
                "19:00",
                "21:00",
                "23:00",
            )

        findViewById<Spinner>(R.id.sp_booking_detail_time).adapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                times,
            )

        findViewById<Button>(R.id.btn_booking_detail_count_down).setOnClickListener {
            if (ticketCount > 0) {
                ticketCount--
                findViewById<TextView>(R.id.tv_booking_detail_count).text = ticketCount.toString()
            }
        }

        findViewById<Button>(R.id.btn_booking_detail_count_up).setOnClickListener {
            ticketCount++
            findViewById<TextView>(R.id.tv_booking_detail_count).text = ticketCount.toString()
        }
    }

    private fun getDatesBetween(
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<LocalDate> {
        val dates = mutableListOf<LocalDate>()
        var currentDate = startDate
        while (currentDate.isBefore(endDate)) {
            dates.add(currentDate)
            currentDate = currentDate.plusDays(1)
        }
        return dates
    }

    companion object {
        const val MOVIE_TITLE_KEY = "movie_title"
        const val MOVIE_START_DATE_KEY = "movie_start_date"
        const val MOVIE_END_DATE_KEY = "movie_end_date"
        const val MOVIE_RUNNING_TIME_KEY = "movie_running_time"
        const val MOVIE_POSTER_KEY = "movie_poster"

        fun newIntent(
            context: Context,
            title: String,
            startDate: String,
            endDate: String,
            runningTime: String,
            poster: Int,
        ): Intent =
            Intent(context, BookingDetailActivity::class.java).apply {
                putExtra(MOVIE_TITLE_KEY, title)
                putExtra(MOVIE_START_DATE_KEY, startDate)
                putExtra(MOVIE_END_DATE_KEY, endDate)
                putExtra(MOVIE_RUNNING_TIME_KEY, runningTime)
                putExtra(MOVIE_POSTER_KEY, poster)
            }
    }
}
