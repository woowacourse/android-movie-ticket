package woowacourse.movie.presentation.bookingdetail

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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.BookingInfo
import woowacourse.movie.domain.model.DateType
import woowacourse.movie.domain.model.MovieDates
import woowacourse.movie.presentation.bookingcomplete.BookingCompleteActivity
import woowacourse.movie.presentation.bookingdetail.adapter.DateAdapter
import woowacourse.movie.presentation.bookingdetail.adapter.TimeAdapter
import woowacourse.movie.presentation.mapper.toDomain
import woowacourse.movie.presentation.mapper.toUi
import woowacourse.movie.presentation.model.BookingInfoUiModel
import woowacourse.movie.presentation.model.MovieDateUiModel
import woowacourse.movie.presentation.model.MovieTimeUiModel
import woowacourse.movie.presentation.model.MovieUiModel
import woowacourse.movie.util.getExtra

class BookingDetailActivity : AppCompatActivity() {
    private lateinit var dateAdapter: DateAdapter
    private lateinit var timeAdapter: TimeAdapter
    private val ticketCountView: TextView by lazy { findViewById(R.id.tv_booking_detail_count) }
    private val movieUiModel: MovieUiModel by lazy { intent.getExtra(MOVIE_KEY) ?: MovieUiModel() }
    private val bookingInfo: BookingInfo by lazy { BookingInfo(movieUiModel.toDomain()) }
    private val bookingInfoUiModel: BookingInfoUiModel get() = bookingInfo.toUi()
    private val dateSpinner: Spinner by lazy { findViewById(R.id.sp_booking_detail_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.sp_booking_detail_time) }
    private val selectCompleteDialog: AlertDialog.Builder by lazy { createSelectCompleteDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupView()
        updateView(bookingInfoUiModel)

        setupDateSpinner()
        setupTimeSpinner()

        setupDateSpinnerItemClickListener()
        setupTimeSpinnerItemClickListener()
        setupTicketCountClickListeners()

        setupSelectCompleteClickListener()
    }

    private fun updateView(bookingInfo: BookingInfoUiModel) {
        findViewById<TextView>(R.id.tv_booking_detail_movie_title).text = bookingInfo.movie.title
        findViewById<TextView>(R.id.tv_booking_detail_date).text =
            getString(R.string.movies_movie_date_with_tilde, bookingInfo.movie.startDate, bookingInfo.movie.endDate)
        findViewById<TextView>(R.id.tv_booking_detail_running_time).text =
            getString(R.string.movies_movie_running_time, bookingInfo.movie.runningTime)
        findViewById<ImageView>(R.id.iv_booking_detail_movie_poster).setImageResource(bookingInfo.movie.poster)
        ticketCountView.text = bookingInfo.ticketCount.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(BOOKING_INFO_KEY, bookingInfoUiModel)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val bookingInfo: BookingInfoUiModel = savedInstanceState.getExtra(BOOKING_INFO_KEY) ?: BookingInfoUiModel()
        updateView(bookingInfo)
    }

    private fun createSelectCompleteDialog(): AlertDialog.Builder =
        AlertDialog
            .Builder(this@BookingDetailActivity)
            .setTitle(getString(R.string.booking_detail_booking_check))
            .setMessage(getString(R.string.booking_detail_booking_check_description))
            .setPositiveButton(getString(R.string.booking_detail_booking_complete)) { _, _ ->
                navigateToBookingComplete()
            }.setNegativeButton(getString(R.string.booking_detail_booking_cancel), null)
            .setCancelable(false)

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

    private fun setupDateSpinner() {
        dateAdapter = DateAdapter(this, MovieDates(movieUiModel.startDate.toDomain(), movieUiModel.endDate.toDomain()).toUi())
        dateSpinner.adapter = dateAdapter
    }

    private fun setupTimeSpinner() {
        timeAdapter = TimeAdapter(this)
        timeAdapter.updateTimes(DateType.from(movieUiModel.startDate.toDomain()))
        timeSpinner.adapter = timeAdapter
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
                    val selectedDate = MovieDateUiModel.from(parent?.getItemAtPosition(position) as String).toDomain()
                    bookingInfo.updateDate(selectedDate)

                    val dateType = DateType.from(selectedDate)
                    timeAdapter.updateTimes(dateType)
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
                    val selectedTime = parent?.getItemAtPosition(position) as String
                    bookingInfo.updateMovieTime(MovieTimeUiModel(selectedTime).toDomain())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }

    private fun setupTicketCountClickListeners() {
        findViewById<Button>(R.id.btn_booking_detail_count_down).setOnClickListener {
            decreaseTicketCount()
        }

        findViewById<Button>(R.id.btn_booking_detail_count_up).setOnClickListener {
            increaseTicketCount()
        }
    }

    private fun decreaseTicketCount() {
        bookingInfo.decreaseTicketCount()
        ticketCountView.text = bookingInfoUiModel.ticketCount.toString()
    }

    private fun increaseTicketCount() {
        bookingInfo.increaseTicketCount()
        ticketCountView.text = bookingInfoUiModel.ticketCount.toString()
    }

    private fun setupSelectCompleteClickListener() {
        findViewById<Button>(R.id.btn_booking_detail_select_complete).setOnClickListener {
            selectCompleteDialog.show()
        }
    }

    private fun navigateToBookingComplete() {
        val intent =
            BookingCompleteActivity.newIntent(
                context = this,
                bookingInfo = bookingInfoUiModel,
            )

        startActivity(intent)
        finish()
    }

    companion object {
        const val MOVIE_KEY = "movie"
        private const val BOOKING_INFO_KEY = "booking_info"

        fun newIntent(
            context: Context,
            movie: MovieUiModel,
        ): Intent =
            Intent(context, BookingDetailActivity::class.java).apply {
                putExtra(MOVIE_KEY, movie)
            }
    }
}
