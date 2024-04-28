package woowacourse.movie.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.contract.SeatSelectionContract
import woowacourse.movie.model.Theater
import woowacourse.movie.model.seat.Position
import woowacourse.movie.presenter.SeatSelectionPresenter
import woowacourse.movie.uimodel.SeatPlan

class SeatSelectionActivity : AppCompatActivity(), SeatSelectionContract.View {
    private lateinit var movieRecyclerView: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_activity)
        val presenter = SeatSelectionPresenter(
            view = this,
        )
        movieRecyclerView = findViewById(R.id.movies_recycler_view)
        val seatPlan = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("SeatPlan", SeatPlan::class.java)
        } else {
            intent.getParcelableExtra(Intent.EXTRA_STREAM) as? SeatPlan
        }
        if(seatPlan != null) {
            presenter.loadData(
                seatPlan.movieId,
                seatPlan.ticketNum,
                seatPlan.reservedDateTime
            )
        } else finish()
    }

    override fun displayTheater(theater: Theater) {
        TODO("Not yet implemented")
    }

    override fun displaySelectedSeat(position: Position) {
        TODO("Not yet implemented")
    }

    override fun displayDeSelectedSeat(position: Position) {
        TODO("Not yet implemented")
    }

    override fun activateConfirm() {
        TODO("Not yet implemented")
    }

    override fun deActivateConfirm() {
        TODO("Not yet implemented")
    }

    override fun displayTicketPrice(price: Int) {
        TODO("Not yet implemented")
    }

    override fun displayConfirmDialog() {
        TODO("Not yet implemented")
    }

    override fun navigateToPurchaseConfirmation() {
        TODO("Not yet implemented")
    }
}
