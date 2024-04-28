package woowacourse.movie.presentation.ui.seat

import android.app.AlertDialog
import android.content.Intent
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.data.repository.MovieTicketRepositoryImpl
import woowacourse.movie.data.repository.SeatRepositoryImpl
import woowacourse.movie.domain.model.SeatGrade
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.ui.reservation.ReservationResultActivity
import woowacourse.movie.presentation.uimodel.SeatsUIModel

class SeatActivity : BaseActivity(), SeatContract.View {
    private lateinit var presenter: SeatPresenter
    private lateinit var seatBoard: TableLayout
    private lateinit var title: TextView
    private lateinit var totalPrice: TextView
    private lateinit var confirmButton: Button

    override fun getLayoutResId(): Int = R.layout.activity_seat

    override fun onCreateSetup() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val movieTicketId = intent.getIntExtra(EXTRA_MOVIE_TICKET_ID, -1)

        seatBoard = findViewById(R.id.seat_board_layout)
        title = findViewById(R.id.title)
        totalPrice = findViewById(R.id.total_price)
        confirmButton = findViewById(R.id.confirm_button)

        presenter = SeatPresenter(this, MovieTicketRepositoryImpl, SeatRepositoryImpl, movieTicketId)
    }

    override fun initClickListener() {
        confirmButton.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.reservation_confirm_title))
        builder.setMessage(getString(R.string.reservation_confirm_message))
        builder.setCancelable(false)

        builder.setPositiveButton(getString(R.string.reservation_finish_text)) { _, _ ->
            presenter.requestReservationResult()
        }

        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    override fun showMovieTitle(movieTitle: String) {
        title.text = movieTitle
    }

    override fun showSeats(seatsUIModel: SeatsUIModel) {
        val seatsTable =
            seatBoard.children.filterIsInstance<TableRow>().flatMap { it.children }
                .filterIsInstance<Button>().toList()
        seatsTable.forEachIndexed { index, button ->
            val seatUIModel = seatsUIModel.seatUIModels[index]
            button.text = seatUIModel.seat.toString()
            button.setTextColor(loadSeatColor(seatUIModel.seat.seatGrade))
            button.isSelected = seatUIModel.isSelected
            button.setOnClickListener {
                presenter.onSeatClicked(index)
            }
        }
    }

    override fun updateConfirmButton(enabled: Boolean) {
        confirmButton.isEnabled = enabled
        confirmButton.isSelected = enabled
    }

    override fun moveToReservationResult(movieTicketId: Int) {
        val intent = Intent(this, ReservationResultActivity::class.java)
        intent.putExtra(EXTRA_MOVIE_TICKET_ID, movieTicketId)
        startActivity(intent)
    }

    override fun showTotalPrice(total: Int) {
        totalPrice.text = getString(R.string.total_price_format, total)
    }

    override fun showMessage(message: String) {
        showSnackBar(message)
    }

    private fun loadSeatColor(grade: SeatGrade): Int {
        return when (grade) {
            SeatGrade.B -> getColor(R.color.purple_500)
            SeatGrade.A -> getColor(R.color.teal_700)
            SeatGrade.S -> getColor(R.color.blue_500)
        }
    }

    companion object {
        const val EXTRA_MOVIE_TICKET_ID = "movieTicketId"
    }
}
