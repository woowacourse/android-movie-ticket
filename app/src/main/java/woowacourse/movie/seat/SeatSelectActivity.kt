package woowacourse.movie.seat

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.model.ReservationSchedule
import woowacourse.movie.model.Seats
import woowacourse.movie.reservation.ReservationFinishedActivity

class SeatSelectActivity : AppCompatActivity(), SeatSelectContract.View {
    private val seatLayout: GridLayout by lazy { findViewById(R.id.grid_layout_seat_select) }
    private val movieTitle: TextView by lazy { findViewById(R.id.text_view_seat_select_movie_title) }
    private val totalPrice: TextView by lazy { findViewById(R.id.text_view_seat_select_total_price) }
    private val reservationButton: Button by lazy { findViewById(R.id.button_seat_select_confirm) }

    private lateinit var presenter: SeatSelectPresenter

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_select)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra(MOVIE_ID, DEFAULT_MOVIE_ID)
        val seats =
            intent.getParcelableExtra(TICKET, Seats::class.java)
                ?: throw IllegalArgumentException("빈 티켓이 넘어 왔습니다.")
        val reservationSchedule =
            intent.getParcelableExtra(RESERVATION_SCHEDULE, ReservationSchedule::class.java)
                ?: throw IllegalArgumentException("빈 예약 스케줄이 넘어 왔습니다.")

        initPresenter(savedInstanceState, movieId, reservationSchedule, seats)
        initSeats()

        reservationButton.setOnClickListener {
            presenter.confirm()
        }
    }

    private fun initPresenter(
        savedInstanceState: Bundle?,
        movieId: Int,
        reservationSchedule: ReservationSchedule,
        seats: Seats,
    ) {
        presenter =
            if (savedInstanceState == null) {
                SeatSelectPresenter(this, movieId, reservationSchedule, seats)
            } else {
                val loadedSeats =
                    savedInstanceState.getStringArray(SEATS)?.toMutableList()
                        ?: throw IllegalArgumentException("빈 자리가 넘어 왔습니다.")
                SeatSelectPresenter(
                    this,
                    movieId,
                    reservationSchedule,
                    Seats(value = loadedSeats),
                )
            }
    }

    private fun initSeats() {
        seatLayout.children
            .filterIsInstance<TextView>()
            .forEach { textView ->
                textView.setOnClickListener {
                    if (it.isSelected) {
                        presenter.unselectSeat(textView.text.toString()) { color ->
                            it.setBackgroundColor(getColor(color))
                        }
                    } else {
                        presenter.selectSeat(textView.text.toString()) { color ->
                            it.setBackgroundColor(getColor(color))
                        }
                    }
                    it.isSelected = !it.isSelected
                }
            }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putParcelable(SEATS, presenter.seats)
    }

    override fun showReservationInfo(
        movieTitle: String,
        totalPrice: Int,
    ) {
        this.movieTitle.text = movieTitle
        this.totalPrice.text = getString(R.string.seat_select_total_price, totalPrice)
    }

    override fun showTotalPrice(totalPrice: Int) {
        this.totalPrice.text = getString(R.string.seat_select_total_price, totalPrice)
    }

    override fun showReservationCheck(isAvailable: Boolean) {
        if (isAvailable) {
            reservationButton.isEnabled = true
            reservationButton.setBackgroundColor(getColor(R.color.blue))
        } else {
            reservationButton.isEnabled = false
            reservationButton.setBackgroundColor(getColor(R.color.grey))
        }
    }

    override fun changeSeatColor(
        isSelected: Boolean,
        onColor: (Int) -> Unit,
    ) {
        if (isSelected) {
            onColor(R.color.white)
        } else {
            onColor(R.color.yellow)
        }
    }

    override fun showConfirmDialog() {
        AlertDialog.Builder(this)
            .setTitle("예매 확인")
            .setMessage("정말 예매하시겠습니까?")
            .setPositiveButton("예매 완료") { _, _ ->
                presenter.loadReservationInformation()
            }
            .setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    override fun moveToReservationFinished(
        movieId: Int,
        ticket: Seats,
        seats: String,
        totalPrice: Int,
        reservationSchedule: ReservationSchedule,
    ) {
        startActivity(
            ReservationFinishedActivity.getIntent(
                this,
                movieId,
                ticket,
                seats,
                totalPrice,
                reservationSchedule,
            ),
        )
    }

    companion object {
        private const val MOVIE_ID = "movieId"
        private const val TICKET = "ticket"
        private const val SEATS = "seats"
        private const val RESERVATION_SCHEDULE = "reservationSchedule"
        private const val DEFAULT_MOVIE_ID = 0

        fun getIntent(
            context: Context,
            movieId: String,
            seats: Seats,
            reservationSchedule: ReservationSchedule,
        ): Intent {
            return Intent(context, SeatSelectActivity::class.java)
                .putExtra(MOVIE_ID, movieId)
                .putExtra(TICKET, seats)
                .putExtra(RESERVATION_SCHEDULE, reservationSchedule)
        }
    }
}
