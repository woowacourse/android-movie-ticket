package woowacourse.movie.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.view.data.SeatViewData
import woowacourse.movie.view.widget.SeatView

class SeatSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        makeSeatTable(5, 4)
        findViewById<Button>(R.id.seat_selection_reserve_button).setOnClickListener {
            onClickReserveButton()
        }
    }

    private fun onClickReserveButton() {
        AlertDialog.Builder(this).setTitle("예매 확인").setMessage("정말 예매하시겠습니까?")
            .setPositiveButton("예매 완료") { _, _ ->
                val intent = Intent(this, ReservationResultActivity::class.java)
                // intent.putExtra("", null)
                startActivity(intent)
            }.setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun makeSeatTable(rowSize: Int, columnSize: Int) {
        val tableLayout = findViewById<TableLayout>(R.id.seat_selection_table)
        tableLayout.weightSum = rowSize.toFloat()
        (0 until rowSize).forEach {
            tableLayout.addView(makeSeatRow(it, columnSize))
        }
    }

    private fun makeSeatRow(row: Int, columnSize: Int): TableRow {
        val tableRow = TableRow(this)
        tableRow.weightSum = columnSize.toFloat()
        tableRow.layoutParams = TableLayout.LayoutParams(0, 0, 1f)
        (0 until columnSize).forEach {
            tableRow.addView(makeSeatCell(row, it))
        }
        return tableRow
    }

    private fun makeSeatCell(row: Int, column: Int): View {
        return SeatView(this, SeatViewData('a', column, Color.BLACK))
    }
}
