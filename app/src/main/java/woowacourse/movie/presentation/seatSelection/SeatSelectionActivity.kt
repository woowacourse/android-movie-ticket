package woowacourse.movie.presentation.seatSelection

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.model.Seat
import woowacourse.movie.model.SeatClass
import woowacourse.movie.presentation.movieList.MovieListActivity.Companion.EXTRA_MOVIE_ID

class SeatSelectionActivity : AppCompatActivity(), SeatSelectionContract.View {
    private lateinit var presenter: SeatSelectionPresenter
    private val seatItems: List<TextView> by lazy {
        findViewById<TableLayout>(R.id.tl_seats).children.filterIsInstance<TableRow>()
            .flatMap { tableRow ->
                tableRow.children.filterIsInstance<TextView>().toList()
            }.toList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, EXTRA_DEFAULT_MOVIE_ID)
        presenter = SeatSelectionPresenter(this, movieId)
        presenter.initializeSeats()
    }

    override fun initializeSeats(seats: List<Seat>) {
        seatItems.forEachIndexed { index, textView ->
            val seat = seats[index]
            textView.text = "${('A'.code + seat.row).toChar()}${seat.col + 1}"
            val colorCode =
                when (seat.seatClass) {
                    SeatClass.B_CLASS -> "#8E13EF"
                    SeatClass.A_CLASS -> "#19D358"
                    SeatClass.S_CLASS -> "#1B48E9"
                }
            textView.setTextColor(Color.parseColor(colorCode))
            textView.setOnClickListener { presenter.updateSeatSelection(index) }
        }
    }

    override fun updateSelectedSeatUI(index: Int) {
        val clickedColor = ContextCompat.getColor(this, R.color.clickedSeat_bgr)
        seatItems[index].setBackgroundColor(clickedColor)
    }

    override fun updateUnSelectedSeatUI(index: Int) {
        val unClickedColor = ContextCompat.getColor(this, R.color.unClickedSeat_bgr)
        seatItems[index].setBackgroundColor(unClickedColor)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_DEFAULT_MOVIE_ID = -1
    }
}
