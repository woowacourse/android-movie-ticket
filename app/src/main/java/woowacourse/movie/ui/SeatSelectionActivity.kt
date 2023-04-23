package woowacourse.movie.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import movie.domain.Count
import woowacourse.movie.R
import woowacourse.movie.model.CountState
import woowacourse.movie.model.MovieDataState
import woowacourse.movie.model.ScreeningDateTimeState
import woowacourse.movie.model.SeatState
import woowacourse.movie.model.TicketState
import woowacourse.movie.model.mapper.toDomain
import woowacourse.movie.util.customGetParcelableExtra
import woowacourse.movie.util.setOnSingleClickListener
import kotlin.properties.Delegates

class SeatSelectionActivity : AppCompatActivity() {

    private lateinit var ticketState: TicketState
    private lateinit var seatView: List<TextView>
    private var selectedSeatCount: Count = Count(0)
    private lateinit var tvSeatSelectionPrice: TextView
    private lateinit var btnSeatSelection: Button

    private var price: Int by Delegates.observable(0) { _, _, new ->
        tvSeatSelectionPrice.text = "%s원".format(new)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        initView()
        initMovieData()
        initMovieInformation()
        initSeatSelectionView()
        initSeatSelectionClickListener()
        initReservationButtonClickListener()
    }

    private fun initView() {
        tvSeatSelectionPrice = findViewById<TextView>(R.id.tv_seat_selection_price)
        btnSeatSelection = findViewById<Button>(R.id.btn_seat_selection)
    }

    private fun initMovieData() {
        val countState = intent.customGetParcelableExtra<CountState>(TICKET_COUNT)
            ?: return notFoundData(TICKET_COUNT)
        val movieDataState =
            intent.customGetParcelableExtra<MovieDataState>(MOVIE_DATA)
                ?: return notFoundData(MOVIE_DATA)
        val screeningDateTimeState =
            intent.customGetParcelableExtra<ScreeningDateTimeState>(SELECTED_SCREENING_DATE_TIME)
                ?: return notFoundData(SELECTED_SCREENING_DATE_TIME)
        ticketState = TicketState(countState, movieDataState, screeningDateTimeState)
    }

    private fun notFoundData(key: String) {
        Log.d(SEAT_SELECTION, DATA_NOT_FOUNT_ERROR_MSG.format(key))
        finish()
    }

    private fun initMovieInformation() {
        val tvSeatSelectionTitle = findViewById<TextView>(R.id.tv_seat_selection_title)

        tvSeatSelectionTitle.text = ticketState.movieData.title
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
                    true -> cancelSeat(view, index)
                    false -> selectSeat(view, index)
                }
                price = ticketState.toDomain().price
            }
        }
    }

    private fun selectSeat(seatView: TextView, index: Int) {
        if (selectedSeatCount == ticketState.count.toDomain()) {
            return
        }
        selectedSeatCount++
        seatView.isSelected = true
        ticketState.seatSelection.add(SeatState(index))
        if (selectedSeatCount == ticketState.count.toDomain()) {
            btnSeatSelection.isEnabled = true
        }
    }

    private fun cancelSeat(seatView: TextView, index: Int) {
        btnSeatSelection.isEnabled = false
        if (selectedSeatCount == Count(0)) return
        selectedSeatCount--
        seatView.isSelected = false
        ticketState.seatSelection.remove(SeatState(index))
    }

    private fun initReservationButtonClickListener() {
        btnSeatSelection.setOnSingleClickListener {
            AlertDialog.Builder(this)
                .setTitle("예매 확인")
                .setMessage("정말 예매하시겠습니까?")
                .setPositiveButton("예매 완료") { _, _ ->
                    ticketState.price = price
                    val intent = Intent(this, MovieBookingCheckActivity::class.java)
                        .putExtra(TICKET, ticketState)
                    startActivity(intent)
                }
                .setNegativeButton("취소") { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }
    }

    companion object {
        private const val TICKET = "ticket"
        private const val MOVIE_DATA = "movieData"
        private const val TICKET_COUNT = "ticketCount"
        private const val SEAT_SELECTION = "SeatSelection"
        private const val SELECTED_SCREENING_DATE_TIME = "selectedScreeningDateTime"
        private const val DATA_NOT_FOUNT_ERROR_MSG = "%s를 찾을 수 없습니다."
    }
}
