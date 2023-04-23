package woowacourse.movie.selection

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableRow
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import com.example.domain.domain.DiscountCalculator
import com.example.domain.model.Money
import com.example.domain.model.SeatRank
import woowacourse.movie.KEY_MOVIE
import woowacourse.movie.KEY_RESERVATION_COUNT
import woowacourse.movie.KEY_RESERVATION_DATE
import woowacourse.movie.KEY_RESERVATION_MONEY
import woowacourse.movie.KEY_RESERVATION_SEATS
import woowacourse.movie.KEY_RESERVATION_TIME
import woowacourse.movie.confirm.ReservationConfirmActivity
import woowacourse.movie.databinding.ActivitySeatSelectBinding
import woowacourse.movie.fomatter.MoneyFormatter
import woowacourse.movie.mapper.CountMapper
import woowacourse.movie.mapper.MoneyMapper
import woowacourse.movie.model.MovieAndAd
import woowacourse.movie.model.Seat
import woowacourse.movie.model.Seats
import woowacourse.movie.model.ViewingDate
import woowacourse.movie.model.ViewingTime
import woowacourse.movie.utils.BackKeyActionBarActivity
import woowacourse.movie.utils.getParcelableCompat
import java.lang.IllegalArgumentException
import java.time.LocalDateTime
import kotlin.properties.Delegates

class SeatSelectActivity : BackKeyActionBarActivity() {
    private lateinit var binding: ActivitySeatSelectBinding

    private var chosenSeats = mutableListOf<Seat>()
    private lateinit var movie: MovieAndAd.Movie
    private var reservationCount by Delegates.notNull<Int>()
    private lateinit var date: ViewingDate
    private lateinit var time: ViewingTime
    private lateinit var dateTime: LocalDateTime
    private var totalMoney = 0

    override fun onCreateView(savedInstanceState: Bundle?) {
        binding = ActivitySeatSelectBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        getData()
        initBind()
        retryConfirm()
    }

    private fun getData() {
        movie = intent.getParcelableCompat(KEY_MOVIE)!!
        reservationCount = intent.getIntExtra(KEY_RESERVATION_COUNT, 0)
        date = intent.getParcelableCompat(KEY_RESERVATION_DATE)!!
        time = intent.getParcelableCompat(KEY_RESERVATION_TIME)!!
        dateTime = LocalDateTime.of(date.value, time.value)
    }

    private fun initBind() {
        binding.selectTitle.text = movie.title
        binding.selectConfrimBtn.isEnabled = false
        binding.selectConfrimBtn.setBackgroundColor(Color.parseColor("#B7B7B7"))
        binding.seatTable.children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<Button>()
            .forEachIndexed { index, view ->
                view.setOnClickListener {
                    selectSeat(index, view)
                    activeConfirmBtn()
                    addMoney()
                }
            }
    }

    private fun selectSeat(index: Int, view: View) {
        if (chosenSeats.contains(positionFind(index))) {
            chosenSeats.remove(positionFind(index))
            view.setBackgroundColor(Color.parseColor("#FFFFFF"))
        } else {
            chosenSeats.add(positionFind(index))
            view.setBackgroundColor(Color.parseColor("#FAFF00"))
        }
    }

    private fun activeConfirmBtn() {
        if (chosenSeats.size == reservationCount) {
            binding.selectConfrimBtn.isEnabled = true
            binding.selectConfrimBtn.setBackgroundColor(Color.parseColor("#6200EE"))
        } else {
            binding.selectConfrimBtn.isEnabled = false
            binding.selectConfrimBtn.setBackgroundColor(Color.parseColor("#B7B7B7"))
        }
    }

    private fun positionFind(index: Int): Seat {
        val row = index % 4 + 1
        val rank = when (index / 4 + 1) {
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
        binding.selectMoney.text = MoneyFormatter().active(Money(totalMoney))
    }

    private fun retryConfirm() {
        binding.selectConfrimBtn.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("예매 확인")
                .setMessage("정말 예매하시겠습니까?")
                .setPositiveButton("예매 완료") { _, _ -> putIntent() }
                .setNegativeButton("취소") { dialog, _ -> dialog.dismiss() }
                .setCancelable(false)
                .show()
        }
    }

    private fun putIntent() {
        val intent = Intent(this, ReservationConfirmActivity::class.java)
        intent.putExtra(KEY_MOVIE, movie)
        intent.putExtra(KEY_RESERVATION_COUNT, CountMapper(reservationCount))
        intent.putExtra(KEY_RESERVATION_MONEY, MoneyMapper(totalMoney))
        intent.putExtra(KEY_RESERVATION_DATE, date)
        intent.putExtra(KEY_RESERVATION_TIME, time)
        intent.putExtra(KEY_RESERVATION_SEATS, Seats(chosenSeats))
        startActivity(intent)
    }
}
