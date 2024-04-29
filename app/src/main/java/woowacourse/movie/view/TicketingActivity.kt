package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.screening.Screening
import woowacourse.movie.presenter.TicketingPresenter
import woowacourse.movie.presenter.contract.TicketingContract
import woowacourse.movie.view.state.TicketingForm
import woowacourse.movie.view.utils.ErrorMessage
import java.time.LocalDate
import java.time.LocalTime

class TicketingActivity : AppCompatActivity(), TicketingContract.View, OnItemSelectedListener {
    private val countText by lazy { findViewById<TextView>(R.id.tv_count) }
    private lateinit var ticketingPresenter: TicketingPresenter
    private val screeningId by lazy {
        intent.getLongExtra(EXTRA_SCREENING_ID, EXTRA_DEFAULT_SCREENING_ID)
    }
    private val dateSpinner: Spinner by lazy { findViewById(R.id.spinner_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.spinner_time) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ticketingPresenter = TicketingPresenter(this)
        if (savedInstanceState == null) {
            ticketingPresenter.initializeTicketingData(screeningId, DEFAULT_COUNT)
        }
        initializeButtons()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNT, ticketingPresenter.ticketingForm.numberOfTickets.currentValue)
        outState.putString(
            KEY_DATE,
            ticketingPresenter.ticketingForm.bookingDateTime.date.toString(),
        )
        outState.putString(
            KEY_TIME,
            ticketingPresenter.ticketingForm.bookingDateTime.time.toString(),
        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let {
            val count = it.getInt(KEY_COUNT, DEFAULT_COUNT)
            val date = it.getString(KEY_DATE)
            val time = it.getString(KEY_TIME)
            ticketingPresenter.initializeTicketingData(screeningId, count, date, time)
            countText.text =
                ticketingPresenter.ticketingForm.numberOfTickets.currentValue.toString()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun assignInitialView(
        screening: Screening,
        count: Int,
        date: LocalDate?,
        time: LocalTime?,
    ) {
        updateCount(count)
        findViewById<ImageView>(R.id.iv_thumbnail).apply { setImageResource(screening.movie.thumbnailResourceId) }
        findViewById<TextView>(R.id.tv_title).apply { text = screening.movie.title }
        findViewById<TextView>(R.id.tv_date).apply {
            text =
                getString(
                    R.string.title_date,
                    screening.datePeriod.startDate.toString(),
                    screening.datePeriod.endDate.toString(),
                )
        }
        findViewById<TextView>(R.id.tv_running_time).apply {
            text = getString(R.string.title_running_time, screening.movie.runningTime)
        }
        findViewById<TextView>(R.id.tv_introduction).apply { text = screening.movie.introduction }

        initializeDateSpinner(screening, date)
        initializeTimeSpinner(time)
    }

    private fun initializeTimeSpinner(time: LocalTime?) {
        timeSpinner.apply {
            adapter =
                ArrayAdapter(
                    this@TicketingActivity,
                    android.R.layout.simple_spinner_item,
                    ticketingPresenter.ticketingUiState.availableTimes.localTimes,
                )
            onItemSelectedListener = this@TicketingActivity
            val position =
                time?.let {
                    ticketingPresenter.ticketingUiState.availableTimes.localTimes.indexOf(it)
                } ?: 0
            setSelection(position)
        }
    }

    private fun initializeDateSpinner(
        screening: Screening,
        date: LocalDate?,
    ) {
        dateSpinner.apply {
            adapter =
                ArrayAdapter(
                    this@TicketingActivity,
                    android.R.layout.simple_spinner_item,
                    screening.dates,
                )

            onItemSelectedListener = this@TicketingActivity
            val position =
                date?.let {
                    screening.dates.indexOf(it)
                } ?: 0
            setSelection(position)
        }
    }

    override fun updateCount(count: Int) {
        countText.text = count.toString()
    }

    override fun navigateToSeatSelection(ticketingForm: TicketingForm) {
        Intent(this, SeatSelectionActivity::class.java).apply {
            val ticketingState =
                TicketingForm(
                    screeningId,
                    ticketingForm.movieTitle,
                    ticketingForm.numberOfTickets,
                    ticketingForm.bookingDateTime,
                )
            putExtra(EXTRA_TICKETING_INFORMATION, ticketingState)
            startActivity(this)
        }
    }

    override fun showToastMessage(error: ErrorMessage) {
        Toast.makeText(this, error.value, Toast.LENGTH_SHORT).show()
    }

    override fun updateAvailableTimes(times: List<LocalTime>) {
        timeSpinner.apply {
            adapter =
                ArrayAdapter(
                    this@TicketingActivity,
                    android.R.layout.simple_spinner_item,
                    times,
                )
            onItemSelectedListener = this@TicketingActivity
        }
    }

    private fun initializeButtons() {
        val minusButton = findViewById<Button>(R.id.btn_minus)
        val plusButton = findViewById<Button>(R.id.btn_plus)
        val completeButton = findViewById<Button>(R.id.btn_complete)

        minusButton.setOnClickListener {
            ticketingPresenter.decreaseCount()
        }

        plusButton.setOnClickListener {
            ticketingPresenter.increaseCount()
        }

        completeButton.setOnClickListener {
            ticketingPresenter.reserveTickets()
        }
    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long,
    ) {
        when (parent?.id) {
            R.id.spinner_date ->
                ticketingPresenter.updateDate(
                    parent.getItemAtPosition(position).toString(),
                )

            R.id.spinner_time ->
                ticketingPresenter.updateTime(
                    parent.getItemAtPosition(position).toString(),
                )
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        when (parent?.id) {
            R.id.spinner_date ->
                ticketingPresenter.updateDate(
                    parent.getItemAtPosition(0).toString(),
                )

            R.id.spinner_time ->
                ticketingPresenter.updateTime(
                    parent.getItemAtPosition(0).toString(),
                )
        }
    }

    companion object {
        const val EXTRA_SCREENING_ID = "screening_id"
        const val EXTRA_TICKETING_INFORMATION = "ticketing_information"
        const val EXTRA_DEFAULT_SCREENING_ID = -1L
        private const val DEFAULT_COUNT = 1
        private const val KEY_COUNT = "count"
        private const val KEY_DATE = "date"
        private const val KEY_TIME = "time"
    }
}
