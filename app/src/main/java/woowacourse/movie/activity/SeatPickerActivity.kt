package woowacourse.movie.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import com.woowacourse.domain.seat.Seat
import com.woowacourse.domain.seat.SeatColumn
import com.woowacourse.domain.seat.SeatGroup
import com.woowacourse.domain.seat.SeatRow
import com.woowacourse.domain.ticket.Ticket
import com.woowacourse.domain.ticket.TicketBundle
import woowacourse.movie.BundleKeys
import woowacourse.movie.R
import woowacourse.movie.getSerializableCompat
import woowacourse.movie.mapper.toDomain
import woowacourse.movie.mapper.toPresentation
import woowacourse.movie.model.SeatGroupModel
import woowacourse.movie.movie.MovieBookingInfo
import woowacourse.movie.movie.MovieBookingSeatInfo

class SeatPickerActivity : BackButtonActivity() {
    private val seatTableLayout: TableLayout by lazy { findViewById(R.id.tl_seats) }
    private val pickDoneButton: Button by lazy { findViewById(R.id.bt_seat_picker_done) }
    private val seatPickerTicketPrice: TextView by lazy { findViewById(R.id.tv_seat_picker_ticket_price) }
    private val movieTitle: TextView by lazy { findViewById(R.id.tv_seat_picker_movie) }
    private var seatGroup = SeatGroup()
    private lateinit var ticketBundle: TicketBundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_picker)

        val movieBookingInfo = getMovieBookingInfo()

        initSeatName()
        initSeatNameColor()
        initMovieTitle(movieBookingInfo)
        ticketBundle = TicketBundle(count = movieBookingInfo.ticketCount)
        reloadData(savedInstanceState)

        setPickDoneButtonClickListener()
        setSeatsOnClickListener(movieBookingInfo)
    }

    private fun getMovieBookingInfo(): MovieBookingInfo {
        return intent.getSerializableCompat(BundleKeys.MOVIE_BOOKING_INFO_KEY)
            ?: MovieBookingInfo.dummyData
    }

    private fun initSeatName() {
        getRowSeats().forEachIndexed { index, rowSeat ->
            rowSeat.children
                .filterIsInstance<TextView>()
                .forEachIndexed { rowIndex, seat ->
                    seat.text = formatSeatName(index, rowIndex)
                }
        }
    }

    private fun formatSeatName(index: Int, rowIndex: Int) =
        getString(R.string.seat_name).format(ALPHABET[index], rowIndex + 1)

    private fun initSeatNameColor() {
        getSeats().forEachIndexed { index, seat ->
            when (index) {
                in SEAT_B -> seat.setTextColor(getColor(R.color.seat_color_b))
                in SEAT_S -> seat.setTextColor(getColor(R.color.seat_color_s))
                in SEAT_A -> seat.setTextColor(getColor(R.color.seat_color_a))
                else -> throw IllegalArgumentException("잘못된 값: $index 분류될 수 없는 값입니다.")
            }
        }
    }

    private fun getRowSeats() =
        seatTableLayout.children
            .filterIsInstance<TableRow>()
            .toList()

    private fun getSeats() =
        seatTableLayout.children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .toList()

    private fun initMovieTitle(movieBookingInfo: MovieBookingInfo) {
        movieTitle.text = movieBookingInfo.movieInfo.title
    }

    private fun reloadData(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            movieTitle.text = savedInstanceState.getString(MOVIE_TITLE)
            seatPickerTicketPrice.text = savedInstanceState.getString(TICKET_PRICE)
            seatGroup =
                (savedInstanceState.getSerializable(PICKED_SEAT) as SeatGroupModel).toDomain()
            val seatNames = seatGroup.seats.map {
                formatSeatName(
                    it.row.value,
                    it.column.value
                )
            }
            setPickDoneButtonColor()

            Toast.makeText(this, "$seatNames", Toast.LENGTH_SHORT).show()
            getSeats().forEach {
                if (seatNames.contains(it.text)) it.setBackgroundColor(getColor(R.color.picked_seat_color))
            }
        }
    }

    private fun setPickDoneButtonClickListener() {
        pickDoneButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.alert_dialog_book_confirm))
                .setMessage(getString(R.string.alert_dialog_book_re_confirm))
                .setPositiveButton(
                    getString(R.string.alert_dialog_book_done)
                ) { _, _ ->
                    val intent = BookCompleteActivity.intent(this)
                    intent.putExtra(
                        BundleKeys.MOVIE_BOOKING_SEAT_INFO_KEY,
                        MovieBookingSeatInfo(
                            getMovieBookingInfo(),
                            seatGroup.sorted().seats.map {
                                formatSeatName(it.row.value, it.column.value)
                            },
                            seatPickerTicketPrice.text.toString()
                        )
                    )
                    startActivity(intent)
                }.setNegativeButton(
                    getString(R.string.alert_dialog_book_cancel)
                ) { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }
    }

    private fun setSeatsOnClickListener(movieBookingInfo: MovieBookingInfo) {
        getSeats().forEachIndexed { index, seat ->
            seat.setOnClickListener {
                val newSeat =
                    Seat(SeatRow(index / SEAT_ROW_INTERVAL), SeatColumn(index % SEAT_ROW_INTERVAL))
                if (seatGroup.seats.contains(newSeat)) {
                    progressRemoveSeat(newSeat, seat, movieBookingInfo)
                    return@setOnClickListener
                }
                if (seatGroup.canAdd(ticketBundle.count) && !seatGroup.seats.contains(newSeat)) {
                    progressAddSeat(newSeat, seat, movieBookingInfo)
                }
            }
        }
    }

    private fun progressAddSeat(
        newSeat: Seat,
        seat: TextView,
        movieBookingInfo: MovieBookingInfo,
    ) {
        seatGroup = seatGroup.add(newSeat)
        ticketBundle = ticketBundle.add(Ticket(newSeat.getSeatTier().price))
        seat.setBackgroundColor(getColor(R.color.picked_seat_color))
        setPickDoneButtonColor()
        seatPickerTicketPrice.text =
            getString(
                R.string.ticket_price_format,
                ticketBundle.calculateTotalPrice(
                    movieBookingInfo.date,
                    movieBookingInfo.time
                )
            )
    }

    private fun progressRemoveSeat(
        newSeat: Seat,
        seat: TextView,
        movieBookingInfo: MovieBookingInfo
    ) {
        seatGroup = seatGroup.remove(newSeat)
        ticketBundle = ticketBundle.remove(Ticket(newSeat.getSeatTier().price))
        seat.setBackgroundColor(getColor(R.color.unpicked_seat_color))
        setPickDoneButtonColor()
        seatPickerTicketPrice.text =
            getString(
                R.string.ticket_price_format,
                ticketBundle.calculateTotalPrice(
                    movieBookingInfo.date,
                    movieBookingInfo.time
                )
            )
    }

    private fun setPickDoneButtonColor() {
        pickDoneButton.isEnabled = !seatGroup.canAdd(ticketBundle.count)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(MOVIE_TITLE, movieTitle.text.toString())
        outState.putString(TICKET_PRICE, seatPickerTicketPrice.text.toString())
        outState.putSerializable(PICKED_SEAT, seatGroup.toPresentation())
    }

    companion object {
        private val ALPHABET = ('A'..'Z').toList()
        private val SEAT_B = 0..7
        private val SEAT_S = 8..15
        private val SEAT_A = 16..19
        private const val MOVIE_TITLE = "movieTitle"
        private const val TICKET_PRICE = "ticketPrice"
        private const val PICKED_SEAT = "prickedSeat"
        private const val SEAT_ROW_INTERVAL = 4
        fun intent(context: Context) = Intent(context, SeatPickerActivity::class.java)
    }
}
