package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReservationActivity : AppCompatActivity(), ReservationView {
    private val reservationPresenter = ReservationPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        reservationPresenter.onViewCreated()
    }

    override fun readMovieData() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("movie", Movie::class.java)
        } else {
            intent.getSerializableExtra("movie") as? Movie
        }

    override fun initializeMovieDetails(movie: Movie) {
        findViewById<ImageView>(R.id.poster).setImageResource(movie.poster)
        findViewById<TextView>(R.id.movie_title).text = movie.title
        findViewById<TextView>(R.id.content).text = movie.content
        findViewById<TextView>(R.id.opening_day).text = "상영일: ${movie.openingDay}"
        findViewById<TextView>(R.id.running_time).text = "러닝타임: ${movie.runningTime}분"
    }

    override fun setupReservationCompletedButton(movie: Movie) {
        findViewById<Button>(R.id.btn_reservation_completed).setOnClickListener {
            reservationPresenter.onClicked(movie)
        }
    }

    override fun moveToCompletedActivity(
        movie: Movie,
        quantity: Int,
    ) {
        val intent = Intent(this@ReservationActivity, ReservationCompletedActivity::class.java)
        intent.putExtra("ticket", Ticket(movie, quantity))
        startActivity(intent)
    }

    override fun setupTicketQuantityControls() {
        findViewById<Button>(R.id.btn_minus).setOnClickListener { reservationPresenter.minus() }
        findViewById<Button>(R.id.btn_plus).setOnClickListener { reservationPresenter.plus() }
    }

    override fun setQuantityText(newText: String) {
        findViewById<TextView>(R.id.quantity).text = newText
    }
}
