package woowacourse.movie.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import woowacourse.movie.R
import woowacourse.movie.domain.model.DateRange
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.repository.DummyMovies
import woowacourse.movie.domain.repository.DummyReservation
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.ui.ScreenDetailUI
import woowacourse.movie.ui.reservation.ReservationActivity
import java.lang.IllegalStateException
import java.time.DayOfWeek
import java.time.LocalDate

class ScreenDetailActivity : AppCompatActivity(), ScreenDetailContract.View {
    private val presenter: ScreenDetailContract.Presenter by lazy {
        ScreenDetailPresenter(
            this,
            DummyMovies(),
            DummyScreens(),
            DummyReservation,
        )
    }

    private val screenDetailView: ScreenDetailScreenView by lazy { findViewById(R.id.screen_detail_screen_view) }
    private val ticketView: ScreenDetailTicketView by lazy { findViewById(R.id.screen_detail_ticket_view) }
    private val dateSpinner: Spinner by lazy { findViewById(R.id.spn_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.spn_time) }

    private lateinit var dateAdapter: ArrayAdapter<LocalDate>
    private lateinit var timeAdapter: ArrayAdapter<CharSequence>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_detail)
        initView()
    }

    private fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = intent.getIntExtra(PUT_EXTRA_KEY_ID, DEFAULT_ID)
        initClickListener(id)

        presenter.loadScreen(id)
        presenter.loadTicket()
    }

    private fun initClickListener(id: Int) {
        ticketView.initClickListener(screenId = id, presenter = presenter)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PUT_TICKET_STATE_KEY, ticketView.ticketCount())

        outState.putInt("datePositionKey", dateSpinner.selectedItemPosition)
        outState.putInt("timePositionKey", timeSpinner.selectedItemPosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.let { bundle ->
            val count = bundle.getInt(PUT_TICKET_STATE_KEY)
            ticketView.restoreTicketCount(count)
            presenter.saveTicket(count)
            presenter.loadTicket()

            val datePosition = bundle.getInt("datePositionKey")
            presenter.saveDatePosition(datePosition)
            presenter.loadDatePosition()
            val timePosition = bundle.getInt("timePositionKey")
            presenter.saveTimePosition(timePosition)
            presenter.loadTimePosition()
        }
    }

    override fun showScreen(screen: ScreenDetailUI) {
        screenDetailView.show(screen)
    }

    override fun showTicket(count: Int) {
        ticketView.updateTicketCount(count)
    }

    override fun showDatePicker(dateRange: DateRange) {
        dateAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dateRange.allDates())
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dateSpinner.adapter = dateAdapter

        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val date = dateAdapter.getItem(position)
                presenter.saveDatePosition(position)
                showTimePicker(date?: throw IllegalStateException("Spinner's item is null!!"))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("ScreenDetailActivity", "onNothingSelected")
            }
        }
    }

    override fun showTimePicker(date: LocalDate) {
//        timeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, decideTime(isWeek(date)))
        timeAdapter = ArrayAdapter.createFromResource(this, decideTime(isWeek(date)), android.R.layout.simple_spinner_item)
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timeSpinner.adapter = timeAdapter

        timeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.saveTimePosition(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("ScreenDetailActivity", "onNothingSelected")
            }
        }
    }

    override fun showDateWithPosition(datePosition: Int) {
        dateSpinner.setSelection(datePosition)
    }

    override fun showTimeWithPosition(timePosition: Int) {
        timeSpinner.setSelection(timePosition)
    }

    private fun isWeek(date: LocalDate?) = date?.let {
        it.dayOfWeek in DayOfWeek.MONDAY..DayOfWeek.FRIDAY
    } ?: throw IllegalStateException("Spinner's item is null!!")

    private val weekDayTimes = listOf("09:00", "11:00", "13:00", "15:00", "17:00", "19:00", "21:00", "23:00")
    private val weekEndTimes = listOf("09:00", "11:00", "13:00", "15:00", "17:00", "19:00", "21:00", "23:00")

    private fun decideTime(isWeek: Boolean): Int = if(isWeek) R.array.weekday_time else R.array.weekend_time

    override fun navigateToReservation(navigationId: Int) {
        ReservationActivity.startActivity(this, navigationId)
        finish()
    }

    override fun goToBack(e: Throwable) {
        showToastMessage(e)
        finish()
    }

    override fun unexpectedFinish(e: Throwable) {
        showSnackBar(e)
        finish()
    }

    override fun showToastMessage(e: Throwable) {
        when (e) {
            is NoSuchElementException -> Toast.makeText(this, "해당하는 상영 정보가 없습니다!!", Toast.LENGTH_SHORT).show()
            is IllegalArgumentException ->
                Toast.makeText(
                    this,
                    "티켓 수량은 ${Ticket.MIN_TICKET_COUNT}~${Ticket.MAX_TICKET_COUNT} 사이어야 합니다!!",
                    Toast.LENGTH_SHORT,
                ).show()

            else -> Toast.makeText(this, "예상치 못한 에러가 발생했습니다!!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showSnackBar(e: Throwable) {
        when (e) {
            is NoSuchElementException ->
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "해당하는 상영 정보가 없습니다!!",
                    Snackbar.LENGTH_SHORT,
                ).show()

            is IllegalArgumentException ->
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "티켓 수량은 ${Ticket.MIN_TICKET_COUNT}~${Ticket.MAX_TICKET_COUNT} 사이어야 합니다!!",
                    Toast.LENGTH_SHORT,
                ).show()

            else ->
                Snackbar.make(findViewById(android.R.id.content), "예상치 못한 에러가 발생했습니다!!", Snackbar.LENGTH_SHORT)
                    .show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    companion object {
        private const val DEFAULT_ID = -1
        private const val PUT_EXTRA_KEY_ID = "screenId"
        private const val PUT_TICKET_STATE_KEY = "ticketCount"

        fun startActivity(
            context: Context,
            id: Int,
        ) {
            val intent = Intent(context, ScreenDetailActivity::class.java)
            intent.putExtra(PUT_EXTRA_KEY_ID, id)
            context.startActivity(intent)
        }
    }
}
