package woowacourse.movie.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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

    private val iScreenDetailViewHolder: IScreenDetailViewHolder by lazy { ScreenDetailViewHolder() }
    private lateinit var ticketViewHolder: ITicketViewHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_detail)
        initView()
    }

    private fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = intent.getIntExtra(PUT_EXTRA_KEY_ID, DEFAULT_ID)
        ticketViewHolder = TicketViewHolder().apply { initClickListener(id) }

        presenter.loadScreen(id)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PUT_TICKET_STATE_KEY, ticketViewHolder.ticketCount())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.let {
            ticketViewHolder.restoreTicketCount(it.getInt(PUT_TICKET_STATE_KEY))
        }
    }

    override fun showScreen(screen: ScreenDetailUI) {
        iScreenDetailViewHolder.show(screen)
    }

    override fun showTicket(count: Int) {
        ticketViewHolder.updateTicketCount(count)
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

            else -> Snackbar.make(findViewById(android.R.id.content), "예상치 못한 에러가 발생했습니다!!", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    inner class ScreenDetailViewHolder : IScreenDetailViewHolder {
        private val title: TextView = findViewById(R.id.tv_title)
        private val date: TextView = findViewById(R.id.tv_screen_date)
        private val runningTime: TextView = findViewById(R.id.tv_screen_running_time)
        private val description: TextView = findViewById(R.id.tv_description)
        private val poster: ImageView = findViewById(R.id.iv_poster)

        override fun show(screen: ScreenDetailUI) {
            with(screen) {
                title.text = movieDetailUI.title
                this@ScreenDetailViewHolder.date.text = date
                runningTime.text = movieDetailUI.runningTime.toString()
                description.text = movieDetailUI.description
                poster.setImageResource(movieDetailUI.image.imageSource as Int)
            }
        }
    }

    inner class TicketViewHolder : ITicketViewHolder {
        private val ticketCount: TextView = findViewById(R.id.tv_count)
        private val plusBtn: Button = findViewById(R.id.btn_plus)
        private val minusBtn: Button = findViewById(R.id.btn_minus)
        private val reserveDone: Button = findViewById(R.id.btn_reserve_done)

        fun initClickListener(id: Int) {
            plusBtn.setOnClickListener {
                presenter.plusTicket()
            }
            minusBtn.setOnClickListener {
                presenter.minusTicket()
            }
            reserveDone.setOnClickListener {
                presenter.reserve(id)
            }
        }

        override fun updateTicketCount(count: Int) {
            ticketCount.text = count.toString()
        }

        override fun ticketCount(): Int = ticketCount.text.toString().toInt()

        override fun restoreTicketCount(count: Int) {
            ticketCount.text = count.toString()
        }
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
