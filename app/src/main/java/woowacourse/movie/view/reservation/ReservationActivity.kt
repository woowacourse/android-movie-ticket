package woowacourse.movie.view.reservation

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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.presenter.reservation.ReservationContract
import woowacourse.movie.presenter.reservation.ReservationPresenter
import woowacourse.movie.view.extension.getSerializableExtraData
import woowacourse.movie.view.extension.showShortToast
import woowacourse.movie.view.mapper.Formatter.localDateToUI
import woowacourse.movie.view.mapper.Formatter.uiToMovieTime
import woowacourse.movie.view.reservationComplete.ReservationCompleteActivity
import java.time.LocalDate

class ReservationActivity :
    AppCompatActivity(),
    ReservationContract.View {
    private val presenter: ReservationContract.Presenter = ReservationPresenter(this)
    private lateinit var timeSpinnerAdapter: TimeSpinnerAdapter

    private val ticketCountTextView: TextView by lazy { findViewById(R.id.tv_reservation_ticket_count) }
    private val posterImageView: ImageView by lazy { findViewById(R.id.iv_reservation_poster) }
    private val movieTitleTextView: TextView by lazy { findViewById(R.id.tv_reservation_title) }
    private val screeningDateTextView: TextView by lazy { findViewById(R.id.tv_reservation_screening_date) }
    private val runningTimeTextView: TextView by lazy { findViewById(R.id.tv_reservation_running_time) }
    private val dateSpinner: Spinner by lazy { findViewById(R.id.spinner_reservation_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.spinner_reservation_time) }
    private val plusButton: Button by lazy { findViewById(R.id.btn_reservation_plus_ticket_count) }
    private val minusButton: Button by lazy { findViewById(R.id.btn_reservation_minus_ticket_count) }
    private val completeButton: Button by lazy { findViewById(R.id.btn_reservation_select_complete) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupClickListener()
        setupTimeAdapter()
        updateMovieToPresenter()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun updateMovieToPresenter() {
        val intentMovieData: Movie = intent.getSerializableExtraData<Movie>(MOVIE_DATA_KEY)
        presenter.updateMovieData(intentMovieData)
    }

    private fun setupClickListener() {
        minusButton.setOnClickListener {
            presenter.decreaseTicketCount()
        }

        plusButton.setOnClickListener {
            presenter.increaseTicketCount()
        }

        completeButton.setOnClickListener {
            showReservationDialog()
        }
    }

    private fun showReservationDialog() {
        AlertDialog
            .Builder(this)
            .setTitle(getString(R.string.reservation_dialog_title))
            .setMessage(getString(R.string.reservation_dialog_message))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.reservation_dialog_cancel)) { dialog, _ ->
                dialog.dismiss()
            }.setPositiveButton(getString(R.string.reservation_dialog_complete)) { dialog, _ ->
                presenter.createMovieTicket()
                dialog.dismiss()
            }.show()
    }

    override fun setupDateAdapter(dates: List<LocalDate>) {
        val dateAdapter =
            ArrayAdapter(
                this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
                dates,
            )
        dateSpinner.apply {
            adapter = dateAdapter
            onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long,
                    ) {
                        presenter.updateMovieDate(dates[position])
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) = Unit
                }
        }
    }

    private fun setupTimeAdapter() {
        timeSpinnerAdapter = TimeSpinnerAdapter(this, mutableListOf())
        timeSpinner.apply {
            adapter = timeSpinnerAdapter
            onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long,
                    ) {
                        val selectedTime: String = timeSpinnerAdapter.getItem(position)
                        presenter.updateMovieTime(
                            uiToMovieTime(selectedTime.toString()),
                        )
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) = Unit
                }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(TICKET_COUNT_DATA_KEY, ticketCountTextView.text.toString().toInt())
        outState.putInt(TICKET_DATE_POSITION_DATA_KEY, dateSpinner.selectedItemPosition)
        outState.putInt(MOVIE_TIME_POSITION_DATA_KEY, timeSpinner.selectedItemPosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedCount: Int? = savedInstanceState.getInt(TICKET_COUNT_DATA_KEY)
        val savedDatePosition: Int = savedInstanceState.getInt(TICKET_DATE_POSITION_DATA_KEY)
        val savedTimePosition: Int = savedInstanceState.getInt(MOVIE_TIME_POSITION_DATA_KEY)

        presenter.updateTicketCount(savedCount)
        presenter.updateSelectedDatePosition(savedDatePosition)
        presenter.updateSelectedTimePosition(savedTimePosition)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun showTicketCount(count: Int) {
        ticketCountTextView.text = count.toString()
    }

    override fun showTitle(title: String) {
        movieTitleTextView.text = title
    }

    override fun showScreeningDate(
        startDate: LocalDate,
        endDate: LocalDate,
    ) {
        val startDateFormat: String = localDateToUI(startDate)
        val endDateFormat: String = localDateToUI(endDate)
        screeningDateTextView.text =
            resources.getString(
                R.string.movie_screening_date,
                startDateFormat,
                endDateFormat,
            )
    }

    override fun showPoster(poster: Int) {
        val poster =
            AppCompatResources.getDrawable(
                this,
                poster,
            )
        posterImageView.setImageDrawable(poster)
    }

    override fun showRunningTime(runningTime: Int) {
        runningTimeTextView.text = getString(R.string.movie_running_time).format(runningTime)
    }

    override fun showErrorToastMessage(message: String) {
        showShortToast(message)
    }

    override fun showReservationCompleteView(movieTicket: MovieTicket) {
        startActivity(ReservationCompleteActivity.getIntent(this, movieTicket))
        finish()
    }

    override fun updateTimes(times: List<Int>) {
        timeSpinnerAdapter.updateDateItems(times)
    }

    override fun showSelectedDate(position: Int) {
        dateSpinner.setSelection(position)
    }

    override fun showSelectedTime(position: Int) {
        timeSpinner.setSelection(position)
    }

    companion object {
        private const val TICKET_COUNT_DATA_KEY = "count"
        private const val TICKET_DATE_POSITION_DATA_KEY = "date"
        private const val MOVIE_TIME_POSITION_DATA_KEY = "time"
        private const val MOVIE_DATA_KEY = "data"

        fun getIntent(
            context: Context,
            movie: Movie,
        ): Intent =
            Intent(
                context,
                ReservationActivity::class.java,
            ).apply { putExtra(MOVIE_DATA_KEY, movie) }
    }
}
