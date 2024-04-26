package woowacourse.movie.movieDetail

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
import woowacourse.movie.model.movieInfo.MovieInfo
import woowacourse.movie.purchaseConfirmation.PurchaseConfirmationActivity

class MovieDetailActivity : AppCompatActivity(), MovieDetailContract.View {
    private var ticketNum = 1
    private lateinit var presenter: MovieDetailContract.Presenter

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = MovieDetailPresenter(
            view = this@MovieDetailActivity,
            intent = intent,
        )
        presenter.load()
        setupEventListeners()
    }


    private fun setupEventListeners() {
        findViewById<Button>(R.id.plus_button).setOnClickListener {
            presenter.onTicketPlusClicked(ticketNum)
        }

        findViewById<Button>(R.id.minus_button).setOnClickListener {
            presenter.onTicketMinusClicked(ticketNum)
        }

        findViewById<Button>(R.id.buy_ticket_button).setOnClickListener {
            val theater = presenter.getTheater()
            val intent = Intent(this, PurchaseConfirmationActivity::class.java).apply {
                putExtra("ticketNum", ticketNum)
                putExtra("Theater", theater)
            }
            presenter.onBuyTicketClicked(intent)
        }
    }

    override fun initializeViews(movieInfo: MovieInfo) {
        findViewById<TextView>(R.id.movie_title_large).text = movieInfo.title.toString()
        findViewById<TextView>(R.id.movie_release_date_large).text =
            movieInfo.releaseDate.toString()
        findViewById<TextView>(R.id.movie_running_time).text = movieInfo.runningTime.toString()
        findViewById<TextView>(R.id.movie_synopsis).text = movieInfo.synopsis.toString()
        findViewById<ImageView>(R.id.movie_thumbnail)
            .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.movie_making_poster))

    }

    override fun navigateToPurchaseConfirmation(intent: Intent) {
        startActivity(intent)
    }

    override fun onTicketCountChanged(ticketNum: Int) {
        this.ticketNum = ticketNum
        findViewById<TextView>(R.id.quantity_text_view).text = this.ticketNum.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}
