package woowacourse.movie.screen.reservation

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
        findViewById<ImageView>(R.id.reservation_poster).setImageResource(movie.poster)
        findViewById<TextView>(R.id.reservation_movie_title).text = movie.title
        findViewById<TextView>(R.id.reservation_content).text = movie.content
        findViewById<TextView>(R.id.reservation_opening_day).text = movie.openingDay
        findViewById<TextView>(R.id.reservation_running_time).text = "${movie.runningTime}분"
    }

    override fun setupReservationCompletedButton(movie: Movie) {
        findViewById<Button>(R.id.btn_reservation_completed).setOnClickListener {
            presenter.onReservationCompleted(movie)
        }
    }

    override fun moveToCompletedActivity(reservation: Reservation) {
        val intent = Intent(this@ReservationActivity, ReservationCompletedActivity::class.java)
        intent.putExtra("reservation", reservation)
        startActivity(intent)
    }

    override fun setupTicketQuantityControls(quantity: Quantity) {
        setQuantityText(quantity.value.toString())
        findViewById<Button>(R.id.btn_minus).setOnClickListener { presenter.minus() }
        findViewById<Button>(R.id.btn_plus).setOnClickListener { presenter.plus() }
    }

    override fun setQuantityText(newText: String) {
        findViewById<TextView>(R.id.reservation_quantity).text = newText
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
