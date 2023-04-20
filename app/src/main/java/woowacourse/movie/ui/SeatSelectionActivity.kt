package woowacourse.movie.ui

import android.os.Bundle
import android.util.Log
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import movie.domain.Count
import movie.domain.price.DiscountPolicy
import movie.domain.price.EarlyMorningLateNightDiscount
import movie.domain.price.MovieDayDiscount
import movie.domain.price.PricePolicyCalculator
import movie.domain.seat.Seat
import woowacourse.movie.R
import woowacourse.movie.model.CountState
import woowacourse.movie.model.MovieDataState
import woowacourse.movie.model.ScreeningDateTimeState
import woowacourse.movie.model.mapper.toDomain
import woowacourse.movie.util.customGetParcelableExtra
import woowacourse.movie.util.setOnSingleClickListener
import kotlin.properties.Delegates

class SeatSelectionActivity : AppCompatActivity() {

    private lateinit var movieDataState: MovieDataState
    private lateinit var ticketCount: Count
    private lateinit var seatView: List<TextView>
    private var selectedSeatCount: Count = Count(0)
    private lateinit var selectedScreeningDateTimeState: ScreeningDateTimeState
    private val selectedSeats: MutableList<Seat> = mutableListOf()
    private lateinit var tvSeatSelectionPrice: TextView

    private var price: Int by Delegates.observable(0) { _, _, new ->
        tvSeatSelectionPrice.text = "%s원".format(new)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        initMovieData()
        initMovieInformation()
        initTicketCount()
        initSeatSelectionView()
        initSeatSelectionClickListener()
        // initReservationButtonClickListener()
    }

    private fun initMovieData() {
        movieDataState =
            intent.customGetParcelableExtra<MovieDataState>(MOVIE_DATA) ?: return notFoundData(
                MOVIE_DATA
            )
        selectedScreeningDateTimeState =
            intent.customGetParcelableExtra(SELECTED_SCREENING_DATE_TIME)
                ?: return notFoundData(SELECTED_SCREENING_DATE_TIME)
    }

    private fun notFoundData(key: String) {
        Log.d(SEAT_SELECTION, DATA_NOT_FOUNT_ERROR_MSG.format(key))
        finish()
    }

    private fun initMovieInformation() {
        val tvSeatSelectionTitle = findViewById<TextView>(R.id.tv_seat_selection_title)
        tvSeatSelectionPrice = findViewById<TextView>(R.id.tv_seat_selection_price)

        tvSeatSelectionTitle.text = movieDataState.title
    }

    private fun calculatePrice(): Int {
        val discountPolicies = mutableListOf<DiscountPolicy>()
        if (selectedScreeningDateTimeState.toDomain().checkMovieDay()) {
            discountPolicies.add(MovieDayDiscount())
        }
        if (selectedScreeningDateTimeState.toDomain().checkEarlyMorningLateNight()) {
            discountPolicies.add(EarlyMorningLateNightDiscount())
        }
        return PricePolicyCalculator(discountPolicies).totalPriceCalculate(selectedSeats)
    }

    private fun initTicketCount() {
        val tmpCount = intent.customGetParcelableExtra<CountState>(TICKET_COUNT) ?: return notFoundData(
            TICKET_COUNT
        )
        ticketCount = tmpCount.toDomain()
    }

    private fun initSeatSelectionView() {
        seatView = findViewById<TableLayout>(R.id.lo_seat)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .toList()
    }

    private fun initSeatSelectionClickListener() {
        seatView.forEachIndexed { index, view ->
            view.setOnSingleClickListener {
                Log.d("링링", index.toString())
                when (view.isSelected) {
                    true -> cancelSeat(view, index)
                    false -> selectSeat(view, index)
                }
                price = calculatePrice()
            }
        }
    }

    private fun selectSeat(seatView: TextView, index: Int) {
        if (selectedSeatCount == ticketCount) return
        selectedSeatCount++
        seatView.isSelected = true
        selectedSeats.add(Seat(index))
    }

    private fun cancelSeat(seatView: TextView, index: Int) {
        if (selectedSeatCount == Count(0)) return
        selectedSeatCount--
        seatView.isSelected = false
        selectedSeats.remove(Seat(index))
    }

    private fun initReservationButtonClickListener() {
        TODO("Not yet implemented")
    }

    companion object {
        private const val MOVIE_DATA = "movieData"
        private const val TICKET_COUNT = "ticketCount"
        private const val SEAT_SELECTION = "SeatSelection"
        private const val SELECTED_SCREENING_DATE_TIME = "selectedScreeningDateTime"
        private const val DATA_NOT_FOUNT_ERROR_MSG = "%s를 찾을 수 없습니다."
    }
}
