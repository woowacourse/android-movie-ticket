package woowacourse.movie.view.seatselection

import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.view.BaseActivity
import woowacourse.movie.view.viewmodel.MovieUIModel
import java.time.LocalDateTime

class SeatSelectionActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        val selectedDate = intent.getSerializableExtra(DATE_KEY) as LocalDateTime
        val selectedNumberOfPeople = intent.getIntExtra(NUMBER_OF_PEOPLE_KEY, 0)
        val movieUI = intent.getSerializableExtra(MOVIE_KEY) as MovieUIModel

        setBackToBefore(R.id.seat_selection_toolbar)

        findViewById<TextView>(R.id.seat_selection_title).text = movieUI.title
        val price = findViewById<TextView>(R.id.seat_selection_price)
        price.text = this.getString(R.string.price, 0)
        val seatsView = findViewById<TableLayout>(R.id.seat_selection)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>().toList()

        val seatState = SeatState(selectedDate)
        seatsView.forEachIndexed { index, view ->
            view.setOnClickListener {
                seatState.setSeatState(index, view, this)
                price.text = getString(R.string.price, seatState.priceNum)

                checkNumberOfPeople(selectedNumberOfPeople, seatState.countPeople)
            }
        }
    }

    private fun checkNumberOfPeople(numberOfPeople: Int, countPeople: Int) {
        val nextBtn = findViewById<Button>(R.id.seat_selection_check)
        nextBtn.isEnabled = (countPeople == numberOfPeople)
    }

    companion object {
        private const val MOVIE_KEY = "movie"
        private const val DATE_KEY = "date"
        private const val NUMBER_OF_PEOPLE_KEY = "numberOfPeople"
    }
}
