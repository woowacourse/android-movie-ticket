package woowacourse.movie.activity

import android.os.Bundle
import android.view.Gravity
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import seat.Seat.Companion.MAX_COLUMN
import seat.Seat.Companion.MAX_ROW
import seat.Seat.Companion.MIN_COLUMN
import seat.Seat.Companion.MIN_ROW
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivitySeatSelectionBinding

class SeatSelectionActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySeatSelectionBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSeatsViews()
    }

    private fun setSeatsViews() {
        for (row in MIN_ROW..MAX_ROW) {
            val tableRow = TableRow(this)
            tableRow.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, 180, 1f
            )

            for (col in MIN_COLUMN..MAX_COLUMN) {
                val textView = TextView(this)
                textView.layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    180,
                    1f
                )

                textView.text = getString(R.string.seat_name_form).format(row, col)
                textView.setBackgroundColor(getColor(R.color.white))
                textView.setTextColor(getColor(R.color.black))
                textView.gravity = Gravity.CENTER
                tableRow.addView(textView)
            }

            binding.seatTableLayout.addView(tableRow)
        }
    }
}
