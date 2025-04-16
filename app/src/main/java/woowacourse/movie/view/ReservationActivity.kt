package woowacourse.movie.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R

class ReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_reservation_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
    }

    private fun initViews() {
        val title = intent.getStringExtra(MainActivity.EXTRA_TITLE)
        val startYear = intent.getIntExtra(MainActivity.EXTRA_START_YEAR, 0)
        val startMonth = intent.getIntExtra(MainActivity.EXTRA_START_MONTH, 0)
        val startDay = intent.getIntExtra(MainActivity.EXTRA_START_DAY, 0)
        val endYear = intent.getIntExtra(MainActivity.EXTRA_END_YEAR, 0)
        val endMonth = intent.getIntExtra(MainActivity.EXTRA_END_MONTH, 0)
        val endDay = intent.getIntExtra(MainActivity.EXTRA_END_DAY, 0)
        val posterId = intent.getIntExtra(MainActivity.EXTRA_POSTER_ID, 0)
        val runningTIme = intent.getIntExtra(MainActivity.EXTRA_RUNNING_TIME, 0)

        val titleView = findViewById<TextView>(R.id.tv_reservation_movie_title)
        titleView.text = title

        val periodView = findViewById<TextView>(R.id.tv_reservation_movie_period)
        periodView.text =
            getString(
                R.string.screening_period,
                startYear,
                startMonth,
                startDay,
                endYear,
                endMonth,
                endDay,
            )
        val posterImageView = findViewById<ImageView>(R.id.iv_reservation_poster)
        posterImageView.setImageResource(posterId)

        val runningTimeView = findViewById<TextView>(R.id.tv_reservation_movie_running_time)
        runningTimeView.text = getString(R.string.running_time, runningTIme)
    }
}
