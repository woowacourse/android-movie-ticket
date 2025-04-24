package woowacourse.movie.view.reservation

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
import woowacourse.movie.model.Seat
import woowacourse.movie.view.Extras
import woowacourse.movie.view.getParcelableExtraCompat

class SeatSelectActivity : AppCompatActivity() {
    private val movieTicket by lazy { getMovieTicketData() }
    private val selectedSeats = mutableSetOf<String>()
    private lateinit var priceTextView: TextView
    private lateinit var confirmButton: Button
    private var price: Int = 0

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
        priceTextView.text = getString(R.string.seat_select_ticket_price).format(price)
        confirmButton = findViewById<Button>(R.id.tv_confirm)
        confirmButton.isClickable = false
        confirmButton.alpha = 0.1f
    }

    private fun getMovieTicketData(): MovieTicket? =
        intent.getParcelableExtraCompat(Extras.TicketData.TICKET_KEY)

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
        val grade = Seat.fromSeatId(seatId)
        if (selectedSeats.contains(seatId)) {
            selectedSeats.remove(seatId)
            price -= grade.price
            seatView.setBackgroundResource(R.color.white)
            findViewById<Button>(R.id.tv_confirm).alpha = 0.1f
            confirmButton.isClickable = false
        } else {
            if (selectedSeats.size >= movieTicket!!.count) {
                Toast.makeText(
                    this,
                    "최대 ${movieTicket!!.count}개의 좌석만 선택할 수 있어요.",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
            selectedSeats.add(seatId)
            price += grade.price
            seatView.setBackgroundResource(R.color.yellow)
            if (selectedSeats.size == movieTicket!!.count) {
                findViewById<Button>(R.id.tv_confirm).alpha = 1f
                confirmButton.isClickable = true
            }
        }
        priceTextView.text = getString(R.string.seat_select_ticket_price).format(price)
    }
}
