package woowacourse.movie.ui.moviedetail

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.MovieListModel
import woowacourse.movie.model.PeopleCountModel
import woowacourse.movie.ui.movielist.MainActivity
import woowacourse.movie.ui.seat.SeatSelectionActivity
import woowacourse.movie.utils.failLoadingData
import woowacourse.movie.utils.getParcelableCompat
import woowacourse.movie.utils.getSerializableExtraCompat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieDetailActivity : AppCompatActivity() {

    private val dateTimeSpinnerView by lazy {
        DateTimeSpinnerView(
            findViewById(R.id.detail_date_spinner),
            findViewById(R.id.detail_time_spinner)
        )
    }

    private val peopleCountControllerView by lazy {
        PeopleCountControllerView(
            minusButton = findViewById(R.id.detail_minus_button),
            plusButton = findViewById(R.id.detail_plus_button),
            peopleCountView = findViewById(R.id.detail_people_count)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie: MovieListModel.MovieModel =
            intent.getParcelableCompat(MainActivity.KEY_MOVIE) ?: return failLoadingData()

        setMovieInfo(movie)
        initSpinner(movie)
        initPeopleCountController()
        initBookingButton(movie)
        loadSavedData(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        outState.putInt(KEY_DATE_POSITION, dateTimeSpinnerView.dateSelectedPosition)
        outState.putInt(KEY_TIME_POSITION, dateTimeSpinnerView.timeSelectedPosition)
        outState.putSerializable(KEY_PEOPLE_COUNT, peopleCountControllerView.peopleCountModel)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setMovieInfo(movie: MovieListModel.MovieModel) {
        findViewById<ImageView>(R.id.detail_poster).setImageResource(movie.poster)
        findViewById<TextView>(R.id.detail_title).text = movie.title
        findViewById<TextView>(R.id.detail_date).text =
            getString(R.string.screening_date, movie.startDate.format(), movie.endDate.format())
        findViewById<TextView>(R.id.detail_running_time).text =
            getString(R.string.running_time, movie.runningTime)
        findViewById<TextView>(R.id.detail_description).text = movie.description
    }

    private fun LocalDate.format(): String =
        format(DateTimeFormatter.ofPattern(getString(R.string.date_format)))

    private fun initSpinner(movie: MovieListModel.MovieModel) {
        dateTimeSpinnerView.apply {
            setDateSpinner(movie)
            setTimeSpinner()
        }
    }

    private fun initPeopleCountController() {
        peopleCountControllerView.apply {
            setMinusButton()
            setPlusButton()
            setPeopleCountView()
        }
    }

    private fun initBookingButton(movie: MovieListModel.MovieModel) {
        val bookingButton = findViewById<Button>(R.id.detail_booking_button)

        bookingButton.setOnClickListener {
            moveToSeatSelectionActivity(movie)
        }
    }

    private fun moveToSeatSelectionActivity(movie: MovieListModel.MovieModel) {
        val intent = Intent(this, SeatSelectionActivity::class.java).apply {
            putExtra(KEY_TITLE, movie.title)
            putExtra(KEY_TIME, dateTimeSpinnerView.selectedItem)
            putExtra(KEY_PEOPLE_COUNT, peopleCountControllerView.peopleCountModel)
        }
        startActivity(intent)
    }

    private fun loadSavedData(savedInstanceState: Bundle?) {
        dateTimeSpinnerView.setItemPosition(
            savedInstanceState?.getInt(KEY_DATE_POSITION) ?: 0,
            savedInstanceState?.getInt(KEY_TIME_POSITION) ?: 0
        )
        peopleCountControllerView.setPeopleCountNumber(
            savedInstanceState?.getSerializableExtraCompat<PeopleCountModel>(KEY_PEOPLE_COUNT)?.count ?: 1
        )
    }

    companion object {
        private const val KEY_DATE_POSITION = "date_position"
        private const val KEY_TIME_POSITION = "time_position"
        const val KEY_TITLE = "title"
        const val KEY_TIME = "time"
        const val KEY_PEOPLE_COUNT = "count"
    }
}
