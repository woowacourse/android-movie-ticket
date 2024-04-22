package woowacourse.movie.presentation.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.repository.DummyReservation
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.ui.detail.DetailContract.Presenter
import woowacourse.movie.presentation.ui.detail.DetailContract.View
import woowacourse.movie.presentation.ui.reservation.ReservationActivity

class DetailActivity : BaseActivity(), View {
    override val layoutResourceId: Int
        get() = R.layout.activity_detail
    override val presenter: Presenter by lazy {
        DetailPresenter(this, DummyScreens(), DummyReservation)
    }

    private val title: TextView by lazy { findViewById(R.id.tv_title) }
    private val date: TextView by lazy { findViewById(R.id.tv_screen_date) }
    private val runningTime: TextView by lazy { findViewById(R.id.tv_screen_running_time) }
    private val description: TextView by lazy { findViewById(R.id.tv_description) }
    private val poster: ImageView by lazy { findViewById(R.id.iv_poster) }
    private val ticketCount: TextView by lazy { findViewById(R.id.tv_count) }
    private val plusBtn: Button by lazy { findViewById(R.id.btn_plus) }
    private val minusBtn: Button by lazy { findViewById(R.id.btn_minus) }
    private val reserveDone: Button by lazy { findViewById(R.id.btn_reserve_done) }

    override fun initStartView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = intent.getIntExtra(PUT_EXTRA_KEY_ID, DEFAULT_ID)
        presenter.loadScreen(id)
        initClickListener()
    }

    private fun initClickListener() {
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

    override fun showScreen(screen: Screen) {
        with(screen) {
            title.text = movie.title
            this@DetailActivity.date.text = date
            runningTime.text = movie.runningTime.toString()
            description.text = movie.description
            poster.setImageResource(movie.imageSrc)
        }
    }

    override fun showTicket(count: Int) {
        ticketCount.text = count.toString()
    }

    override fun navigateToReservation(id: Int) {
        ReservationActivity.startActivity(this, id)
        back()
    }

    override fun back() = finish()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        back()
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PUT_TICKET_STATE_KEY, ticketCount.text.toString().toInt())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val count = savedInstanceState.getInt(PUT_TICKET_STATE_KEY, DEFAULT_TICKET_COUNT)
        if (count != DEFAULT_TICKET_COUNT) {
            ticketCount.text = count.toString()
        }
    }

    companion object {
        private const val DEFAULT_ID = -1
        private const val PUT_EXTRA_KEY_ID = "screenId"

        private const val DEFAULT_TICKET_COUNT = -1
        private const val PUT_TICKET_STATE_KEY = "ticketCount"

        fun startActivity(
            context: Context,
            id: Int,
        ) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(PUT_EXTRA_KEY_ID, id)
            context.startActivity(intent)
        }
    }
}
