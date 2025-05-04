package woowacourse.movie.feature.bookingdetail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.feature.bookingdetail.contract.BookingDetailContract
import woowacourse.movie.feature.bookingdetail.presenter.BookingDetailPresenter
import woowacourse.movie.feature.bookingdetail.view.adapter.DateAdapter
import woowacourse.movie.feature.bookingdetail.view.adapter.TimeAdapter
import woowacourse.movie.feature.bookingseat.view.BookingSeatActivity
import woowacourse.movie.feature.model.BookingInfoUiModel
import woowacourse.movie.feature.model.MovieDateUiModel
import woowacourse.movie.feature.model.MovieTimeUiModel
import woowacourse.movie.feature.model.MovieUiModel
import woowacourse.movie.util.getExtra

class BookingDetailActivity :
    AppCompatActivity(),
    BookingDetailContract.View {
    private lateinit var dateAdapter: DateAdapter
    private lateinit var timeAdapter: TimeAdapter
    private val ticketCountView: TextView by lazy { findViewById(R.id.tv_booking_detail_count) }
    private val dateSpinner: Spinner by lazy { findViewById(R.id.sp_booking_detail_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.sp_booking_detail_time) }
    private val presenter: BookingDetailPresenter by lazy { BookingDetailPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
        setupDateSpinnerItemClickListener()
        setupTimeSpinnerItemClickListener()
        setupTicketCountClickListeners()
        setupSelectCompleteClickListener()
        presenter.prepareBookingInfo(movieUiModel = intent.getExtra(MOVIE_KEY) ?: MovieUiModel())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) presenter.cancelBookingInfoSetting()
        return super.onOptionsItemSelected(item)
    }

    override fun setupDateView(dates: List<MovieDateUiModel>) {
        dateAdapter = DateAdapter(this, dates)
        dateSpinner.adapter = dateAdapter
    }

    override fun setupTimeView(times: List<String>) {
        timeAdapter = TimeAdapter(this, times)
        timeSpinner.adapter = timeAdapter
    }

    override fun updateView(bookingInfo: BookingInfoUiModel) {
        findViewById<TextView>(R.id.tv_booking_detail_movie_title).text = bookingInfo.movie.title
        findViewById<TextView>(R.id.tv_booking_detail_date).text =
            getString(R.string.movies_movie_date_with_tilde, bookingInfo.movie.startDate, bookingInfo.movie.endDate)
        findViewById<TextView>(R.id.tv_booking_detail_running_time).text =
            getString(R.string.movies_movie_running_time, bookingInfo.movie.runningTime)
        findViewById<ImageView>(R.id.iv_booking_detail_movie_poster).setImageResource(bookingInfo.movie.poster)

        ticketCountView.text = bookingInfo.ticketCount.toString()
        dateSpinner.setSelection(dateAdapter.getPosition(bookingInfo.date.toString()))
        timeSpinner.setSelection(timeAdapter.getPosition(bookingInfo.movieTime.toString()))
    }

    override fun navigateToBookingSeat(bookingInfo: BookingInfoUiModel) {
        val intent = BookingSeatActivity.newIntent(this, bookingInfo)
        startActivity(intent)
    }

    override fun updateTimeSpinnerItems(times: List<String>) {
        timeAdapter.updateTimes(times)
    }

    override fun updateTicketCount(count: Int) {
        ticketCountView.text = count.toString()
    }

    override fun navigateToBack() {
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(BOOKING_INFO_KEY, presenter.saveBookingInfo())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val bookingInfo: BookingInfoUiModel = savedInstanceState.getExtra(BOOKING_INFO_KEY) ?: BookingInfoUiModel()
        presenter.loadBookingInfo(bookingInfo)
    }

    private fun setupView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_booking_detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupDateSpinnerItemClickListener() {
        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedDate = MovieDateUiModel.from(parent?.getItemAtPosition(position) as String)
                    presenter.selectDate(selectedDate.toString())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    private fun setupTimeSpinnerItemClickListener() {
        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedTime = MovieTimeUiModel.from(parent?.getItemAtPosition(position) as String)
                    presenter.selectTime(selectedTime.toString())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    private fun setupTicketCountClickListeners() {
        findViewById<Button>(R.id.btn_booking_detail_count_down).setOnClickListener {
            presenter.decreaseTicketCount()
        }

        findViewById<Button>(R.id.btn_booking_detail_count_up).setOnClickListener {
            presenter.increaseTicketCount()
        }
    }

    private fun setupSelectCompleteClickListener() {
        findViewById<Button>(R.id.btn_booking_detail_select_complete).setOnClickListener {
            presenter.confirmBookingInfo()
        }
    }

    companion object {
        private const val MOVIE_KEY = "MOVIE"
        private const val BOOKING_INFO_KEY = "BOOKING_INFO"

        fun newIntent(
            context: Context,
            movie: MovieUiModel,
        ): Intent =
            Intent(context, BookingDetailActivity::class.java).apply {
                putExtra(MOVIE_KEY, movie)
            }
    }
}
