package woowacourse.movie.presentation.activities.ticketing

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivitySeatPickerBinding
import woowacourse.movie.databinding.ItemMovieSeatBinding
import woowacourse.movie.domain.model.discount.policy.MovieDayDiscountPolicy
import woowacourse.movie.domain.model.discount.policy.MovieTimeDiscountPolicy
import woowacourse.movie.domain.model.seat.DomainPickedSeats
import woowacourse.movie.domain.model.seat.DomainSeat
import woowacourse.movie.presentation.activities.movielist.MovieListActivity
import woowacourse.movie.presentation.extensions.getParcelableCompat
import woowacourse.movie.presentation.extensions.showBackButton
import woowacourse.movie.presentation.extensions.showToast
import woowacourse.movie.presentation.mapper.toDomain
import woowacourse.movie.presentation.mapper.toPresentation
import woowacourse.movie.presentation.model.Movie
import woowacourse.movie.presentation.model.MovieDate
import woowacourse.movie.presentation.model.MovieTime
import woowacourse.movie.presentation.model.PickedSeats
import woowacourse.movie.presentation.model.Seat
import woowacourse.movie.presentation.model.SeatColumn
import woowacourse.movie.presentation.model.SeatRow
import woowacourse.movie.presentation.model.Ticket

class SeatPickerActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySeatPickerBinding

    private var pickedSeats = DomainPickedSeats()
    private val seatRowSize: Int = 5
    private val seatColSize: Int = 4

    private val movieDate by lazy {
        intent.getParcelableCompat<MovieDate>(TicketingActivity.MOVIE_DATE_KEY)!!.toDomain()
    }
    private val movieTime by lazy {
        intent.getParcelableCompat<MovieTime>(TicketingActivity.MOVIE_TIME_KEY)!!.toDomain()
    }
    private val ticket by lazy { intent.getParcelableCompat<Ticket>(TicketingActivity.TICKET_KEY)!! }
    private val movie by lazy { intent.getParcelableCompat<Movie>(MovieListActivity.MOVIE_KEY)!! }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreState(savedInstanceState)
        binding = ActivitySeatPickerBinding.inflate(layoutInflater).also { setContentView(it.root) }
        initView()
    }

    private fun restoreState(instanceState: Bundle?) {
        instanceState?.getParcelableCompat<PickedSeats>(PICKED_SEATS_KEY)?.let { restoredSeats ->
            pickedSeats = restoredSeats.toDomain()
        }
    }

    private fun initView() {
        showBackButton()
        showMovieTitle()
        updateDoneBtnEnabled(!canPick())
        updateTotalPriceView(calculateTotalPrice())
        initSeatTable(seatRowSize, seatColSize)
    }

    private fun showMovieTitle() {
        binding.movieTitleTv.text = movie.title
    }

    private fun updateDoneBtnEnabled(isEnabled: Boolean) {
        binding.doneBtn.isEnabled = isEnabled
    }

    private fun canPick(): Boolean =
        pickedSeats.canPick(ticket.toDomain())

    private fun updateTotalPriceView(amount: Int) {
        binding.totalPriceTv.text = getString(R.string.movie_pay_price, amount)
    }

    private fun calculateTotalPrice(): Int = pickedSeats.calculateTotalPrice(
        MovieDayDiscountPolicy(movieDate),
        MovieTimeDiscountPolicy(movieTime),
    ).toPresentation().amount

    private fun initSeatTable(rowSize: Int, colSize: Int) {
        SeatRow.make(rowSize).forEach { seatRow ->
            binding.seatTable.addView(makeSeatTableRow(seatRow, colSize))
        }
    }

    private fun makeSeatTableRow(row: SeatRow, colSize: Int): TableRow = TableRow(this).apply {
        SeatColumn.make(colSize).forEach { col ->
            addView(makeSeatView(row, col))
        }
    }

    private fun makeSeatView(row: SeatRow, col: SeatColumn): View {
        val seat = Seat(row, col).toDomain()
        return seat.toPresentation().makeView(this, isPicked(seat)) {
            when {
                isPicked(seat) -> unpick(seat)
                canPick() -> pick(seat)
                else -> showToast(getString(R.string.exceed_pickable_seat))
            }
        }
    }

    private fun ItemMovieSeatBinding.unpick(seat: DomainSeat) {
        seatNumberTv.isSelected = false
        pickedSeats = pickedSeats.remove(seat)
        updateTotalPriceView(calculateTotalPrice())
        updateDoneBtnEnabled(!canPick())
    }

    private fun ItemMovieSeatBinding.pick(seat: DomainSeat) {
        seatNumberTv.isSelected = true
        pickedSeats = pickedSeats.add(seat)
        updateTotalPriceView(calculateTotalPrice())
        updateDoneBtnEnabled(!canPick())
    }

    private fun isPicked(seat: DomainSeat) = pickedSeats.isPicked(seat)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(PICKED_SEATS_KEY, pickedSeats.toPresentation())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val PICKED_SEATS_KEY = "picked_seats"
    }
}
