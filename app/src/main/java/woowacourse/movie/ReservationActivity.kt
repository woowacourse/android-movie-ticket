package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReservationActivity : AppCompatActivity() {
    private var ticketQuantity = DEFAULT_QUANTITY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        val movie = readMovieData() ?: return
        initializeMovieDetails(movie)
        setupReservationCompletedButton(movie)
        setupTicketQuantityControls()
    }

    private fun readMovieData() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("movie", Movie::class.java)
        } else {
            intent.getSerializableExtra("movie") as? Movie
        }

    private fun initializeMovieDetails(movie: Movie) {
        findViewById<ImageView>(R.id.poster).setImageResource(movie.poster)
        findViewById<TextView>(R.id.movie_title).text = movie.title
        findViewById<TextView>(R.id.content).text = movie.content
        findViewById<TextView>(R.id.opening_day).text = "상영일: ${movie.openingDay}"
        findViewById<TextView>(R.id.running_time).text = "러닝타임: ${movie.runningTime}분"
    }

    private fun setupReservationCompletedButton(movie: Movie) {
        findViewById<Button>(R.id.btn_reservation_completed).setOnClickListener {
            val intent = Intent(this@ReservationActivity, ReservationCompletedActivity::class.java)
            intent.putExtra("ticket", Ticket(movie, quantity = ticketQuantity))
            startActivity(intent)
        }
    }

    private fun setupTicketQuantityControls() {
        findViewById<Button>(R.id.btn_minus).setOnClickListener { adjustTicketQuantity(-1) }
        findViewById<Button>(R.id.btn_plus).setOnClickListener { adjustTicketQuantity(1) }
    }

    private fun adjustTicketQuantity(change: Int) {
        ticketQuantity = (ticketQuantity + change).coerceAtLeast(MIN_QUANTITY)
        findViewById<TextView>(R.id.quantity).text = ticketQuantity.toString()
    }

    companion object {
        private const val DEFAULT_QUANTITY = 1
        private const val MIN_QUANTITY = 1
    }
}
