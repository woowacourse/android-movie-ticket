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
import woowacourse.movie.model.ticketing.BookingDateTime
import woowacourse.movie.presenter.TicketingPresenter
import woowacourse.movie.presenter.contract.TicketingContract
import java.time.LocalTime

class TicketingActivity : AppCompatActivity(), TicketingContract.View, OnItemSelectedListener {
    private val countText by lazy { findViewById<TextView>(R.id.tv_count) }
    private lateinit var ticketingPresenter: TicketingPresenter
    private val screeningId by lazy { intent.getLongExtra(EXTRA_SCREENING_ID, EXTRA_DEFAULT_SCREENING_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ticketingPresenter = TicketingPresenter(this, screeningId, DEFAULT_COUNT)
        ticketingPresenter.initializeTicketingData()
        initializeButtons()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNT, ticketingPresenter.countValue)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.let {
            val count = it.getInt(KEY_COUNT, DEFAULT_COUNT)
            ticketingPresenter = TicketingPresenter(this, screeningId, count)
            countText.text = ticketingPresenter.countValue.toString()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun assignInitialView(
        screening: Screening,
        count: Int,
    ) {
        updateCount(count)
        findViewById<ImageView>(R.id.iv_thumbnail).apply { screening.movie?.let { setImageResource(it.thumbnailResourceId) } }
        findViewById<TextView>(R.id.tv_title).apply { text = screening.movie?.title }
        findViewById<TextView>(R.id.tv_date).apply {
            text =
                getString(R.string.title_date, screening.datePeriod.startDate.toString(), screening.datePeriod.endDate.toString())
        }
        findViewById<TextView>(R.id.tv_running_time).apply {
            text = getString(R.string.title_running_time, screening.movie?.runningTime)
        }
        findViewById<TextView>(R.id.tv_introduction).apply { text = screening.movie?.introduction }

        findViewById<Spinner>(R.id.spinner_date).apply {
            adapter =
                ArrayAdapter(
                    this@TicketingActivity,
                    android.R.layout.simple_spinner_item,
                    screening.dates,
                )
            onItemSelectedListener = this@TicketingActivity
        }

        findViewById<Spinner>(R.id.spinner_time).apply {
            adapter =
                ArrayAdapter(
                    this@TicketingActivity,
                    android.R.layout.simple_spinner_item,
                    ticketingPresenter.availableTimes.localTimes,
                )
            onItemSelectedListener = this@TicketingActivity
        }
    }

    override fun updateCount(count: Int) {
        countText.text = count.toString()
    }

    override fun navigateToSeatSelection(
        screeningId: Long,
        count: Int,
        bookingDateTime: BookingDateTime,
        movieTitle: String?,
    ) {
        Intent(this, SeatSelectionActivity::class.java).apply {
            putExtra(EXTRA_SCREENING_ID, screeningId)
            putExtra(EXTRA_COUNT, count)
            putExtra(EXTRA_DATE, bookingDateTime.date.toString())
            putExtra(EXTRA_TIME, bookingDateTime.time.toString())
            putExtra(EXTRA_MOVIE_TITLE, movieTitle)
            startActivity(this)
            finish()
        }
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun updateAvailableTimes(times: List<LocalTime>) {
        findViewById<Spinner>(R.id.spinner_time).apply {
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
            R.id.spinner_date -> ticketingPresenter.updateDate(parent.getItemAtPosition(position).toString())
            R.id.spinner_time -> ticketingPresenter.updateTime(parent.getItemAtPosition(position).toString())
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        when (parent?.id) {
            R.id.spinner_date -> ticketingPresenter.updateDate(parent.getItemAtPosition(0).toString())
            R.id.spinner_time -> ticketingPresenter.updateTime(parent.getItemAtPosition(0).toString())
        }
    }

    companion object {
        const val EXTRA_SCREENING_ID = "screening_id"
        const val EXTRA_COUNT = "count"
        const val EXTRA_DATE = "movie_date"
        const val EXTRA_TIME = "movie_time"
        const val EXTRA_MOVIE_TITLE = "movie_title"
        const val EXTRA_DEFAULT_SCREENING_ID = -1L
        private const val DEFAULT_COUNT = 1
        private const val KEY_COUNT = "count"
    }
}
