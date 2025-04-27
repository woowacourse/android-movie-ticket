package woowacourse.movie.view

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import woowacourse.movie.R
import woowacourse.movie.contract.SeatSelectionContract
import woowacourse.movie.domain.Row
import woowacourse.movie.domain.Seat
import woowacourse.movie.domain.SeatGrade
import woowacourse.movie.domain.ticket.Ticket
import woowacourse.movie.presenter.SeatSelectionPresenter
import woowacourse.movie.view.util.ErrorMessage
import java.time.LocalDateTime

class SeatSelectionActivity :
    AppCompatActivity(),
    SeatSelectionContract.View {
    private var presenter: SeatSelectionContract.Presenter? = null
    private var selectedSeats: Set<Seat> = setOf()
    private lateinit var seatsLayout: TableLayout
    private lateinit var titleView: TextView
    private lateinit var priceView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat_selection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        savedInstanceState.getSelectedSeats()?.let { selectedSeats = it }
        Log.e("selectedSeats", selectedSeats.toString())
        initPresenter()
        findViews()
        presenter?.presentSeats()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_SEATS, ArrayList(selectedSeats))
    }

    private fun initPresenter() {
        val ticket =
            intent.getTicketExtra(EXTRA_TICKET) ?: error(ErrorMessage(CAUSE_TICKET).notProvided())
        presenter = SeatSelectionPresenter(this, ticket)
    }

    @Suppress("DEPRECATION")
    private fun Bundle?.getSelectedSeats(): Set<Seat>? {
        if (this == null) return null
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
                getSerializable(
                    KEY_SEATS,
                    ArrayList::class.java,
                )?.let { (it as ArrayList<Seat>).toSet() }

            else -> (getSerializable(KEY_SEATS) as? ArrayList<Seat>)?.toSet()
        }
    }

    @Suppress("DEPRECATION")
    private fun Intent.getTicketExtra(key: String): Ticket? =
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
                getSerializableExtra(
                    key,
                    Ticket::class.java,
                )

            else -> getSerializableExtra(key) as? Ticket
        }

    private fun findViews() {
        seatsLayout = findViewById(R.id.layout_seat_selection_seats)
        titleView = findViewById(R.id.tv_seat_selection_title)
        priceView = findViewById(R.id.tv_seat_selection_price)
    }

    override fun setSeats(seats: Set<Seat>) {
        val seats: Map<Row, List<Seat>> = seats.groupBy { seat: Seat -> seat.row }
        seats.forEach { row: Row, seats: List<Seat> ->
            val seatsRow = TableRow(this)
            seats.forEach { seat: Seat ->
                val seatView: TextView = seatView(seat)
                seatsRow.addView(seatView)
            }
            seatsLayout.addView(seatsRow)
        }
    }

    private fun seatView(seat: Seat): TextView =
        TextView(this).apply {
            text = "${seat.row.prettyString}${seat.column.value}"
            @ColorRes val colorResId: Int =
                when (seat.grade) {
                    SeatGrade.S -> R.color.seat_grade_s
                    SeatGrade.A -> R.color.seat_grade_a
                    SeatGrade.B -> R.color.seat_grade_b
                }
            val textColor = getColor(colorResId)
            setTextColor(textColor)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f)
            setPadding(27.dp)
            gravity = Gravity.CENTER
            background =
                AppCompatResources.getDrawable(
                    this@SeatSelectionActivity,
                    R.drawable.background_seat,
                )
            isSelected = seat in selectedSeats
            setOnClickListener { view: View ->
                view.isSelected = !view.isSelected
                selectedSeats =
                    if (view.isSelected) {
                        selectedSeats + seat
                    } else {
                        selectedSeats - seat
                    }
            }
        }

    private val Row.prettyString: String get() = ('A' + this.value - 1).toString()

    private val Int.dp: Int get() = (this * resources.displayMetrics.density).toInt()

    override fun setTitle(title: String) {
        titleView.text = title
    }

    override fun setPrice(price: Int) {
        priceView.text = getString(R.string.seat_selection_price, price)
    }

    companion object {
        private const val KEY_SEATS = "KEY_SEATS"

        private const val CAUSE_TICKET = "ticket"

        private const val EXTRA_TICKET = "woowacourse.movie.EXTRA_TICKET"

        fun newIntent(
            context: Context,
            title: String,
            count: Int,
            showtime: LocalDateTime,
        ): Intent =
            run {
                val ticket = Ticket(title, count, showtime)
                Intent(context, SeatSelectionActivity::class.java)
                    .putExtra(EXTRA_TICKET, ticket)
            }
    }
}
