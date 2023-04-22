package woowacourse.movie.selection

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TableRow
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.KEY_MOVIE
import woowacourse.movie.KEY_RESERVATION_COUNT
import woowacourse.movie.KEY_RESERVATION_DATE
import woowacourse.movie.KEY_RESERVATION_TIME
import woowacourse.movie.Movie
import woowacourse.movie.confirm.ReservationConfirmActivity
import woowacourse.movie.databinding.ActivitySeatSelectBinding
import woowacourse.movie.entity.Count
import woowacourse.movie.entity.Seat
import woowacourse.movie.entity.SeatRank
import woowacourse.movie.entity.ViewingDate
import woowacourse.movie.entity.ViewingTime
import woowacourse.movie.utils.getParcelableCompat
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

        val movie = intent.getParcelableCompat<Movie>(KEY_MOVIE)!!
        val reservationCount = intent.getParcelableCompat<Count>(KEY_RESERVATION_COUNT)!!
        val date = intent.getParcelableCompat<ViewingDate>(KEY_RESERVATION_DATE)!!
        val time = intent.getParcelableCompat<ViewingTime>(KEY_RESERVATION_TIME)!!

        binding.selectTitle.text = movie.title

        binding.selectConfrimBtn.isEnabled = false
        binding.selectConfrimBtn.setBackgroundColor(Color.parseColor("#B7B7B7"))

        binding.seatTable.children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<Button>()
            .forEachIndexed { index, view ->
                view.setOnClickListener {
                    Log.v("hi", "ihi $index / $view")

                    if (chosenSeats.contains(positionFind(index))) {
                        chosenSeats.remove(positionFind(index))
                        view.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    } else {
                        chosenSeats.add(positionFind(index))
                        view.setBackgroundColor(Color.parseColor("#FAFF00"))
                    }

                    if (chosenSeats.size == 3) {
                        binding.selectConfrimBtn.isEnabled = true
                        binding.selectConfrimBtn.setBackgroundColor(Color.parseColor("#6200EE"))
                    } else {
                        binding.selectConfrimBtn.isEnabled = false
                        binding.selectConfrimBtn.setBackgroundColor(Color.parseColor("#B7B7B7"))
                    }
                }
            }

        binding.selectConfrimBtn.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("예매 확인")
                .setMessage("정말 예매하시겠습니까?")
                .setPositiveButton("예매 완료") { _, _ ->
                    val intent = Intent(this, ReservationConfirmActivity::class.java)
                    intent.putExtra(KEY_MOVIE, movie)
                    intent.putExtra(KEY_RESERVATION_COUNT, reservationCount)
                    intent.putExtra(KEY_RESERVATION_DATE, date)
                    intent.putExtra(KEY_RESERVATION_TIME, time)
                    startActivity(intent)
                }
                .setNegativeButton("취소") { dialog, _ ->
                    dialog.dismiss()
                }.setCancelable(false)
                .show()
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
