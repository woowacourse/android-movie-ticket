package woowacourse.movie.presentation.activities.ticketing

import android.os.Bundle
import android.view.MenuItem
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.databinding.ItemMovieSeatBinding
import woowacourse.movie.databinding.LayoutSeatSelectBinding

class SeatSelectActivity : AppCompatActivity() {
    private lateinit var binding: LayoutSeatSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutSeatSelectBinding.inflate(layoutInflater).also { setContentView(it.root) }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        with(binding) {
            for (i in 1..5) {
                val tableRow = TableRow(applicationContext)
                for (j in 1..4) {
                    val seatView = ItemMovieSeatBinding.inflate(layoutInflater, tableRow, true)
                    seatView.seatNumberTv.text = "${(i + 64).toChar()}$j"
                    seatView.root.setOnClickListener {
                        seatView.seatNumberTv.isChecked = !seatView.seatNumberTv.isChecked
                    }
                }
                seatTable.addView(tableRow)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
