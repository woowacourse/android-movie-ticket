package woowacourse.movie.feature.seat

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.feature.completed.ReservationCompletedActivity
import woowacourse.movie.feature.main.ui.ScreeningItem
import woowacourse.movie.feature.reservation.ui.SeatBoardView
import woowacourse.movie.feature.reservation.ui.SeatModel
import woowacourse.movie.feature.util.DECIMAL_FORMAT
import java.text.DecimalFormat

class SeatSelectionActivity : AppCompatActivity(), SeatSelectionContract.View {
    private val movieId by lazy { intent.getLongExtra(SCREENING_ID, -1L) }
    private val datePosition by lazy { intent.getIntExtra(DATE_POSITION, -1) }
    private val timePosition by lazy { intent.getIntExtra(TIME_POSITION, -1) }
    private val quantity by lazy { intent.getIntExtra(QUANTITY, -1) }
    private val presenter by lazy {
        SeatSelectionPresenter(
            this,
            movieId,
            datePosition,
            timePosition,
            quantity,
        )
    }
    private val movieTitleTv by lazy { findViewById<TextView>(R.id.tv_screen_movie_title) }
    private val priceTv by lazy { findViewById<TextView>(R.id.tv_seat_price) }
    private val seatBoardView: SeatBoardView by lazy { collectSeatViews().let(::SeatBoardView) }
    private val reservationConfirmTv by lazy { findViewById<TextView>(R.id.btn_reservation_confirm) }

    private fun collectSeatViews(): List<TextView> =
        findViewById<TableLayout>(R.id.table_seat)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .toList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.fetchData()
    }

    override fun initialize(
        screeningModels: ScreeningItem.ScreeningModel,
        seatModels: List<SeatModel>,
    ) {
        updatePriceTextView(0)
        movieTitleTv.text = screeningModels.title
        initializeSeatView(seatModels)
        reservationConfirmTv.setOnClickListener {
            presenter.proceedReservation()
        }
    }

    override fun updatePriceTextView(price: Long) {
        val formattedPrice = DecimalFormat(DECIMAL_FORMAT).format(price)
        priceTv.text = getString(R.string.seat_price, formattedPrice)
    }

    private fun initializeSeatView(seatModels: List<SeatModel>) {
        seatBoardView.initText(seatModels)
        seatBoardView.setupClickListener { index, _ ->
            presenter.proceedSeatSelection(index)
        }
    }

    override fun checkSeatSelected(index: Int) {
        seatBoardView.updateSingleSeat(index)
    }

    override fun noticeReservationImpossible(quantity: Int) {
        Toast.makeText(this, "${quantity}개의 좌석을 선택해 주세요.", Toast.LENGTH_SHORT)
            .show()
    }

    override fun confirmReservation() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("예매 확인")
            .setMessage("정말 예매하시겠습니까?")
            .setNegativeButton(
                "취소",
                DialogInterface.OnClickListener { _, _ ->
                },
            )
            .setPositiveButton(
                "예매 완료",
                DialogInterface.OnClickListener { _, _ ->
                    presenter.saveTicket()
                },
            )
        builder.show()
    }

    override fun navigateToReservationCompleted(reservationId: Long) {
        startActivity(ReservationCompletedActivity.getIntent(this, reservationId))
    }

    companion object {
        const val SCREENING_ID = "screening_id"
        const val TIME_POSITION = "time_position"
        const val DATE_POSITION = "date_position"
        const val QUANTITY = "quantity"

        fun getIntent(
            context: Context,
            movieId: Long,
            datePosition: Int,
            timePosition: Int,
            quantity: Int,
        ): Intent {
            return Intent(context, SeatSelectionActivity::class.java).apply {
                putExtra(SCREENING_ID, movieId)
                putExtra(DATE_POSITION, datePosition)
                putExtra(TIME_POSITION, timePosition)
                putExtra(QUANTITY, quantity)
            }
        }
    }
}
