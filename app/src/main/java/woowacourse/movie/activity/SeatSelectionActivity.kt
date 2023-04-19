package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.view.widget.SeatTableLayout

class SeatSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        val seatTableLayout = SeatTableLayout.from(
            findViewById(R.id.seat_selection_table), SEAT_ROW_COUNT, SEAT_COLUMN_COUNT, 3
        )

        findViewById<Button>(R.id.seat_selection_reserve_button).setOnClickListener {
            onClickReserveButton(seatTableLayout)
        }
    }

    private fun onClickReserveButton(seatTableLayout: SeatTableLayout) {
        AlertDialog.Builder(this).setTitle(getString(R.string.seat_selection_alert_title))
            .setMessage(getString(R.string.seat_selection_alert_message))
            .setPositiveButton(getString(R.string.seat_selection_alert_positive)) { _, _ ->
                reserveMovie(seatTableLayout)
            }.setNegativeButton(getString(R.string.seat_selection_alert_negative)) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun reserveMovie(seatTableLayout: SeatTableLayout) {
        val intent = Intent(this, ReservationResultActivity::class.java)
        // intent.putExtra("", null)
        startActivity(intent)
    }

    companion object {
        private const val SEAT_ROW_COUNT = 5
        private const val SEAT_COLUMN_COUNT = 4
    }
}
