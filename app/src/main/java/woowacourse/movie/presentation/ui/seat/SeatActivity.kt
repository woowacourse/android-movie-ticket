package woowacourse.movie.presentation.ui.seat

import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.data.repository.MovieTicketRepositoryImpl
import woowacourse.movie.data.repository.SeatRepositoryImpl
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.SeatGrade
import woowacourse.movie.presentation.base.BaseActivity

class SeatActivity : BaseActivity(), SeatContract.View {
    private lateinit var presenter: SeatPresenter
    
    override fun getLayoutResId(): Int = R.layout.activity_seat
    
    private lateinit var seatBoard: TableLayout
    private lateinit var title: TextView
    private lateinit var totalPrice: TextView
    private lateinit var confirmButton: Button
    private lateinit var seatsTable: List<Button>
    
    override fun onCreateSetup() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        
        val movieTicketId = intent.getIntExtra(EXTRA_MOVIE_TICKET_ID, -1)
        
        seatBoard = findViewById(R.id.seat_board_layout)
        title = findViewById(R.id.title)
        seatsTable = seatBoard.children.filterIsInstance<TableRow>().flatMap { it.children }
            .filterIsInstance<Button>().toList()
        
        presenter = SeatPresenter(this, MovieTicketRepositoryImpl, SeatRepositoryImpl, movieTicketId)
    }
    
    override fun showMovieTitle(movieTitle: String) {
        title.text = movieTitle
    }
    
    override fun showSeats(seat: List<Seat>) {
        seatsTable.forEachIndexed { index, button ->
            button.text = seat[index].toString()
            button.setTextColor(loadSeatColor(seat[index].seatGrade))
            button.setOnClickListener {
                button.isSelected = !button.isSelected
            }
        }
    }
    
    private fun loadSeatColor(grade: SeatGrade): Int {
        return when (grade) {
            SeatGrade.B -> getColor(R.color.purple_500)
            SeatGrade.S -> getColor(R.color.teal_700)
            SeatGrade.A -> getColor(R.color.blue_500)
        }
    }
    
    override fun showMessage(message: String) {
        showSnackBar(message)
    }
    
    companion object {
        const val EXTRA_MOVIE_TICKET_ID = "movieTicketId"
    }
}
