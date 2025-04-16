package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BookingDetailActivity : AppCompatActivity() {
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

        findViewById<TextView>(R.id.tv_booking_detail_movie_title).text = title
        findViewById<TextView>(R.id.tv_booking_detail_date).text = "$startDate ~ $endDate"
        findViewById<TextView>(R.id.tv_booking_detail_running_time).text = "${runningTime}분"
    }

    companion object {
        const val MOVIE_TITLE_KEY = "movie_title"
        const val MOVIE_START_DATE_KEY = "movie_start_date"
        const val MOVIE_END_DATE_KEY = "movie_end_date"
        const val MOVIE_RUNNING_TIME_KEY = "movie_running_time"

        fun newIntent(
            context: Context,
            title: String,
            startDate: String,
            endDate: String,
            runningTime: String,
        ): Intent =
            Intent(context, BookingDetailActivity::class.java).apply {
                putExtra(MOVIE_TITLE_KEY, title)
                putExtra(MOVIE_START_DATE_KEY, startDate)
                putExtra(MOVIE_END_DATE_KEY, endDate)
                putExtra(MOVIE_RUNNING_TIME_KEY, runningTime)
            }
    }
}
