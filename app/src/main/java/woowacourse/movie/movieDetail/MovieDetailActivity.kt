// MovieDetailActivity.kt
package woowacourse.movie.movieDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import woowacourse.movie.R

class MovieDetailActivity : AppCompatActivity(), MovieDetailView {
    private lateinit var presenter: MovieDetailActivityPresenter
    private var ticketNum = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = MovieDetailActivityPresenter(this, intent)
        initializeViews()
        setupEventListeners()
    }

    private fun initializeViews() {
        val movie = presenter.movie
        findViewById<TextView>(R.id.movie_title_large).text = movie?.title.toString()
        findViewById<TextView>(R.id.movie_release_date_large).text = movie?.releaseDate.toString()
        findViewById<TextView>(R.id.movie_running_time).text = movie?.runningTime.toString()
        findViewById<TextView>(R.id.movie_synopsis).text = movie?.synopsis.toString()
        findViewById<ImageView>(R.id.movie_thumbnail)
            .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.movie_making_poster))
    }

    private fun setupEventListeners() {
        findViewById<Button>(R.id.plus_button).setOnClickListener {
            presenter.onTicketPlusClicked(ticketNum)
        }

        findViewById<Button>(R.id.minus_button).setOnClickListener {
            presenter.onTicketMinusClicked(ticketNum)
        }

        findViewById<Button>(R.id.buy_ticket_button).setOnClickListener {
            presenter.onBuyTicketClicked(ticketNum)
        }
    }

    override fun navigateToPurchaseConfirmation(sendingIntent: Intent) {
        startActivity(sendingIntent)
    }

    override fun onTicketCountChanged(newTicketNum: Int) {
        ticketNum = newTicketNum
        findViewById<TextView>(R.id.quantity_text_view).text = ticketNum.toString()
    }

    override fun getContext(): Context = this

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}
