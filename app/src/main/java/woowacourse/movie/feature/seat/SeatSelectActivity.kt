package woowacourse.movie.feature.seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.view.get
import woowacourse.movie.R

class SeatSelectActivity : AppCompatActivity() {
    private val seatTable by lazy { findViewById<TableLayout>(R.id.seat_table) }
    private val titleText by lazy { findViewById<TextView>(R.id.title_text) }
    private val reservationAmountText by lazy { findViewById<TextView>(R.id.reservation_amount_text) }
    private val confirmButton by lazy { findViewById<Button>(R.id.confirm_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_seat_select)
        val seatViews =
            seatTable
                .children
                .filterIsInstance<TableRow>()
                .map { it.children.filterIsInstance<TextView>().toList() }
                .toList()
        seatViews[0][0].text = "A1"
        seatViews[0][1].text = "A2"
        seatViews[0][2].text = "A3"
        seatViews[0][3].text = "A4"
        seatViews[1][0].text = "B1"

        Log.d(TAG, "${seatViews.size}, ${seatViews[0].size}")
    }

    companion object {
        private val TAG = SeatSelectActivity::class.simpleName

        fun startActivity(context: Context) {
            Intent(context, SeatSelectActivity::class.java).run {
                context.startActivity(this)
            }
        }
    }
}
