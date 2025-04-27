package woowacourse.movie.reserve

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
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
import woowacourse.movie.KeyIdentifiers
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.ScreeningDate
import woowacourse.movie.ext.getSerializableCompat
import woowacourse.movie.seat.SeatActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReserveActivity : AppCompatActivity(), ReserveContract.View {
    private val dateSpinner: Spinner by lazy { findViewById(R.id.sp_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.sp_time) }
    private val ticketCount: TextView by lazy { findViewById(R.id.tv_ticket_count) }
    private val presenter: ReservePresenter = ReservePresenter(this)

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

        presenter.initReservation(getReservation(savedInstanceState), getMovie())
        initView()
    }

    private fun getMovie(): Movie {
        return intent.getSerializableCompat<Movie>(KeyIdentifiers.KEY_MOVIE)
    }

    private fun getReservation(savedInstanceState: Bundle?): Reservation? {
        return savedInstanceState?.getSerializable(KeyIdentifiers.KEY_RESERVATION) as? Reservation?
    }

    private fun initView() {
        presenter.updateReservationInfo()
        presenter.updateReservableDates()
        initTimeSpinner()
        initButtonClickListeners()
    }

    override fun showMovieInfo(movie: Movie) {
        val poster = findViewById<ImageView>(R.id.iv_poster)
        val title = findViewById<TextView>(R.id.tv_title)
        val screeningDate = findViewById<TextView>(R.id.tv_screening_date)
        val runningTime = findViewById<TextView>(R.id.tv_running_time)

        poster.setImageResource(movie.imageUrl)
        title.text = movie.title
        screeningDate.text = formatting(movie.screeningDate)
        runningTime.text = getString(R.string.text_running_time).format(movie.runningTime.time)
    }

    override fun initDateSpinner(
        dates: List<LocalDate>,
        reservedDate: LocalDate,
    ) {
        dateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                dates,
            )
        dateSpinner.setSelection(dates.indexOf(reservedDate))

        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedDate = dates[position]
                    presenter.updateReservableTimes(selectedDate)
                    presenter.updateReservedTime(getSelectedDateTime())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    private fun initTimeSpinner() {
        val selectedDate = dateSpinner.selectedItem as LocalDate

        presenter.updateReservableTimes(selectedDate)

        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    presenter.updateReservedTime(getSelectedDateTime())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    override fun updateTimeSpinner(
        times: List<LocalTime>,
        reservedTime: LocalTime,
    ) {
        val adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                times,
            )
        timeSpinner.adapter = adapter

        timeSpinner.setSelection(times.indexOf(reservedTime))
    }

    private fun getSelectedDateTime(): LocalDateTime {
        return LocalDateTime.of(
            dateSpinner.selectedItem as LocalDate?,
            timeSpinner.selectedItem as LocalTime,
        )
    }

    private fun initButtonClickListeners() {
        val minusBtn = findViewById<Button>(R.id.btn_minus)
        val plusBtn = findViewById<Button>(R.id.btn_plus)
        val selectBtn = findViewById<Button>(R.id.btn_select)

        minusBtn.setOnClickListener {
            presenter.decreaseTicketCount()
        }

        plusBtn.setOnClickListener {
            presenter.increaseTicketCount()
        }

        selectBtn.setOnClickListener {
            val intent = SeatActivity.newIntent(this, presenter.reservation)
            startActivity(intent)
        }
    }

    override fun showTicketCount(count: Int) {
        ticketCount.text = count.toString()
    }

    private fun formatting(screeningDate: ScreeningDate): String {
        val start = screeningDate.startDate.format(formatter)
        val end = screeningDate.endDate.format(formatter)
        return getString(R.string.text_screening_date).format(start, end)
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KeyIdentifiers.KEY_RESERVATION, presenter.reservation)
    }

    companion object {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

        fun newIntent(
            context: Context,
            movie: Movie,
        ): Intent =
            Intent(context, ReserveActivity::class.java).apply {
                putExtra(KeyIdentifiers.KEY_MOVIE, movie)
            }
    }
}
