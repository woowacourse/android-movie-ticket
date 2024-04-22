package woowacourse.movie.reservation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.completed.ReservationCompletedActivity
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.UiMovie

class ReservationActivity : AppCompatActivity(), ReservationContract.View {
    private val reservationPresenter = ReservationPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        reservationPresenter.onViewCreated(intent)
        setupTicketQuantityControls()
    }

    override fun initializeMovieDetails(uiMovie: UiMovie) {
        findViewById<ImageView>(R.id.poster).setImageResource(uiMovie.poster)
        findViewById<TextView>(R.id.movie_title).text = uiMovie.title
        findViewById<TextView>(R.id.content).text = uiMovie.content
        findViewById<TextView>(R.id.opening_day).text =
            getString(R.string.movie_opening_day, uiMovie.openingDay)
        findViewById<TextView>(R.id.running_time).text =
            getString(R.string.movie_running_time, uiMovie.runningTime.toString())
    }

    override fun setupReservationCompletedButton(uiMovie: UiMovie) {
        findViewById<Button>(R.id.btn_reservation_completed).setOnClickListener {
            reservationPresenter.onClicked(uiMovie)
        }
    }

    override fun moveToCompletedActivity(ticket: Ticket) {
        val intent = Intent(this@ReservationActivity, ReservationCompletedActivity::class.java)
        intent.putExtra(EXTRA_TICKET, ticket)
        startActivity(intent)
    }

    override fun setQuantityText(newText: String) {
        findViewById<TextView>(R.id.quantity).text = newText
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupTicketQuantityControls() {
        findViewById<Button>(R.id.btn_minus).setOnClickListener { reservationPresenter.decreaseQuantity() }
        findViewById<Button>(R.id.btn_plus).setOnClickListener { reservationPresenter.increaseQuantity() }
    }

    companion object {
        const val EXTRA_TICKET = "ticket"
    }
}
