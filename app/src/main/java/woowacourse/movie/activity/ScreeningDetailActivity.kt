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
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.presenter.ScreeningDetailPresenter

class ScreeningDetailActivity : AppCompatActivity(), ScreeningDetailContract.View {
    private val numberOfPurchases by lazy {
        findViewById<TextView>(R.id.quantity_text_view)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)
        val presenter =
            ScreeningDetailPresenter(
                intent,
                this,
            )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<ImageView>(R.id.movie_thumbnail)
            .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.movie_making_poster))

        val ticketPlusButton = findViewById<Button>(R.id.plus_button)
        val ticketMinusButton = findViewById<Button>(R.id.minus_button)
        val ticketBuyButton = findViewById<Button>(R.id.buy_ticket_button)

        ticketPlusButton.setOnClickListener {
            presenter.plusTicketNum()
        }
        ticketMinusButton.setOnClickListener {
            presenter.minusTicketNum()
        }
        ticketBuyButton.setOnClickListener {
            presenter.onBuyButtonClicked()
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

    override fun displayMovie(movie: MovieInfo) {
        if (movie != null) {
            findViewById<TextView>(R.id.movie_title_large).text =
                movie.title.toString()
            findViewById<TextView>(R.id.movie_release_date_large).text =
                movie.releaseDate.toString()
            findViewById<TextView>(R.id.movie_running_time).text =
                movie.runningTime.toString()
            findViewById<TextView>(R.id.movie_synopsis).text =
                movie.synopsis.toString()
        }
    }

    override fun displayTicketNum(ticketNum: Int) {
        numberOfPurchases.text = ticketNum.toString()
    }

    override fun navigateToPurchaseConfirmation(reservation: Reservation) {
        val intent =
            Intent(this, PurchaseConfirmationActivity::class.java).apply {
                putExtra("Reservation", reservation)
            }
        startActivity(intent)
    }
}
