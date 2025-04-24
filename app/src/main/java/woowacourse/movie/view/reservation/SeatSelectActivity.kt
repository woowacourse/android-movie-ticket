package woowacourse.movie.view.reservation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.model.ReservationInfo
import woowacourse.movie.model.Seats
import woowacourse.movie.view.Extras
import woowacourse.movie.view.ReservationUiFormatter
import woowacourse.movie.view.getParcelableExtraCompat

class SeatSelectActivity :
    AppCompatActivity(),
    SeatSelectContract.View {
    private val movieTicket by lazy { getMovieTicketData() }
    private val reservationDialog by lazy { ReservationDialog() }
    private lateinit var priceTextView: TextView
    private lateinit var confirmButton: TextView
    private var selectedSeats = Seats.create()
    private var totalPrice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat_select)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<TextView>(R.id.tv_seat_select_movie_title).text = movieTicket!!.title
        val tl = findViewById<TableLayout>(R.id.tl_seat)
        setupSeatSelection(tl)

        priceTextView = findViewById(R.id.tv_seat_select_total_price)
        priceTextView.text = getString(R.string.seat_select_ticket_price).format(totalPrice)
        confirmButton =
            findViewById<TextView?>(R.id.tv_seat_select_confirm).apply {
                isClickable = false
                alpha = 0.1f
            }
        confirmButton.setOnClickListener {
            showReservationDialog(
                getString(R.string.reservation_dialog_title),
                getString(R.string.reservation_dialog_message),
            )
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getMovieTicketData(): MovieTicket? = intent.getParcelableExtraCompat(Extras.TicketData.TICKET_KEY)

    private fun setupSeatSelection(tableLayout: TableLayout) {
        for (i in 0 until tableLayout.childCount) {
            val row = tableLayout.getChildAt(i)
            if (row is TableRow) {
                for (j in 0 until row.childCount) {
                    val seatView = row.getChildAt(j)
                    if (seatView is TextView) {
                        val seatId = seatView.text.toString()
                        seatView.tag = seatId
                        seatView.setOnClickListener {
                            handleSeatClick(seatView)
                        }
                    }
                }
            }
        }
    }

    private fun handleSeatClick(seatView: TextView) {
        val seatId = seatView.text.toString()
        if (selectedSeats.contains(seatId)) {
            selectedSeats.remove(seatId)
            seatView.setBackgroundResource(R.color.white)
            findViewById<Button>(R.id.tv_seat_select_confirm).alpha = 0.1f
            confirmButton.isClickable = false
        } else {
            if (selectedSeats.size >= movieTicket!!.count) {
                Toast
                    .makeText(
                        this,
                        getString(R.string.seat_select_error_count, movieTicket!!.count),
                        Toast.LENGTH_SHORT,
                    ).show()
                return
            }
            selectedSeats.add(seatId)
            seatView.setBackgroundResource(R.color.yellow)
            if (selectedSeats.size == movieTicket!!.count) {
                confirmButton.alpha = 1f
                confirmButton.isClickable = true
            }
        }
        priceTextView.text =
            getString(R.string.seat_select_ticket_price).format(
                ReservationUiFormatter().priceToUI(
                    selectedSeats.totalPrice,
                ),
            )
    }

    override fun showReservationDialog(
        title: String,
        message: String,
    ) {
        reservationDialog.show(
            this,
            title,
            message,
            { dialog -> dialog.dismiss() },
            { _ ->
                val intent = reservationInfoIntent()
                startActivity(intent)
                finish()
            },
        )
    }

    private fun reservationInfoIntent(): Intent {
        val reservationInfo =
            ReservationInfo(
                title = movieTicket!!.title,
                date = movieTicket!!.date,
                time = movieTicket!!.time,
                seats = selectedSeats.labels(),
                price = selectedSeats.totalPrice,
            )

        return Intent(this, ReservationCompleteActivity::class.java).apply {
            putExtra(Extras.ReservationInfoData.RESERVATION_KEY, reservationInfo)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
