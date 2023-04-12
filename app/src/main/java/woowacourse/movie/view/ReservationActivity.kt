package woowacourse.movie.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Reservation

class ReservationActivity : AppCompatActivity() {

    private var peopleCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(MovieListAdapter.MOVIE, Movie::class.java)
        } else {
            intent.getSerializableExtra(MovieListAdapter.MOVIE) as? Movie
        }
        requireNotNull(movie) { "인텐트로 받아온 데이터가 널일 수 없습니다." }
        initViewData(movie)
        initButtonClickListener()
    }

    private fun initViewData(movie: Movie) {
        val posterView = findViewById<ImageView>(R.id.movie_poster)
        posterView.setImageResource(movie.poster.resourceId)
        val titleView = findViewById<TextView>(R.id.movie_title)
        titleView.text = movie.title
        val screeningDateView = findViewById<TextView>(R.id.movie_screening_date)
        screeningDateView.text =
            getString(R.string.screening_date).format(movie.screeningDate.toString())
        val runningTimeView = findViewById<TextView>(R.id.movie_running_time)
        runningTimeView.text = getString(R.string.running_time).format(movie.runningTime.value)
        val summaryView = findViewById<TextView>(R.id.movie_summary)
        summaryView.text = movie.movieDetail.summary
    }

    private fun initButtonClickListener() {
        val peopleCountView = findViewById<TextView>(R.id.people_count)
        findViewById<Button>(R.id.minus_button).setOnClickListener {
            if (peopleCount > Reservation.MIN_PEOPLE_COUNT) {
                peopleCount--
                peopleCountView.text = peopleCount.toString()
            }
        }
        findViewById<Button>(R.id.plus_button).setOnClickListener {
            if (peopleCount < Reservation.MAX_PEOPLE_COUNT) {
                peopleCount++
                peopleCountView.text = peopleCount.toString()
            }
        }
    }
}
