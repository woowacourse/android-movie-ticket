package woowacourse.movie.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import woowacourse.movie.R
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.repository.DummyMovies
import woowacourse.movie.domain.repository.DummyReservation
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.ui.ScreenDetailUI
import woowacourse.movie.ui.reservation.ReservationActivity

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_detail)
        initView()

        ArrayAdapter.createFromResource(
            this,
            R.array.fake_date,
            android.R.layout.simple_spinner_item,
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dateSpinner.adapter = adapter
        }


        ArrayAdapter.createFromResource(
            this,
            R.array.fake_time,
            android.R.layout.simple_spinner_item,
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            timeSpinner.adapter = adapter
        }
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
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.let { bundle ->
            val count = bundle.getInt(PUT_TICKET_STATE_KEY)
            ticketView.restoreTicketCount(count)
            presenter.saveTicket(count)
            presenter.loadTicket()
        }
    }

    override fun showScreen(screen: ScreenDetailUI) {
        screenDetailView.show(screen)
    }

    override fun showTicket(count: Int) {
        ticketView.updateTicketCount(count)
    }

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
