package woowacourse.movie.reservation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    var movieId: Long = -1
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

    override fun showSpinnerInfo(
        screeningDates: List<LocalDate>,
        screeningTimes: List<LocalTime>,
    ) {
        spinnerDate.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, screeningDates)
        spinnerTime.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, screeningTimes)
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
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    selectedDate = screeningDates[0]
                }
            }
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
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    selectedTime = screeningTimes[0]
                }
            }
    }

    override fun setMovieView(info: Movie) {
        val formattedScreeningDate =
            info.screeningDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN))

        titleView.text = info.title
        screeningDateView.text = formattedScreeningDate
        runningTimeView.text = info.runningTime.toString()
        descriptionView.text = info.description
        posterView.setImageResource(info.posterResourceId)
    }

    override fun showToast(message: String) {
        toast?.cancel()
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        Log.d("alsong", "$toast, $message")
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
        intent.putExtra(EXTRA_DATE_KEY, selectedDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN)))
        intent.putExtra(EXTRA_TIME_KEY, selectedTime.toString())
        this.startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val count = findViewById<TextView>(R.id.ticket_count).text.toString().toInt()
        outState.putInt(EXTRA_COUNT_KEY, count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let {
            val count = it.getInt(EXTRA_COUNT_KEY)
            val countTextView = findViewById<TextView>(R.id.ticket_count)
            countTextView.text = count.toString()
        }
    }

    companion object {
        const val EXTRA_COUNT_KEY = "count_key"
        const val EXTRA_DATE_KEY = "selected_date_key"
        const val EXTRA_TIME_KEY = "selected_time_key"
        private const val DATE_PATTERN = "yyyy.MM.dd"
    }
}
