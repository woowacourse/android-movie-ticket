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

    private val movieViewHolder: ScreenDetailViewHolder by lazy { ScreenDetailViewHolderImpl() }
    private val ticketViewHolder: TicketViewHolder by lazy { TicketViewHolderImpl().apply { initClickListener() } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_detail)
        initView()
    }

    private fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = intent.getIntExtra(PUT_EXTRA_KEY_ID, DEFAULT_ID)
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
        movieViewHolder.show(screen)
    }

    override fun showTicket(count: Int) {
        ticketViewHolder.updateTicketCount(count)
    }

    override fun navigateToReservation(id: Int) {
        ReservationActivity.startActivity(this, id)
        finish()
    }

    override fun goToBack(message: String) {
        showToastMessage(message)
        finish()
    }

    override fun unexpectedFinish(message: String) {
        showSnackBar(message)
        finish()
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showSnackBar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    inner class ScreenDetailViewHolderImpl : ScreenDetailViewHolder {
        private val title: TextView = findViewById(R.id.tv_title)
        private val date: TextView = findViewById(R.id.tv_screen_date)
        private val runningTime: TextView = findViewById(R.id.tv_screen_running_time)
        private val description: TextView = findViewById(R.id.tv_description)
        private val poster: ImageView = findViewById(R.id.iv_poster)

        override fun show(screen: ScreenDetailUI) {
            with(screen) {
                title.text = movieDetailUI.title
                this@ScreenDetailViewHolderImpl.date.text = date
                runningTime.text = movieDetailUI.runningTime.toString()
                description.text = movieDetailUI.description
                poster.setImageResource(movieDetailUI.image.imageSource as Int)
            }
        }
    }

    inner class TicketViewHolderImpl : TicketViewHolder {
        private val ticketCount: TextView = findViewById(R.id.tv_count)
        private val plusBtn: Button = findViewById(R.id.btn_plus)
        private val minusBtn: Button = findViewById(R.id.btn_minus)
        private val reserveDone: Button = findViewById(R.id.btn_reserve_done)

        fun initClickListener() {
            plusBtn.setOnClickListener {
                presenter.plusTicket()
            }
            minusBtn.setOnClickListener {
                presenter.minusTicket()
            }
            reserveDone.setOnClickListener {
                presenter.reserve()
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
