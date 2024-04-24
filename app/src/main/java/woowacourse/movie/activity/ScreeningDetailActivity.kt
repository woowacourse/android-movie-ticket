package woowacourse.movie.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import woowacourse.movie.R
import woowacourse.movie.contract.ScreeningDetailContract
import woowacourse.movie.model.Reservation
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Synopsis
import woowacourse.movie.model.movieInfo.Title
import woowacourse.movie.model.screening.Screening
import woowacourse.movie.model.screening.ScreeningDate
import woowacourse.movie.presenter.ScreeningDetailPresenter

class ScreeningDetailActivity : AppCompatActivity(), ScreeningDetailContract.View {

    private var ticketNum: Int = 1
    private val numberOfPurchases by lazy {
        findViewById<TextView>(R.id.quantity_text_view)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)
        val presenter = ScreeningDetailPresenter(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<ImageView>(R.id.movie_thumbnail)
            .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.movie_making_poster))

        val ticketPlusButton = findViewById<Button>(R.id.plus_button)
        val ticketMinusButton = findViewById<Button>(R.id.minus_button)
        val ticketBuyButton = findViewById<Button>(R.id.buy_ticket_button)
        val screeningId = intent.getIntExtra("ScreeningId", -1)
        ticketPlusButton.setOnClickListener {
            presenter.plusTicketNum(ticketNum)
        }
        ticketMinusButton.setOnClickListener {
            presenter.minusTicketNum(ticketNum)
        }
        ticketBuyButton.setOnClickListener {
            presenter.purchase(screeningId, ticketNum)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun displayScreening(screening: Screening) {
        val movie = screening.movie
        findViewById<TextView>(R.id.movie_title_large).text =
            movie.title.format()
        findViewById<TextView>(R.id.movie_release_date_large).text =
            screening.date.format()
        findViewById<TextView>(R.id.movie_running_time).text =
            movie.runningTime.format()
        findViewById<TextView>(R.id.movie_synopsis).text =
            movie.synopsis.format()
    }

    override fun displayTicketNum(ticketNum: Int) {
        this.ticketNum = ticketNum
        numberOfPurchases.text = this.ticketNum.toString()
    }

    override fun navigateToPurchaseConfirmation(reservation: Reservation) {
        val intent =
            Intent(this, PurchaseConfirmationActivity::class.java).apply {
                putExtra("Reservation", reservation)
            }
        startActivity(intent)
    }

    private fun Title.format() = content

    private fun ScreeningDate.format() = "${date.year}.${date.monthValue}.${date.dayOfMonth}"

    private fun RunningTime.format() = time.toString() + "분"

    private fun Synopsis.format() = content
}
