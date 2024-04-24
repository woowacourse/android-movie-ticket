package woowacourse.movie.view.reservation

import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R

class SeatSelectionActivity : AppCompatActivity() {
    private val seatTableLayout: TableLayout by lazy { findViewById(R.id.table_layout_seat_selection) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        val seats =
            seatTableLayout.children.filterIsInstance<TableRow>().flatMap { it.children }
                .filterIsInstance<Button>()
    }
}
