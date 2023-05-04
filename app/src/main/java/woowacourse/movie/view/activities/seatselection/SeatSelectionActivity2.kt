package woowacourse.movie.view.activities.seatselection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import woowacourse.movie.R

class SeatSelectionActivity2 : AppCompatActivity(), SeatSelectionContract.View {

    private val presenter: SeatSelectionContract.Presenter = SeatSelectionPresenter(this)

    private val selectedSeatNames: MutableSet<String> = mutableSetOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection2)

        presenter.loadScreening(intent.getLongExtra(SCREENING_ID, -1L))
    }

    override fun setSeats(seatUIStates: List<List<SeatUIState>>) {
        val seatsView = findViewById<TableLayout>(R.id.seat_table)
        seatUIStates.forEach {
            val tableRow = TableRow(this).apply {
                layoutParams = TableLayout.LayoutParams(0, 0, 1f)
            }
            it.forEach { seatUIState ->
                tableRow.addView(createSeatUI(seatUIState.seatName, seatUIState.textColor))
            }
            seatsView.addView(tableRow)
        }
    }

    private fun createSeatUI(seatName: String, @ColorRes textColor: Int): AppCompatButton =
        AppCompatButton(this).apply {
            text = seatName
            setTextColor(getColor(textColor))
            setBackgroundColor(getColor(R.color.unselected_seat_color))
            setOnClickListener { onSeatButtonClick(this) }
            layoutParams = TableRow.LayoutParams(0, Toolbar.LayoutParams.MATCH_PARENT, 1f)
        }

    private fun onSeatButtonClick(button: AppCompatButton) {
        fun deselect(button: AppCompatButton) {
            button.isSelected = false
            button.setBackgroundColor(getColor(R.color.unselected_seat_color))
            selectedSeatNames.remove(button.text)
        }
        fun select(button: AppCompatButton) {
            button.isSelected = true
            button.setBackgroundColor(getColor(R.color.selected_seat_color))
            selectedSeatNames.add(button.text.toString())
        }

        if (button.isSelected) deselect(button)
        else select(button)
    }

    override fun setMovieTitle(title: String) {
        val titleView = findViewById<TextView>(R.id.movie_title_tv)
        titleView.text = title
    }

    override fun setReservationFee(fee: Int) {
        val reservationFeeView = findViewById<TextView>(R.id.reservation_fee_tv)
        reservationFeeView.text = getString(R.string.fee_format).format(fee)
    }

    companion object {
        const val SCREENING_ID = "SCREENING_ID"
        const val SCREENING_DATE_TIME = "SCREENING_DATE_TIME"
    }
}
