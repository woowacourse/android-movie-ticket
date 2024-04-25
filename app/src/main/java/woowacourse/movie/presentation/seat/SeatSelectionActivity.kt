package woowacourse.movie.presentation.seat

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.data.SeatRepositoryImpl
import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.presentation.seat.model.SeatSelectType
import woowacourse.movie.domain.model.SeatType

class SeatSelectionActivity : AppCompatActivity(), SeatSelectionContract.View {
    private lateinit var seatTable: TableLayout
    private lateinit var confirmButton: TextView
    private val presenter = SeatSelectionPresenter(
        view = this@SeatSelectionActivity,
        seatRepository = SeatRepositoryImpl(),
        ticketCount = 2,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        initView()
        presenter.loadSeat()
    }

    private fun initView() {
        seatTable = findViewById(R.id.seat_table)
        confirmButton = findViewById(R.id.confirm_button)
    }

    override fun showSeat(seats: List<List<MovieSeat>>) {
        seatTable
            .children
            .filterIsInstance<TableRow>()
            .forEachIndexed { rowIndex, rows ->
                rows.children.filterIsInstance<TextView>()
                    .forEachIndexed { columIndex, view ->
                        val currentSeat = seats[rowIndex][columIndex]
                        view.text = currentSeat.seatName
                        view.setTextColor(
                            when (currentSeat.seatType) {
                                SeatType.S -> getColor(R.color.seat_s_text_color)
                                SeatType.A -> getColor(R.color.seat_a_text_color)
                                SeatType.B -> getColor(R.color.seat_b_text_color)
                            }
                        )
                        view.setOnClickListener {
                            presenter.selectSeat(rowIndex, columIndex)
                        }
                    }
            }
    }

    override fun showSelectedSeat(rowIndex: Int, columnIndex: Int,selectType: SeatSelectType) {
        val tableRowAtIndex = seatTable.getChildAt(rowIndex) as TableRow
        val textViewAtIndex = tableRowAtIndex.getChildAt(columnIndex) as TextView
        if (selectType == SeatSelectType.ADD){
            textViewAtIndex.setBackgroundColor(getColor(R.color.select_seat_color))
        }else {
            textViewAtIndex.setBackgroundColor(getColor(R.color.white))
        }
    }
}
