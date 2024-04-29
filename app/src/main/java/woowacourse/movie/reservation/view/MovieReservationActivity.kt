package woowacourse.movie.reservation.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.list.model.Movie
import woowacourse.movie.list.view.MovieListActivity.Companion.EXTRA_MOVIE_ID_KEY
import woowacourse.movie.reservation.contract.MovieReservationContract
import woowacourse.movie.reservation.model.Count
import woowacourse.movie.reservation.model.ScreeningTimes
import woowacourse.movie.reservation.presenter.MovieReservationPresenter
import woowacourse.movie.seats.view.SeatsActivity
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MovieReservationActivity : AppCompatActivity(), MovieReservationContract.View {
    private lateinit var titleView: TextView
    private lateinit var screeningDateView: TextView
    private lateinit var runningTimeView: TextView
    private lateinit var ticketCountView: TextView
    private lateinit var posterView: ImageView
    private lateinit var descriptionView: TextView
    private lateinit var minusNumberButton: Button
    private lateinit var plusNumberButton: Button
    private lateinit var ticketingButton: Button
    private lateinit var spinnerDate: Spinner
    private lateinit var spinnerTime: Spinner
    private var toast: Toast? = null
    lateinit var selectedDate: LocalDate
    lateinit var selectedTime: LocalTime
    private var movieId: Long = -1
    override val presenter = MovieReservationPresenter(this@MovieReservationActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)
        initView()
        presenter.setCurrentResultTicketCountInfo()
        movieId = intent.getLongExtra(EXTRA_MOVIE_ID_KEY, 0)
        presenter.storeMovieId(movieId)
        presenter.setMovieInfo()
        presenter.setSpinnerInfo()
        presenter.setSpinnerDateItemInfo()
        presenter.setSpinnerTimeItemInfo()
        setOnPlusButtonClickListener()
        setOnMinusButtonClickListener()
        setOnTicketingButtonListener()
    }

    private fun initView() {
        titleView = findViewById(R.id.movie_detail_title)
        screeningDateView = findViewById(R.id.movie_detail_screening_date)
        runningTimeView = findViewById(R.id.movie_detail_running_time)
        descriptionView = findViewById(R.id.movie_detail_description)
        ticketCountView = findViewById(R.id.ticket_count)
        posterView = findViewById(R.id.movie_detail_poster)
        minusNumberButton = findViewById(R.id.minus_button)
        plusNumberButton = findViewById(R.id.plus_button)
        ticketingButton = findViewById(R.id.ticketing_button)
        spinnerDate = findViewById(R.id.spinner_date)
        spinnerTime = findViewById(R.id.spinner_time)
    }

    override fun showSpinner(
        screeningDates: List<LocalDate>,
        screeningTimes: List<LocalTime>,
    ) {
        val dateAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, screeningDates)
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val timeAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, screeningTimes)
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDate.adapter = dateAdapter
        spinnerTime.adapter = timeAdapter
    }

    override fun setOnSpinnerDateItemSelectedListener(screeningDates: List<LocalDate>) {
        spinnerDate.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    selectedDate = screeningDates[position]
                    updateTimeSpinner()
                    val screeningTimes = ScreeningTimes(selectedDate).contents
                    setOnSpinnerTimeItemSelectedListener(screeningTimes)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    selectedDate = screeningDates[0]
                }
            }
    }

    private fun updateTimeSpinner() {
        val screeningTimes = ScreeningTimes(selectedDate).contents
        val timeAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, screeningTimes)
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTime.adapter = timeAdapter
    }

    override fun setOnSpinnerTimeItemSelectedListener(screeningTimes: List<LocalTime>) {
        spinnerTime.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    selectedTime = screeningTimes[position]
                    presenter.storeSelectedTime(selectedTime)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    selectedTime = screeningTimes[0]
                }
            }
    }

    override fun setMovieView(info: Movie) {
        val formattedScreeningDate =
            info.firstScreeningDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN))

        titleView.text = info.title
        screeningDateView.text = formattedScreeningDate
        runningTimeView.text = info.runningTime.toString()
        descriptionView.text = info.description
        posterView.setImageResource(info.posterResourceId)
    }

    override fun showToast(message: String) {
        toast?.cancel()
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast?.show()
    }

    override fun showCurrentResultTicketCountView(info: Int) {
        ticketCountView.text = info.toString()
    }

    private fun setOnPlusButtonClickListener() {
        plusNumberButton.setOnClickListener {
            presenter.setPlusButtonClickInfo()
        }
    }

    private fun setOnMinusButtonClickListener() {
        minusNumberButton.setOnClickListener {
            presenter.setMinusButtonClickInfo()
        }
    }

    private fun setOnTicketingButtonListener() {
        ticketingButton.setOnClickListener {
            presenter.setTicketingButtonClickInfo()
        }
    }

    override fun startMovieTicketActivity(info: Count) {
        val intent = Intent(this, SeatsActivity::class.java)
        intent.putExtra(EXTRA_COUNT_KEY, info)
        intent.putExtra(EXTRA_MOVIE_ID_KEY, movieId)
        intent.putExtra(
            EXTRA_DATE_KEY,
            selectedDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN)),
        )
        intent.putExtra(EXTRA_TIME_KEY, selectedTime.toString())
        this.startActivity(intent)
    }

    companion object {
        const val EXTRA_COUNT_KEY = "count_key"
        const val EXTRA_DATE_KEY = "selected_date_key"
        const val EXTRA_TIME_KEY = "selected_time_key"
        private const val DATE_PATTERN = "yyyy.MM.dd"
    }
}
