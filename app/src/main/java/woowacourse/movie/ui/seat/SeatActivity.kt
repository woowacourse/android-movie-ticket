package woowacourse.movie.ui.seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import woowacourse.movie.R
import woowacourse.movie.Reservation
import woowacourse.movie.SelectResult
import woowacourse.movie.SelectedSeat
import woowacourse.movie.model.BookedMovie
import woowacourse.movie.model.Mapper.toDomainModel
import woowacourse.movie.model.Mapper.toUiModel
import woowacourse.movie.model.SelectedSeatUiModel
import woowacourse.movie.movie.Movie
import woowacourse.movie.movie.MovieRepository
import woowacourse.movie.theater.Theater
import woowacourse.movie.theater.TheaterRepository
import woowacourse.movie.ticket.Seat
import woowacourse.movie.ticket.Ticket
import woowacourse.movie.ui.completed.CompletedActivity
import woowacourse.movie.util.getParcelable
import woowacourse.movie.util.getParcelableBundle
import woowacourse.movie.util.shortToast

class SeatActivity : AppCompatActivity() {
    private val textPayment by lazy { findViewById<TextView>(R.id.textSeatPayment) }
    private val buttonConfirm by lazy { findViewById<TextView>(R.id.buttonSeatConfirm) }
    private val table by lazy { findViewById<SeatTableLayout>(R.id.seatTableLayout) }

    private lateinit var bookedMovie: BookedMovie
    private lateinit var movie: Movie
    private lateinit var theater: Theater
    private lateinit var selectedSeat: SelectedSeat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat)
        getData()
        initSeatTable()
        initView()
        clickConfirmButton()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable("SELECTED_SEAT", selectedSeat.toUiModel())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val selectedSeatUiModel =
            savedInstanceState.getParcelableBundle("SELECTED_SEAT", SelectedSeatUiModel::class.java)
        restoreSelectedSeat(selectedSeatUiModel)
    }

    private fun restoreSelectedSeat(selectedSeatUiModel: SelectedSeatUiModel) {
        selectedSeatUiModel.selectedSeat.forEach {
            setSeatState(it.toDomainModel())
        }
    }

    private fun getData() {
        bookedMovie = intent.getParcelable(BOOKED_MOVIE, BookedMovie::class.java) ?: return finish()
        movie = MovieRepository.getMovie(bookedMovie.movieId)
        theater = TheaterRepository.getTheater(bookedMovie.theaterId)
        selectedSeat = SelectedSeat(bookedMovie.ticketCount)
    }

    private fun initSeatTable() {
        table.setTable(theater.rowSize, theater.columnSize)
        table.setColorRange(
            mapOf(
                theater.sRankRange to R.color.green_400,
                theater.aRankRange to R.color.blue_500,
                theater.bRankRange to R.color.purple_400,
            ),
        )
        table.setClickListener { clickedPosition ->
            val seat = theater.selectSeat(clickedPosition)
            setSeatState(seat)
        }
    }

    private fun setSeatState(seat: Seat) {
        val view = table[seat.position.row][seat.position.column]
        val result = selectedSeat.clickSeat(seat)
        when (result) {
            SelectResult.Select.Full -> shortToast("더 이상 좌석을 선택할 수 없습니다.")
            SelectResult.Select.Success -> view.isSelected = !view.isSelected
            SelectResult.Unselect -> view.isSelected = !view.isSelected
        }
        setConfirmButtonEnable(selectedSeat.isSeatFull)
        setPayment(selectedSeat.seats)
    }

    private fun setConfirmButtonEnable(isSeatFull: Boolean) {
        buttonConfirm.isEnabled = isSeatFull

        if (buttonConfirm.isEnabled) {
            buttonConfirm.setBackgroundResource(R.color.purple_700)
            return
        }
        buttonConfirm.setBackgroundResource(R.color.gray_400)
    }

    private fun clickConfirmButton() {
        buttonConfirm.setOnClickListener {
            showDialog()
        }
    }

    private fun completeBooking() {
        val tickets: List<Ticket> =
            selectedSeat.seats.map { movie.reserve(bookedMovie.bookedDateTime, it) }
        val reservation = Reservation(tickets.toSet()).toUiModel()
        startActivity(CompletedActivity.getIntent(this, reservation))
        finish()
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle("예매 확인")
            .setMessage("정말 예매하시겠습니까?")
            .setPositiveButton("예") { _, _ -> completeBooking() }
            .setNegativeButton("아니요") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun setPayment(seats: Set<Seat>) {
        val tickets: List<Ticket> = seats.map { movie.reserve(bookedMovie.bookedDateTime, it) }
        textPayment.text = tickets.sumOf { it.price }.toString() + "원"
    }

    private fun initView() {
        findViewById<TextView>(R.id.textSeatMovieTitle).text = movie.title
    }

    companion object {
        private const val BOOKED_MOVIE = "BOOKED_MOVIE"

        fun getIntent(context: Context, bookedMovie: BookedMovie): Intent {
            return Intent(context, SeatActivity::class.java).apply {
                putExtra(BOOKED_MOVIE, bookedMovie)
            }
        }
    }
}
