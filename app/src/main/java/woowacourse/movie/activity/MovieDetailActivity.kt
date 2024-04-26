package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import woowacourse.movie.R
import woowacourse.movie.contract.MovieDetailContract
import woowacourse.movie.format.format
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.presenter.MovieDetailPresenter

class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {
    private var ticketNum: Int = 1
    private val numberOfPurchases by lazy {
        findViewById<TextView>(R.id.quantity_text_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)
        val presenter = MovieDetailPresenter(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<ImageView>(R.id.movie_thumbnail)
            .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.movie_making_poster))

        val ticketPlusButton = findViewById<Button>(R.id.plus_button)
        val ticketMinusButton = findViewById<Button>(R.id.minus_button)
        val ticketBuyButton = findViewById<Button>(R.id.buy_ticket_button)
        val movieId = intent.getIntExtra("MovieId", -1)
        presenter.loadMovie(movieId)
        ticketPlusButton.setOnClickListener {
            presenter.plusTicketNum()
        }
        ticketMinusButton.setOnClickListener {
            presenter.minusTicketNum()
        }
        ticketBuyButton.setOnClickListener {
            presenter.purchase(movieId)
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

    override fun displayMovie(movie: Movie) {
        val movieDetail = movie.movieDetail
        findViewById<TextView>(R.id.movie_title_large).text =
            movieDetail.title.format()
        findViewById<TextView>(R.id.movie_release_date_large).text =
            movie.screeningDate.format()
        findViewById<TextView>(R.id.movie_running_time).text =
            movieDetail.runningTime.format()
        findViewById<TextView>(R.id.movie_synopsis).text =
            movieDetail.synopsis.format()
    }

    override fun displayTicketNum(ticketNum: Int) {
        this.ticketNum = ticketNum
        numberOfPurchases.text = this.ticketNum.toString()
    }

    override fun navigateToPurchaseConfirmation() {
        val intent = Intent(this, PurchaseConfirmationActivity::class.java)
        startActivity(intent)
    }

    override fun displayScreeningDays() {
    }
}
