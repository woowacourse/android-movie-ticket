package woowacourse.movie.selection

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.databinding.ActivitySeatSelectBinding
import woowacourse.movie.entity.Seat
import woowacourse.movie.entity.SeatRank
import java.lang.IllegalArgumentException

class SeatSelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySeatSelectBinding
    var chosenSeats = mutableListOf<Seat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeatSelectBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Log.v("hi", "test")

        binding.selectConfrimBtn.isEnabled = false
        binding.selectConfrimBtn.setBackgroundColor(Color.parseColor("#B7B7B7"))

        binding.seatTable.children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<Button>()
            .forEachIndexed { index, view ->
                view.setOnClickListener {
                    Log.v("hi", "ihi $index / $view")
                    chosenSeats.add(positionFind(index))
                    view.setBackgroundColor(Color.parseColor("#FAFF00"))

                    if (chosenSeats.size == 3) {
                        binding.selectConfrimBtn.isEnabled = true
                        binding.selectConfrimBtn.setBackgroundColor(Color.parseColor("#6200EE"))
                    }
                }
            }
    }

    fun positionFind(index: Int): Seat {
        val row = index % 4 + 1
        val column = index / 4 + 1
        Log.v("hi", "rank: $column , row: $row")
        val rank = when (column) {
            1 -> SeatRank.A
            2 -> SeatRank.B
            3 -> SeatRank.C
            4 -> SeatRank.D
            5 -> SeatRank.E
            else -> throw IllegalArgumentException("[ERROR]")
        }
        return Seat(rank, row)
    }

    companion object {
    }
}
