package woowacourse.movie.selection

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableRow
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import woowacourse.movie.KEY_MOVIE
import woowacourse.movie.KEY_RESERVATION_COUNT
import woowacourse.movie.KEY_RESERVATION_DATE
import woowacourse.movie.KEY_RESERVATION_MONEY
import woowacourse.movie.KEY_RESERVATION_SEATS
import woowacourse.movie.KEY_RESERVATION_TIME
import woowacourse.movie.R
import woowacourse.movie.confirm.ReservationConfirmActivity
import woowacourse.movie.databinding.ActivitySeatSelectBinding
import woowacourse.movie.domain.DiscountCalculator
import woowacourse.movie.fomatter.MoneyFormatter
import woowacourse.movie.mapper.CountMapper
import woowacourse.movie.mapper.MoneyMapper
import woowacourse.movie.model.Money
import woowacourse.movie.model.MovieAndAd
import woowacourse.movie.model.Seat
import woowacourse.movie.model.SeatRank
import woowacourse.movie.model.Seats
import woowacourse.movie.model.ViewingDate
import woowacourse.movie.model.ViewingTime
import woowacourse.movie.utils.BackKeyActionBarActivity
import woowacourse.movie.utils.getParcelableCompat
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

    @SuppressLint("ResourceAsColor")
    private fun initBind() {
        binding.selectTitle.text = movie.title
        binding.selectConfrimBtn.isEnabled = false
        binding.selectConfrimBtn.setBackgroundColor(R.color.inactivate_btn)
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

    @SuppressLint("ResourceAsColor")
    private fun selectSeat(index: Int, view: View) {
        if (chosenSeats.contains(positionFind(index))) {
            chosenSeats.remove(positionFind(index))
            view.isSelected = false
            view.setBackgroundColor(R.color.inactivate_seat)
        } else {
            chosenSeats.add(positionFind(index))
            view.isSelected = true
            view.setBackgroundColor(R.color.activate_seat)
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun activeConfirmBtn() {
        if (chosenSeats.size == reservationCount) {
            binding.selectConfrimBtn.isEnabled = true
            binding.selectConfrimBtn.setBackgroundColor(R.color.activate_btn)
        } else {
            binding.selectConfrimBtn.isEnabled = false
            binding.selectConfrimBtn.setBackgroundColor(R.color.inactivate_btn)
        }
    }

    private fun positionFind(index: Int): Seat {
        val row = index % 4 + 1
        val rank = when (index / 4 + 1) {
            R.string.columnOne -> SeatRank.A
            R.string.columnTwo -> SeatRank.B
            R.string.columnThree -> SeatRank.C
            R.string.columnFour -> SeatRank.D
            else -> SeatRank.E
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
                .setTitle(R.string.dialogTitle)
                .setMessage(R.string.dialogMessage)
                .setPositiveButton(R.string.dialogPositive) { _, _ -> putIntent() }
                .setNegativeButton(R.string.dialogNegative) { dialog, _ -> dialog.dismiss() }
                .setCancelable(false)
                .show()
        }
    }

    private fun putIntent() {
        val intent = getCustomIntent(this, movie, reservationCount, totalMoney, date, time, chosenSeats)
        startActivity(intent)
    }

    companion object {
        fun getCustomIntent(
            context: Context,
            movie: MovieAndAd.Movie,
            reservationCount: Int,
            totalMoney: Int,
            date: ViewingDate,
            time: ViewingTime,
            chosenSeats: MutableList<Seat>
        ): Intent {
            val intent = Intent(context, ReservationConfirmActivity::class.java)
            intent.putExtra(KEY_MOVIE, movie)
            intent.putExtra(KEY_RESERVATION_COUNT, CountMapper(reservationCount))
            intent.putExtra(KEY_RESERVATION_MONEY, MoneyMapper(totalMoney))
            intent.putExtra(KEY_RESERVATION_DATE, date)
            intent.putExtra(KEY_RESERVATION_TIME, time)
            intent.putExtra(KEY_RESERVATION_SEATS, Seats(chosenSeats))
            return intent
        }
    }
}
