package woowacourse.movie.screen.reservation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Quantity
import woowacourse.movie.model.Reservation
import woowacourse.movie.screen.completed.ReservationCompletedActivity

class ReservationActivity : AppCompatActivity(), ReservationContract.View {
    private val presenter = ReservationPresenter(this)
    private val quantityTv by lazy {
        findViewById<TextView>(R.id.reservation_quantity)
    }

    private val posterIv by lazy {
        findViewById<ImageView>(R.id.reservation_poster)
    }

    private val movieTitleTv by lazy {
        findViewById<TextView>(R.id.reservation_movie_title)
    }

    private val movieContentTv by lazy {
        findViewById<TextView>(R.id.reservation_content)
    }

    private val openingDayTv by lazy {
        findViewById<TextView>(R.id.reservation_opening_day)
    }

    private val runningTimeTv by lazy {
        findViewById<TextView>(R.id.reservation_running_time)
    }

    private val minusBtn by lazy {
        findViewById<Button>(R.id.btn_minus)
    }

    private val plusBtn by lazy {
        findViewById<Button>(R.id.btn_plus)
    }

    private val completeBtn by lazy {
        findViewById<Button>(R.id.btn_reservation_completed)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.onStart()
    }

    override fun readMovieData() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("movie", Movie::class.java)
        } else {
            intent.getSerializableExtra("movie") as? Movie
        }

    override fun initializeMovieDetails(movie: Movie) {
        val (openingDayText, runningTimeText) = getFormattedText(movie)
        posterIv.setImageResource(movie.poster)
        movieTitleTv.text = movie.title
        movieContentTv.text = movie.content
        openingDayTv.text = openingDayText
        runningTimeTv.text = runningTimeText
    }

    private fun getFormattedText(movie: Movie): Pair<String, String> {
        val openingDayStringResource =
            String.format(getString(R.string.opening_day), movie.openingDay)
        val runningTimeStringResource =
            String.format(getString(R.string.running_time), movie.runningTime)
        return Pair(openingDayStringResource, runningTimeStringResource)
    }

    override fun setupReservationCompletedButton(movie: Movie) {
        completeBtn.setOnClickListener {
            presenter.onReservationCompleted(movie)
        }
    }

    override fun moveToCompletedActivity(reservation: Reservation) {
        startActivity(ReservationCompletedActivity.getIntent(this, reservation))
    }

    override fun setupTicketQuantityControls(quantity: Quantity) {
        setQuantityText(quantity.value.toString())
        minusBtn.setOnClickListener { presenter.minus() }
        plusBtn.setOnClickListener { presenter.plus() }
    }

    override fun setQuantityText(newText: String) {
        quantityTv.text = newText
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val MOVIE_INTENT_KEY = "movie"

        fun getIntent(
            context: Context,
            movie: Movie,
        ): Intent {
            return Intent(context, ReservationActivity::class.java).apply {
                putExtra(MOVIE_INTENT_KEY, movie)
            }
        }
    }
}
