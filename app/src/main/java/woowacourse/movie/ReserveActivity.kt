package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieScheduler
import woowacourse.movie.domain.ScreeningDate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ReserveActivity : AppCompatActivity() {
    private val movieScheduler = MovieScheduler()
    private val dateSpinner: Spinner by lazy { findViewById(R.id.sp_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.sp_time) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reserve)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("movie", Movie::class.java)
            } else {
                intent.getParcelableExtra("movie") as? Movie
            }

        if (movie == null) {
            finish()
        }

        initMoveInfo(movie!!)
        initDateSpinner(movie.screeningDate)
        initTimeSpinner(movie.screeningDate)
    }

    private fun initMoveInfo(movie: Movie) {
        val poster = findViewById<ImageView>(R.id.iv_poster)
        val title = findViewById<TextView>(R.id.tv_title)
        val screeningDate = findViewById<TextView>(R.id.tv_screening_date)
        val runningTime = findViewById<TextView>(R.id.tv_running_time)

        val formattedScreeningDate = formatting(movie.screeningDate)

        poster.setImageResource(movie.imageUrl)
        title.text = movie.title
        screeningDate.text = formattedScreeningDate
        runningTime.text = MINUTE.format(movie.runningTime.time)
    }

    private fun initDateSpinner(screeningDate: ScreeningDate) {
        val dates = movieScheduler.reservableDates(screeningDate, LocalDate.now())

        dateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                dates,
            )
    }

    private fun initTimeSpinner(screeningDate: ScreeningDate) {
        val currentTime = LocalDateTime.now()
        val times =
            movieScheduler.reservableTimes(
                movieScheduler.getStartDate(
                    screeningDate.startDate,
                    currentTime.toLocalDate(),
                ),
                currentTime,
            )

        timeSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                times,
            )
    }

    private fun formatting(screeningDate: ScreeningDate): String {
        val start = screeningDate.startDate.format(formatter)
        val end = screeningDate.endDate.format(formatter)
        return SCREENING_DATE.format(start, end)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val MINUTE = "%d분"
        private const val SCREENING_DATE = "%s ~ %s"
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}
