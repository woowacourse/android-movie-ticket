package woowacourse.movie.presentation.ui.seat

import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.data.repository.MovieTicketRepositoryImpl
import woowacourse.movie.presentation.base.BaseActivity

class SeatActivity : BaseActivity(), SeatContract.View {
    private lateinit var presenter: SeatPresenter
    
    override fun getLayoutResId(): Int = R.layout.activity_seat
    
    private lateinit var seatBoard: TableLayout
    private lateinit var title: TextView
    private lateinit var totalPrice: TextView
    private lateinit var confirmButton: Button
    
    override fun onCreateSetup() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        
        val movieTicketId = intent.getIntExtra(EXTRA_MOVIE_TICKET_ID, -1)
        
        presenter = SeatPresenter(this, MovieTicketRepositoryImpl, movieTicketId)
        
        seatBoard = findViewById(R.id.seat_board_layout)
        
        val seatsTable = seatBoard.children.filterIsInstance<TableRow>().flatMap { it.children }
            .filterIsInstance<Button>().toList()
        
        seatsTable.indexOf(seatsTable[0])
        
    }

//    fun showSeatNumber(
//    index: Int,
//    seat: Seat,
//    ) {
//        seatsTable[index].apply {
//            text = getString(R.string.select_seat_number).format(seat.row, seat.column)
//            setTextColor(setUpSeatColorByGrade(seat.grade))
//            setOnClickListener {
//                val seatsCount = seatsTable.count { seat -> seat.isSelected }
//                if (seatsCount < ticket.count || it.isSelected) {
//                    updateSeatSelectedState(index, isSelected)
//                    val updatedSeatsCount = seatsTable.count { seat -> seat.isSelected }
//                    presenter.manageSelectedSeats(it.isSelected, index, seat)
//                    presenter.updateTotalPrice(it.isSelected, seat)
//                    setConfirmButtonEnabled(updatedSeatsCount)
//                }
//            }
//        }
//    }
    
    override fun showMessage(message: String) {
        showSnackBar(message)
    }
    
    companion object {
        const val EXTRA_MOVIE_TICKET_ID = "movieTicketId"
    }
}
