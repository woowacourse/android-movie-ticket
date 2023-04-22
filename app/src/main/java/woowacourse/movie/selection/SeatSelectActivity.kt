package woowacourse.movie.selection

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TableRow
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import woowacourse.movie.BackKeyActionBarActivity
import woowacourse.movie.KEY_MOVIE
import woowacourse.movie.KEY_RESERVATION_COUNT
import woowacourse.movie.KEY_RESERVATION_DATE
import woowacourse.movie.KEY_RESERVATION_MONEY
import woowacourse.movie.KEY_RESERVATION_TIME
import woowacourse.movie.Movie
import woowacourse.movie.confirm.ReservationConfirmActivity
import woowacourse.movie.databinding.ActivitySeatSelectBinding
import woowacourse.movie.domain.DiscountCalculator
import woowacourse.movie.entity.Count
import woowacourse.movie.entity.Money
import woowacourse.movie.entity.Seat
import woowacourse.movie.entity.SeatRank
import woowacourse.movie.entity.ViewingDate
import woowacourse.movie.entity.ViewingTime
import woowacourse.movie.utils.getParcelableCompat
import java.lang.IllegalArgumentException
import java.time.LocalDateTime
import kotlin.properties.Delegates

class SeatSelectActivity : BackKeyActionBarActivity() {
    private lateinit var binding: ActivitySeatSelectBinding

    private var chosenSeats = mutableListOf<Seat>()
    private lateinit var movie: Movie
    private var reservationCount by Delegates.notNull<Int>()
    private lateinit var date: ViewingDate
    private lateinit var time: ViewingTime
    private lateinit var dateTime: LocalDateTime
    private var totalMoney = 0

    override fun onCreateView(savedInstanceState: Bundle?) {
        binding = ActivitySeatSelectBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Log.v("hi", "test")

        movie = intent.getParcelableCompat(KEY_MOVIE)!!
        reservationCount = intent.getIntExtra(KEY_RESERVATION_COUNT, 0)
        date = intent.getParcelableCompat(KEY_RESERVATION_DATE)!!
        time = intent.getParcelableCompat(KEY_RESERVATION_TIME)!!
        dateTime = LocalDateTime.of(date.value, time.value)

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

                    if (chosenSeats.size == reservationCount) {
                        binding.selectConfrimBtn.isEnabled = true
                        binding.selectConfrimBtn.setBackgroundColor(Color.parseColor("#6200EE"))
                    } else {
                        binding.selectConfrimBtn.isEnabled = false
                        binding.selectConfrimBtn.setBackgroundColor(Color.parseColor("#B7B7B7"))
                    }

                    addMoney()
                }
            }

        retryConfirm()
    }

    private fun positionFind(index: Int): Seat {
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

    private fun addMoney() {
        totalMoney = 0
        chosenSeats.map {
            totalMoney += DiscountCalculator().discount(Money(it.rank.money), dateTime).value
        }
        binding.selectMoney.text = ReservationConfirmActivity.DECIMAL_FORMATTER.format(totalMoney)
    }

    private fun retryConfirm() {
        binding.selectConfrimBtn.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("예매 확인")
                .setMessage("정말 예매하시겠습니까?")
                .setPositiveButton("예매 완료") { _, _ ->
                    val intent = Intent(this, ReservationConfirmActivity::class.java)
                    intent.putExtra(KEY_MOVIE, movie)
                    intent.putExtra(KEY_RESERVATION_COUNT, Count(reservationCount))
                    intent.putExtra(KEY_RESERVATION_MONEY, Money(totalMoney))
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
}
