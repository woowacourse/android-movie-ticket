package woowacourse.movie.ui.seatselectionactivity

import android.os.Bundle
import android.util.Log
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import movie.domain.Count
import woowacourse.movie.R
import woowacourse.movie.model.CountState
import woowacourse.movie.model.MovieDataState
import woowacourse.movie.model.mapper.toDomain
import woowacourse.movie.util.customGetParcelableExtra
import woowacourse.movie.util.setOnSingleClickListener

class SeatSelectionActivity : AppCompatActivity() {

    private lateinit var movieDataState: MovieDataState
    private var price = 0
    private lateinit var ticketCount: Count
    private lateinit var seatView: List<TextView>
    private var selectedSeatCount: Count = Count(0)

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
            intent.customGetParcelableExtra<MovieDataState>(MOVIE_DATA) ?: return notFoundData(MOVIE_DATA)
    }

    private fun notFoundData(key: String) {
        Log.d(SEAT_SELECTION, DATA_NOT_FOUNT_ERROR_MSG.format(key))
        finish()
    }

    private fun initMovieInformation() {
        val tvSeatSelectionTitle = findViewById<TextView>(R.id.tv_seat_selection_title)
        val tvSeatSelectionPrice = findViewById<TextView>(R.id.tv_seat_selection_price)

        tvSeatSelectionTitle.text = movieDataState.title
        tvSeatSelectionPrice.text = "%s원".format(price)
    }

    private fun initTicketCount() {
        val tmpCount = intent.customGetParcelableExtra<CountState>(TICKET_COUNT) ?: return notFoundData(TICKET_COUNT)
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
                when (view.isSelected) {
                    true -> cancelSeat(view)
                    false -> selectSeat(view)
                }
            }
        }
    }

    private fun selectSeat(seatView: TextView) {
        if (selectedSeatCount == ticketCount) return
        selectedSeatCount++
        seatView.isSelected = true
    }

    private fun cancelSeat(seatView: TextView) {
        if (selectedSeatCount == Count(0)) return
        selectedSeatCount--
        seatView.isSelected = false
    }

    private fun initReservationButtonClickListener() {
        TODO("Not yet implemented")
    }

    companion object {
        private const val MOVIE_DATA = "movieData"
        private const val TICKET_COUNT = "ticketCount"
        private const val SEAT_SELECTION = "SeatSelection"
        private const val DATA_NOT_FOUNT_ERROR_MSG = "%s를 찾을 수 없습니다."
    }
}
