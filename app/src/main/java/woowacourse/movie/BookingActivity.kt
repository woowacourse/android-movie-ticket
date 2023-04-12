package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BookingActivity : AppCompatActivity() {
    var ticketCount = TicketCount()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        val movie = getMovie()
        initView(movie)
        gatherClickListeners()
    }

    private fun getMovie(): Movie {
        val movieId = intent.getLongExtra(MOVIE_ID, -1)
        return MovieData.findMovieById(movieId)
    }

    private fun gatherClickListeners() {
        clickMinus()
        clickPlus()
        clickBookingComplete()
    }

    private fun initView(movie: Movie) {
        findViewById<ImageView>(R.id.imageBookingPoster).setImageResource(movie.poster)
        findViewById<TextView>(R.id.textBookingTitle).text = movie.title
        findViewById<TextView>(R.id.textBookingScreeningDate).text =
            movie.screeningDate.formatScreenDate()
        findViewById<TextView>(R.id.textBookingRunningTime).text =
            getString(R.string.running_time).format(movie.runningTime)
        findViewById<TextView>(R.id.textBookingDescription).text = movie.description
        findViewById<TextView>(R.id.textBookingTicketCount).text = ticketCount.value.toString()
    }

    private fun clickMinus() {
        findViewById<Button>(R.id.buttonBookingMinus).setOnClickListener {
            ticketCount = ticketCount.minus()
            findViewById<TextView>(R.id.textBookingTicketCount).text = ticketCount.value.toString()
        }
    }

    private fun clickPlus() {
        findViewById<Button>(R.id.buttonBookingPlus).setOnClickListener {
            ticketCount = ticketCount.plus()
            findViewById<TextView>(R.id.textBookingTicketCount).text = ticketCount.value.toString()
        }
    }

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"

        fun getIntent(context: Context, movieId: Long): Intent {
            return Intent(context, BookingActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
            }
        }
    }
}
