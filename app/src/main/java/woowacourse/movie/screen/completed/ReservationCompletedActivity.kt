package woowacourse.movie.screen.completed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.screen.reservation.ReservationModel

class ReservationCompletedActivity : AppCompatActivity(), ReservationCompletedContract.View {
    private val presenter = ReservationCompletedPresenter(this)
    private val movieTitleTv by lazy {
        findViewById<TextView>(R.id.completed_movie_title)
    }
    private val reservationDateTv by lazy {
        findViewById<TextView>(R.id.completed_reservation_date)
    }
    private val quantityTv by lazy {
        findViewById<TextView>(R.id.completed_quantity)
    }
    private val priceTv by lazy {
        findViewById<TextView>(R.id.completed_price)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_completed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = getReservationId()
        presenter.fetchReservationDetails(id)
    }

    private fun getReservationId(): Long = intent.getLongExtra(RESERVATION_ID, -1L)

    override fun initializeReservationDetails(reservation: ReservationModel) {
        movieTitleTv.text = reservation.title
        reservationDateTv.text = reservation.schedule
        quantityTv.text = reservation.formatQuantity(this)
        priceTv.text = reservation.formatPrice(this)
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
        const val RESERVATION_ID = "reservation_id"

        fun getIntent(
            context: Context,
            id: Long,
        ): Intent {
            return Intent(context, ReservationCompletedActivity::class.java).apply {
                putExtra(RESERVATION_ID, id)
            }
        }
    }
}
