package woowacourse.movie.detailbooking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ReservationInfo
import woowacourse.movie.seating.SeatingActivity
import woowacourse.movie.utils.DateFormatter
import woowacourse.movie.utils.parcelableCompat
import java.time.LocalDate
import java.time.LocalTime

class DetailBookingActivity : AppCompatActivity(), DetailBookingContract.View {
    private lateinit var detailBookingPresenter: DetailBookingPresenter

    private val personnel: TextView by lazy { findViewById(R.id.detail_personnel) }
    private val decreasedButton: Button by lazy { findViewById(R.id.decrement_button) }
    private val increasedButton: Button by lazy { findViewById(R.id.increment_button) }
    private val spinnerDate: Spinner by lazy { findViewById(R.id.detail_spinner_date) }
    private val spinnerTime: Spinner by lazy { findViewById(R.id.detail_spinner_time) }
    private val reservationButton: Button by lazy { findViewById(R.id.detail_reservation_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_detail_booking_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        detailBookingPresenter = DetailBookingPresenter(this)

        val movie: Movie = intent.parcelableCompat(KEY_MOVIE, Movie::class.java)
        detailBookingPresenter.set(movie)

        decreasedButton.setOnClickListener {
            detailBookingPresenter.decreasedCount()
        }

        increasedButton.setOnClickListener {
            detailBookingPresenter.increasedCount()
        }

        reservationButton.setOnClickListener {
            detailBookingPresenter.clickedButton()
        }

        if (savedInstanceState != null) {
            val personnel = savedInstanceState.getInt(KEY_PERSONNEL_COUNT, DetailBookingPresenter.DEFAULT_PERSONNEL)
            val selectedDatePosition = savedInstanceState.getInt(KEY_DATE_POSITION, 0)
            val selectedTimePosition = savedInstanceState.getInt(KEY_TIME_POSITION, 0)
            detailBookingPresenter.restoreState(personnel, selectedDatePosition, selectedTimePosition)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_PERSONNEL_COUNT, personnel.text.toString().toInt())
        outState.putInt(KEY_DATE_POSITION, spinnerDate.selectedItemPosition)
        outState.putInt(KEY_TIME_POSITION, spinnerTime.selectedItemPosition)
    }

    override fun showMovieData(movie: Movie) {
        findViewById<TextView>(R.id.detail_movie_title).text = movie.title
        val dateFormatter = DateFormatter()
        findViewById<TextView>(R.id.detail_movie_date).text =
            getString(R.string.movieDate, dateFormatter.format(movie.date.startDate), dateFormatter.format(movie.date.endDate))
        findViewById<TextView>(R.id.detail_movie_time).text =
            getString(R.string.movieTime, movie.time.toString())
        findViewById<ImageView>(R.id.detail_movie_image).setImageResource(movie.image)
    }

    override fun showMovieSchedule(
        movieSchedule: List<LocalDate>,
        selectedDatePosition: Int,
    ) {
        spinnerDate.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, movieSchedule)
        spinnerDate.setSelection(selectedDatePosition)
        spinnerDate.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    detailBookingPresenter.clickedDate(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    override fun showMovieScreeningTime(
        screeningTime: List<LocalTime>,
        selectedTimePosition: Int,
    ) {
        spinnerTime.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, screeningTime).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        spinnerTime.setSelection(selectedTimePosition)
        spinnerTime.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    detailBookingPresenter.clickedTime(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    override fun showCount(count: Int) {
        personnel.text = count.toString()
    }

    override fun showNextActivity(reservationInfo: ReservationInfo) {
        startActivity(SeatingActivity.newIntent(this@DetailBookingActivity, reservationInfo))
    }

    companion object {
        private const val KEY_MOVIE = "movie"
        private const val KEY_PERSONNEL_COUNT = "personnel_count"
        private const val KEY_DATE_POSITION = "movieDate_position"
        private const val KEY_TIME_POSITION = "timeTable_position"

        fun newIntent(
            context: Context,
            movie: Movie,
        ): Intent {
            return Intent(context, DetailBookingActivity::class.java).putExtra(KEY_MOVIE, movie)
        }
    }
}
