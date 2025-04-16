package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReserveActivity : AppCompatActivity() {
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
    }

    private fun initMoveInfo(movie: Movie) {
        val poster = findViewById<ImageView>(R.id.iv_poster)
        val title = findViewById<TextView>(R.id.tv_title)
        val screeningDate = findViewById<TextView>(R.id.tv_screening_date)
        val runningTime = findViewById<TextView>(R.id.tv_running_time)

        val formattedScreeningDate = formatting(movie.startDate, movie.endDate)

        poster.setImageResource(movie.imageUrl)
        title.text = movie.title
        screeningDate.text = formattedScreeningDate
        runningTime.text = MINUTE.format(movie.runningTime.time)
    }

    private fun formatting(
        startDate: LocalDate,
        endDate: LocalDate,
    ): String {
        val start = startDate.format(formatter)
        val end = endDate.format(formatter)
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
