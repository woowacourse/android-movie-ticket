package woowacourse.movie.seat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import woowacourse.movie.KeyIdentifiers
import woowacourse.movie.R
import woowacourse.movie.domain.Reservation

class SeatActivity : AppCompatActivity(), SeatContract.View {
    private val presenter = SeatPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initSeat()
    }

    private fun initSeat() {
        val seat = findViewById<TableLayout>(R.id.tl_seat)

        seat.children.filterIsInstance<TableRow>().forEachIndexed { rowIndex, row ->
            row.children.filterIsInstance<TextView>()
                .forEachIndexed { colIndex, view ->
                    val point = presenter.getPoint(rowIndex, colIndex)
                    view.tag = point

                    view.text = getString(R.string.seat_point).format('A' + point.x, point.y + 1)
                }
        }
    }

    companion object {
        fun newIntent(
            context: Context,
            reservation: Reservation,
        ): Intent =
            Intent(context, SeatActivity::class.java).apply {
                putExtra(KeyIdentifiers.KEY_RESERVATION, reservation)
            }
    }
}
