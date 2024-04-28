package woowacourse.movie.presentation.reservation.seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import woowacourse.movie.R
import woowacourse.movie.common.ui.parcelable
import woowacourse.movie.common.ui.showToast
import woowacourse.movie.data.MovieRepositoryFactory
import woowacourse.movie.presentation.reservation.booking.model.SeatSelectionNavArgs
import woowacourse.movie.presentation.reservation.result.ReservationResultActivity
import woowacourse.movie.presentation.reservation.seat.model.SeatBoardUiModel
import woowacourse.movie.presentation.reservation.seat.model.SeatSelectionUiState
import woowacourse.movie.presentation.reservation.seat.model.SeatUiModel

class SeatSelectionActivity : AppCompatActivity(), SeatSelectionView {
    private lateinit var reservationDialog: AlertDialog
    private lateinit var seatBoardView: SeatBoardView
    private lateinit var tableLayout: TableLayout
    private lateinit var priceView: TextView
    private lateinit var movieTitleView: TextView
    private lateinit var presenter: SeatSelectionPresenter

    private lateinit var reservationButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        initView()
        initClickListener()
        intent.parcelable<SeatSelectionNavArgs>(KEY_SEAT_SELECTION)?.let { navArgs ->
            presenter =
                SeatSelectionPresenter(
                    repository = MovieRepositoryFactory.movieRepository(),
                    navArgs = navArgs,
                    view = this,
                ).apply { loadScreenSeats() }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_SEAT_UI_STATE, presenter.uiState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.parcelable<SeatSelectionUiState>(
            KEY_SEAT_UI_STATE,
        )?.let(presenter::restoreState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showMovieTitle(title: String) {
        movieTitleView.text = title
    }

    override fun showSeat(seat: SeatUiModel) {
        seatBoardView.updateSeat(seat)
    }

    override fun showSeatBoard(board: SeatBoardUiModel) {
        seatBoardView =
            SeatBoardView(
                tableLayout = tableLayout,
                rowCount = board.rowCount,
                columnCount = board.columnCount,
            ).apply {
                updateSeats(board.seats)
                setBoardClickListener { x, y -> presenter.selectSeat(x, y) }
            }
    }

    override fun showTotalPrice(price: Long) {
        priceView.text = getString(R.string.seat_total_price_format, price)
    }

    override fun navigateToReservationResult(reservationId: Long) {
        val intent =
            ReservationResultActivity.newIntent(this, reservationId).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        startActivity(intent)
    }

    override fun showSelectionError() {
        showToast("선택할 수 없는 좌석 입니다.")
    }

    override fun activateReservationButton() {
        reservationButton.isClickable = true
        reservationButton.setBackgroundColor(
            ContextCompat.getColor(this, R.color.purple_500),
        )
    }

    override fun deactivateReservationButton() {
        reservationButton.isClickable = false
        reservationButton.setBackgroundColor(
            ContextCompat.getColor(this, R.color.gray),
        )
    }

    private fun initView() {
        reservationDialog =
            AlertDialog.Builder(this)
                .setTitle("예매 확인")
                .setMessage("예매를 완료하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("확인") { _, _ ->
                    presenter.completeReservation()
                }
                .setNegativeButton("취소") { _, _ ->
                    reservationDialog.dismiss()
                }
                .create()
        tableLayout = findViewById(R.id.tl_seat_selection)

        priceView =
            findViewById<TextView?>(R.id.tv_seat_selection_price).apply {
                text = getString(R.string.seat_total_price_format, 0)
            }
        movieTitleView = findViewById<TextView?>(R.id.tv_seat_selection_title)
        reservationButton = findViewById<TextView>(R.id.tv_seat_selection_complete)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initClickListener() {
        reservationButton.setOnClickListener {
            reservationDialog.show()
        }
    }

    companion object {
        const val KEY_SEAT_SELECTION: String = "KEY_SEAT_SELECTION"
        private const val KEY_SEAT_UI_STATE: String = "KEY_SEAT_UI_STATE"

        @JvmStatic
        fun newIntent(
            context: Context,
            navArgs: SeatSelectionNavArgs,
        ): Intent =
            Intent(context, SeatSelectionActivity::class.java).apply {
                putExtra(KEY_SEAT_SELECTION, navArgs)
            }
    }
}
