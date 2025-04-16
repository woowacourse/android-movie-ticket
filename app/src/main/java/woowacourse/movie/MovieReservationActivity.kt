package woowacourse.movie

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Scheduler
import java.time.LocalDate
import java.time.LocalDateTime

class MovieReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movie =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra("movie", Movie::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra("movie")
            }
        if (movie == null) {
            finish()
            return
        }

        val scheduler = Scheduler()
        val screeningDates = scheduler.getScreeningDates(movie.startDate, movie.endDate, LocalDate.now())
        val dateSpinner = findViewById<Spinner>(R.id.date_spinner)
        dateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                screeningDates,
            )

        val timeSpinner = findViewById<Spinner>(R.id.time_spinner)

        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selected = screeningDates[position]
                    timeSpinner.adapter =
                        ArrayAdapter(
                            this@MovieReservationActivity,
                            android.R.layout.simple_spinner_item,
                            scheduler.getShowTimes(selected, LocalDateTime.now()),
                        )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }
}
